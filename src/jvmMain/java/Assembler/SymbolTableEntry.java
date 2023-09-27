package Assembler;

import java.util.ArrayList;

public class SymbolTableEntry {
    private final String label;
    private final char relocatable;
    private final char definition;
    private Short value;
    private ArrayList<Short> occurrences;

    public SymbolTableEntry(String label, char relocatable, char definition, Short value) {
        this.label = label;
        this.relocatable = relocatable;
        this.definition = definition;
        this.value = value;
        this.occurrences = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public char getRelocatable() { return relocatable; }

    public char getDefinition() { return definition; }

    public Short getValue() {
        return value;
    }

    public void setValue(Short address) {
        this.value = address;
    }

    public ArrayList<Short> getOccurrences() { return occurrences; }

    public void updateOccurrences(Short address) { occurrences.add(address); }
}
