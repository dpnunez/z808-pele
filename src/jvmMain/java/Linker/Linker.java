package Linker;

import Assembler.DefinitionTableEntry;
import Assembler.Tables;
import Assembler.UseTableEntry;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Linker {
    private final GlobalSymbolTable globalSymbolTable;

    public Linker() {
        globalSymbolTable = new GlobalSymbolTable();
    }

    public void run(File[] files, Tables[] moduleTables) throws IOException {
        firstStep(files, moduleTables);
        secondStep(files, moduleTables);
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

    public void secondStep(File[] files, Tables[] moduleTables) throws IOException {
        int programSize = 0;
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
                short value = (short) (globalSymbolTable.getEntry(useTableEntry.getLabel()).getValue() + programSize);
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

            programSize += moduleBytes.length;
            linkedProgram.write(moduleBytes);
        }

        linkedProgram.close();
    }
}