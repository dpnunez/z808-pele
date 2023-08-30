package Assembler.pseudoInstructions;

public abstract class PseudoInstruction {
    private String name;
    private Short address;

    public PseudoInstruction(String name, Short address) {
        this.name = name;
        this.address = address;
    }

    public PseudoInstruction(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Short getAddress() {
        return address;
    }
    public void setAddress(Short address) {
        this.address = address;
    }
}
