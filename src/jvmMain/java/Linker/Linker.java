package Linker;

import Assembler.DefinitionTableEntry;
import Assembler.SymbolTableEntry;
import Assembler.Tables;
import Assembler.UseTableEntry;
import instructions.Instruction;
import main.Instructions;
import main.Memory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Linker {
    private final GlobalSymbolTable globalSymbolTable;

    public Linker() {
        globalSymbolTable = new GlobalSymbolTable();
    }

    public int run(File[] files, Tables[] moduleTables, Memory memory) throws IOException {
        firstStep(files, moduleTables);
        int[] modulesSize = secondStep(files, moduleTables);
        return loader(modulesSize, moduleTables, memory);
    }

    public void firstStep(File[] files, Tables[] moduleTables) throws IOException {
        int programSize = 0;

        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName().split("\\.")[0] + ".obj";
            Tables tables = moduleTables[i];
            HashMap<String, DefinitionTableEntry> moduleDefinitionTable = tables.getDefinitionTable();
            for (String symbol : moduleDefinitionTable.keySet()) {
                if (moduleDefinitionTable.get(symbol).getRelocatable() == 'r') {
                    globalSymbolTable.newEntry(symbol, new DefinitionTableEntry(symbol, 'r', (short) (moduleDefinitionTable.get(symbol).getValue() + programSize)));
                } else globalSymbolTable.newEntry(symbol, new DefinitionTableEntry(symbol, 'a', (moduleDefinitionTable.get(symbol).getValue())));
            }

            File file = new File(fileName);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] fileBytes = inputStream.readAllBytes();
            programSize += fileBytes.length;
        }
    }

    public int[] secondStep(File[] files, Tables[] moduleTables) throws IOException {
        int programSize = 0;
        int[] modulesSize = new int[files.length];
        OutputStream linkedProgram = new FileOutputStream("linkedProgram.bin");

        for (int i = 0; i < files.length; i++) {
            String moduleFileName = files[i].getName().split("\\.")[0] + ".obj";
            File moduleFile = new File(moduleFileName);
            FileInputStream inputStream = new FileInputStream(moduleFile);
            byte[] moduleBytes = inputStream.readAllBytes();
            Tables tables = moduleTables[i];
            HashMap<String, UseTableEntry> moduleUseTable = tables.getUseTable();

            for ( Map.Entry<String, UseTableEntry> entry : moduleUseTable.entrySet() ) {
                UseTableEntry useTableEntry = entry.getValue();
                short value;
                if (useTableEntry.getSignal() == '+')
                    value = (short) (globalSymbolTable.getEntry(useTableEntry.getLabel()).getValue() + programSize);
                else value = (short) (globalSymbolTable.getEntry(useTableEntry.getLabel()).getValue());

                byte[] valueBytes = new byte[2];
                valueBytes[0] = (byte) (value & 0xff);
                valueBytes[1] = (byte)((value >> 8) & 0xff);
                ArrayList<Short> occurrences = useTableEntry.getOccurrences();

                for (int j = 0; j < occurrences.size(); j++) {
                    Short occurrence = occurrences.get(j);
                    moduleBytes[occurrence] = valueBytes[0];
                    moduleBytes[occurrence + 1] = valueBytes[1];
                }
            }
            modulesSize[i] = programSize;
            programSize += moduleBytes.length;
            linkedProgram.write(moduleBytes);
            inputStream.close();
        }
        linkedProgram.close();
        return modulesSize;
    }

    public int loader(int[] modulesSize, Tables[] moduleTables, Memory memory) throws IOException {
        FileInputStream input = new FileInputStream("linkedProgram.bin");
        byte[] programBytes = input.readAllBytes();
        short value;
        int i = 0;

        for (Tables tables : moduleTables) {
            HashMap<String, SymbolTableEntry> moduleSymbolTable = tables.getSymbolTable();
            for ( Map.Entry<String, SymbolTableEntry> entry : moduleSymbolTable.entrySet() ) {
                SymbolTableEntry symbolTableEntry = entry.getValue();
                if (symbolTableEntry.getRelocatable() == 'r') {
                    value = (short) (globalSymbolTable.getEntry(symbolTableEntry.getLabel()).getValue());
                    byte[] valueBytes = new byte[2];
                    valueBytes[0] = (byte) (value & 0xff);
                    valueBytes[1] = (byte) ((value >> 8) & 0xff);
                    ArrayList<Short> occurrences = symbolTableEntry.getOccurrences();

                    for (int j = 0; j < occurrences.size(); j++) {
                        Short occurrence = occurrences.get(j);
                        programBytes[occurrence + modulesSize[i]] = valueBytes[0];
                        programBytes[occurrence + modulesSize[i] + 1] = valueBytes[1];
                    }
                }
            }
            i++;
        }

        int pointer = 0;
        Instructions instructions = new Instructions();
        while (pointer < programBytes.length) {
            Instruction instruction = instructions.getInstructionByOpcode(programBytes[pointer]);
            int instructionSize = instruction.getSize();
            switch(instructionSize) {
                case 1:
                    memory.setCell(pointer, programBytes[pointer]);
                    break;

                case 2:
                    memory.setCell(pointer, programBytes[pointer]);
                    memory.setCell(pointer + 1, programBytes[pointer + 1]);
                    break;

                case 3:
                    memory.setCell(pointer, programBytes[pointer]);
                    memory.setCell(pointer + 1, programBytes[pointer + 1]);
                    memory.setCell(pointer + 2, programBytes[pointer + 2]);
            }
            pointer += instructionSize;
        }

        return pointer;
    }
}