package Assembler.pseudoInstructions;

public class Segment extends PseudoInstruction {
    private short start;
    private short end;

    public Segment(String name) {
        super(name);
    }

    public short getStart() {
        return start;
    }

    public void setStart(short start) {
        this.start = start;
    }

    public short getEnd() {
        return end;
    }

    public void setEnd(short end) {
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