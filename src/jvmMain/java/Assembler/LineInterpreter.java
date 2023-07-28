package Assembler;

public class LineInterpreter {
    private String line;
    private String label;
    private String mnemonic;
    private String operand;

    private boolean commentary;


    public LineInterpreter() {
        reset();
    }

    public void reset() {
        this.line = "";
        this.label = "";
        this.mnemonic = "";
        this.operand = "";
    }

    public void run() {
        String[] tokens = line.split("\\s+");
        int count = tokens.length;

       if (count == 0) {
           // blank line
           return;
       } else {
           boolean hasLabel = tokens[0].endsWith(":");
           if (hasLabel) {
               this.label = tokens[0].substring(0, tokens[0].length() - 1);
               this.mnemonic = tokens[1];
                if (count > 2) {
                     this.operand = tokens[2];
                }
           } else {
               this.mnemonic = tokens[0];
                if (count > 1) {
                     this.operand = tokens[1];
                }
           }
       }
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLabel() {
        return label;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public String getOperand() {
        return operand;
    }
}
