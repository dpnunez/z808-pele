package Linker;

import Assembler.DefinitionTableEntry;
import Assembler.Tables;
import Assembler.UseTableEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

    public void firstStep(File[] files, Tables[] moduleTables) throws FileNotFoundException {
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
            Scanner sc = new Scanner(file);
            String code = sc.nextLine();
            programSize += code.length();
        }
    }

    public void secondStep(File[] files, Tables[] moduleTables) throws IOException {
        int programSize = 0;
        File linkedProgram = new File("linkedProgram.txt");
        linkedProgram.createNewFile();
        FileWriter output_linkedProgram = new FileWriter(linkedProgram);

        for (int i = 0; i < files.length; i++) {
            String moduleFileName = files[i].getName().split("\\.")[0] + ".obj";
            File moduleFile = new File(moduleFileName);
            Scanner sc = new Scanner(moduleFile);
            StringBuilder moduleCode = new StringBuilder(sc.nextLine());

            Tables tables = moduleTables[i];
            HashMap<String, UseTableEntry> moduleUseTable = tables.getUseTable();

            for ( Map.Entry<String, UseTableEntry> entry : moduleUseTable.entrySet() ) {
                UseTableEntry useTableEntry = entry.getValue();
                short value = (short) (globalSymbolTable.getEntry(useTableEntry.getLabel()).getValue() + programSize);
                String binaryValue = Integer.toBinaryString((1 << 16) | value).substring( 1 );
                ArrayList<Short> occurrences = useTableEntry.getOccurrences();

                for (int j = 0; j < occurrences.size(); j++) {
                    Short occurrence = occurrences.get(j);
                    System.out.println("Antes de substituir: " + moduleCode.toString());
                    System.out.println("Substituindo pelo valor: " + value);
                    System.out.println("Substituindo pelo valor em binario: " + binaryValue);
                    System.out.println("Substituindo no endereco: " + occurrence);
                    moduleCode.replace(occurrence, occurrence + 16, binaryValue);
                    System.out.println("Depois de substituir: " + moduleCode.toString());

                }
            }

            output_linkedProgram.write(moduleCode.toString());
            programSize += moduleCode.length();
            sc.close();
        }

        output_linkedProgram.close();
    }
}
