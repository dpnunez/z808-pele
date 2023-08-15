package Assembler.pseudoInstructions;

public abstract class PseudoInstruction {
    private String name;

    public PseudoInstruction(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
