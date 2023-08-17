package Assembler.pseudoInstructions;

public class DW extends PseudoInstruction {
    private String variable = null;

    public DW(String name) {
        super(name);
    }
    public DW(String name, String var) {
        super(name);
        variable = var;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }
}
