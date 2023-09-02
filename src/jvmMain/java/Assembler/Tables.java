package Assembler;

import java.util.HashMap;

public class Tables {
    private final HashMap<String, SymbolTableEntry> symbolTable;
    private final HashMap<String, DefinitionTableEntry> definitionTable;
    private final HashMap<String, UseTableEntry> useTable;

    public Tables() {
        this.symbolTable = new HashMap();
        this.definitionTable = new HashMap();
        this.useTable = new HashMap();
    }

    public HashMap<String, SymbolTableEntry> getSymbolTable() {
        return symbolTable;
    }

    public HashMap<String, DefinitionTableEntry> getDefinitionTable() {
        return definitionTable;
    }

    public HashMap<String, UseTableEntry> getUseTable() {
        return useTable;
    }

    public SymbolTableEntry getSymbolTableEntry(String label) { return symbolTable.get(label); }

    public DefinitionTableEntry getDefinitionTableEntry(String label) { return definitionTable.get(label); }

    public UseTableEntry getUseTableEntry(String label) { return useTable.get(label); }

    public void declareSymbolTableEntry (String label) { symbolTable.put(label, null); }

    public void declareDefinitionTableEntry (String label) {
        definitionTable.put(label, null);
    }

    public void declareUseTableEntry (String label) {
        useTable.put(label, null);
    }

    public void newSymbolTableEntry(String label, char relocatable, char definition, Short value) {
        symbolTable.put(label, new SymbolTableEntry(label,relocatable, definition, value));
    }

    public void newDefinitionTableEntry(String label, char relocatable, Short value) {
        definitionTable.put(label, new DefinitionTableEntry(label, relocatable, value));
    }

    public void newUseTableEntry(String label, char signal) {
        useTable.put(label, new UseTableEntry(label, signal));
    }

    public void newUseTableEntryOccurrence(String label, Short value) {
        useTable.get(label).updateOccurrences(value);
    }

    public boolean isSymbolInST(String label) {
        return symbolTable.containsKey(label);
    }

    public boolean isSymbolInDT(String label) {
        return definitionTable.containsKey(label);
    }

    public boolean isSymbolInUT(String label) {
        return useTable.containsKey(label);
    }

}
