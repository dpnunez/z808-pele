package Assembler;

import java.util.HashMap;

public class SymbolTable {
    private final HashMap<String, TableEntry> symbolTable;

    public SymbolTable() {
        this.symbolTable = new HashMap<>();
    }

    public HashMap<String, TableEntry> getSymbolTable() {
        return symbolTable;
    }

    public TableEntry getTableEntry(String label) {
        return symbolTable.get(label);
    }

    public void newSymbol(String label) {
        symbolTable.put(label, null);
    }

    public void declareSymbol(String label, Short address) {
        symbolTable.put(label, new TableEntry(label, address));
    }

    public boolean isSymbolInTable(String label) {
        return symbolTable.containsKey(label);
    }
}
