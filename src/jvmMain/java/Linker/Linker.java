package Linker;

import Assembler.DefinitionTableEntry;
import Assembler.Tables;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Linker {
    private final GlobalSymbolTable table;

    public Linker() {
        table = new GlobalSymbolTable();
    }

    public void run(File[] files, Tables[] moduleTables) throws FileNotFoundException {
        firstStep(files, moduleTables);
        //secondStep();
    }

    public void firstStep(File[] files, Tables[] moduleTables) throws FileNotFoundException {
        int size = 0;

        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName().split("\\.")[0] + ".obj";
            Tables tables = moduleTables[i];
            HashMap<String, DefinitionTableEntry> definitionTable = tables.getDefinitionTable();
            for (String symbol : definitionTable.keySet()) {
                if (definitionTable.get(symbol).getRelocatable() == 'r') {
                    table.newEntry(symbol, new DefinitionTableEntry(symbol, 'r', (short) (definitionTable.get(symbol).getValue() + size)));
                } else table.newEntry(symbol, new DefinitionTableEntry(symbol, 'a', (short) (definitionTable.get(symbol).getValue())));
            }

            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            String code = sc.nextLine();
            size += code.length();
        }
    }
}
