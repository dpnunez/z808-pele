package Assembler.pseudoInstructions;

public class DW extends PseudoInstruction {
    private String variavel = null;

    public DW(String name) {
        super(name);
    }
    public DW(String name, String var) {
        super(name);
        variavel = var;
    }

    public String getVariavel() {
        return variavel;
    }

    public void setVariavel(String variavel) {
        this.variavel = variavel;
    }
}
