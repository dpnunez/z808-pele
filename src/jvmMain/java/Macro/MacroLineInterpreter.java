package Macro;

import java.util.ArrayList;

public class MacroLineInterpreter {
    private String label;
    private String mnemonic;
    private final ArrayList<String> operands;
    private boolean commentary;


    public MacroLineInterpreter() {
        this.operands = new ArrayList<>();
        reset();
    }

    public void reset() {
        this.label = "";
        this.mnemonic = "";
        this.operands.clear();
        this.commentary = false;
    }

    public void run(String line) {
        reset();
        String[] tokens = line.split("\\s+");
        int count = tokens.length;

        if (line.trim().isEmpty()) {
            // blank line
            commentary = true;
            return;
        }

        if (tokens[0].charAt(0) == ';') {
            // line is commentary
            commentary = true;
            return;
        }

        boolean hasLabel = tokens[0].endsWith(":");
        if (hasLabel) {
            label = tokens[0].substring(0, tokens[0].length() - 1);

            if(tokens[1].charAt(0) == ';') return;
            mnemonic = tokens[1];
            if (count > 2) {
                if(tokens[2].charAt(0) == ';') return;

                for(int i = 2; i < tokens.length; i++) {
                    if(tokens[i].charAt(0) == ';') return;
                    operands.add(tokens[i]);
                }
            }
        } else {
            this.mnemonic = tokens[0];
            for(int i = 1; i < tokens.length; i++) {
                if(tokens[i].charAt(0) == ';') return;
                operands.add(tokens[i]);
            }
        }
    }

    public String getLabel() {
        return label;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public ArrayList<String> getOperands() {
        return operands;
    }

    public boolean hasOperand() {
        return !operands.isEmpty();
    }

    public boolean isCommentary() {
        return commentary;
    }
}