package Assembler.pseudoInstructions;

public class Segment extends PseudoInstruction {
    private Short start = null;
    private Short end = null;

    public Segment(String name) {
        super(name);
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

    public void setValues(String mnemonic,Short PC){
        if (mnemonic.equals("SEGMENT")){
            setStart(PC);
        } else {
            setEnd(PC);
        }
    }
}