package Assembler.pseudoInstructions;

public class Equ extends PseudoInstruction{
    private String variable = null;

    public String getVariable() {
        return variable;
    }

    public Equ(String name, String variable) {
        super(name);
        this.variable = variable;
    }
}
