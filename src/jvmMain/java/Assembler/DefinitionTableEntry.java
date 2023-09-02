package Assembler;

public class DefinitionTableEntry {
    private final String label;
    private final char relocatable;
    private Short value;

    public DefinitionTableEntry(String label, char relocatable, Short value) {
        this.label = label;
        this.relocatable = relocatable;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public char getRelocatable() { return relocatable; }

    public Short getValue() {
        return value;
    }

    public void setValue(Short address) {
        this.value = address;
    }
}
