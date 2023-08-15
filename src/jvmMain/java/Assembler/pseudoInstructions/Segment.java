package Assembler.pseudoInstructions;

public class Segment extends PseudoInstruction{
    private Integer start;
    private Integer end;
    private String nameStart = getName().concat("SEGMENT");
    private String nameEnd = getName().concat("END");

    public Segment(String name) {
        super(name);
    }

    public Integer getStart() {
        return start;
    }
    public void setStart(Integer start) {
        this.start = start;
    }
    public Integer getEnd() {
        return end;
    }
    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getNameStart() {
        return nameStart;
    }

    public void setNameStart(String nameStart) {
        this.nameStart = nameStart;
    }

    public String getNameEnd() {
        return nameEnd;
    }

    public void setNameEnd(String nameEnd) {
        this.nameEnd = nameEnd;
    }
}
