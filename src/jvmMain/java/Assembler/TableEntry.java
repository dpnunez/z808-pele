package Assembler;

public class TableEntry {
    private final String label;
    private Short value;

    public TableEntry(String label, Short value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Short getValue() {
        return value;
    }

    public void setValue(Short address) {
        this.value = address;
    }
}
