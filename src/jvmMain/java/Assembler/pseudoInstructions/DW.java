package Assembler.pseudoInstructions;

public class DW extends PseudoInstruction {
    private Short variavel = null;

    public DW(String name) {
        super(name);
    }

    public Short getVariavel() {
        return variavel;
    }

    public void setVariavel(Short variavel) {
        this.variavel = variavel;
    }
}
