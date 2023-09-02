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
        this.commentary = false;
    }

    public void run() {
        String[] tokens = line.split("\\s+");
        int count = tokens.length;
        if (count == 0 || line.length() == 0) {
           // blank line
           commentary = true;
           return;
        }

        if (tokens[0].charAt(0) == ';') {
           //line is commentary
           this.commentary = true;
           return;
        }

        boolean hasLabel = tokens[0].endsWith(":");
        if (hasLabel) {
           this.label = tokens[0].substring(0, tokens[0].length() - 1);

           if(tokens[1].charAt(0) == ';') return;
           this.mnemonic = tokens[1];
           if (count > 2) {
               if(tokens[2].charAt(0) == ';') return;
               this.operand = tokens[2];
           }
        } else {
           this.mnemonic = tokens[0];
           if (count > 1) {
               if(tokens[1].charAt(0) == ';') return;
               this.operand = tokens[1];
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

    public boolean isCommentary() {
        return commentary;
    }
}
