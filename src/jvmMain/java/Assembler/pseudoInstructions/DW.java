package Assembler.pseudoInstructions;

public class DW extends PseudoInstruction {
    private String variable = null;

    public DW(String name, short address) {
        super(name, address);
    }
    public DW(String name, String var, short address) {
        super(name, address);
        variable = var;
    }

    public String getVariable() {
        return variable;
    }
    public void setVariable(String variable) {
        this.variable = variable;
    }

}
