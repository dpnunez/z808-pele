package Assembler.pseudoInstructions;

public class ProcEndp extends PseudoInstruction {
    private Short start = null;
    private Short end = null;

    public ProcEndp(String name, Short address, Short start, Short end) {
        super(name, address);
        this.start = start;
        this.end = end;
    }

    public ProcEndp(String name, Short address, Short start) {
        super(name, address);
        this.start = start;
    }

    public Short getStart() {
        return start;
    }

    public void setStart(Short start) {
        this.start = start;
    }

    public Short getEnd() {
        return end;
    }

    public void setEnd(Short end) {
        this.end = end;
    }

    public void setValues(String mnemonic, Short PC) {
        if (mnemonic.equals("PROC")) {
            setStart(PC);
        } else {
            setEnd(PC);
        }
    }
}
