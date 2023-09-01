package Linker;

import Assembler.DefinitionTableEntry;

import java.util.HashMap;

public class GlobalSymbolTable {
    private HashMap<String, DefinitionTableEntry> table;

    public GlobalSymbolTable() { table = new HashMap<>(); }

    public void newEntry(String label, DefinitionTableEntry dte) {
        table.put(label, dte);
    }

    public DefinitionTableEntry getEntry(String label) {
        return table.get(label);
    }

    public boolean isEntryInTable(String label) {
        return table.containsKey(label);
    }
}
