package Macro;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class MacroProcessor {
    private int d; // definition-level counter
    private int e; // expansion-level counter
    private final Stack<MacroParameter> formalStack;
    private final Stack<MacroParameter> actualStack;
    private final Stack<Integer> macroCallStack;
    private final ArrayList<String> macroDefnTable;
    private final HashMap<String, Integer> macroNameTable;

    public MacroProcessor() {
        this.d = 0; // definition-level counter
        this.e = 0; // expansion-level counter
        this.formalStack = new Stack();
        this.actualStack = new Stack();
        this.formalStack.push(new MacroParameter("dummy", 0, 0));
        this.macroCallStack = new Stack();
        this.macroDefnTable = new ArrayList();
        this.macroNameTable = new HashMap();
    }

    public void run (File file) throws IOException {
        MacroLineInterpreter lineInterpreter = new MacroLineInterpreter();
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();

        FileWriter output = new FileWriter(file.getName());

        while (line != null) {
            lineInterpreter.run(line);
            String label = lineInterpreter.getLabel();
            String mnemonic = lineInterpreter.getMnemonic();
            ArrayList<String> operands = lineInterpreter.getOperands();

            switch (mnemonic) {
                case "MACRO":
                    d++;
                    if (e == 0) {
                        int i = 1;
                        for (String operand : operands) {
                            formalStack.push(new MacroParameter(operand,d, i));
                            line = line.replace(operand, formalStack.peek().getPair());
                            i++;
                        }
                    }

                    if (d == 1) {
                        macroDefnTable.add(line);
                        macroNameTable.put(label, macroDefnTable.size());
                    } else macroDefnTable.add(line);
                    break;

                case "MCEND":
                    if (d == 0) {
                        // in expansion mode
                        while (actualStack.peek() != null)
                            actualStack.pop();
                        actualStack.pop();
                        e--;
                        macroCallStack.pop();
                    } else {
                        // in definition mode
                        if (e == 0)
                            while (formalStack.peek().getLevel() == d)
                                formalStack.pop();
                        d--;
                        macroDefnTable.add(line);
                    }
                    break;

                default:
                    // macro name:
                    if (macroNameTable.containsKey(mnemonic)) {
                        if (d == 0) {
                            //expand call
                            e++;
                            ArrayList<String> actualArguments = (ArrayList<String>) operands.clone();
                            lineInterpreter.run(macroDefnTable.get(macroNameTable.get(mnemonic) - 1));
                            ArrayList<String> formalArguments = lineInterpreter.getOperands();
                            actualStack.push(null);
                            for (int i = 0; i < actualArguments.size(); i++) {
                                actualStack.push(new MacroParameter(actualArguments.get(i), formalArguments.get(i)));
                            }
                            macroCallStack.push(macroNameTable.get(mnemonic));
                        }

                        if(d > 0 && e == 0) {
                            for (String operand : operands) {
                                MacroParameter parameter = null;
                                for (int i = formalStack.size() - 1; i >= 0; i--) {
                                    parameter = formalStack.get(i);
                                    if (parameter.getName().equals(operand)) {
                                        line = line.replace(operand, parameter.getPair());
                                        break;
                                    }
                                }
                            }
                        }

                        if (d > 0)
                            macroDefnTable.add(line);
                    } else {
                        // other:
                        if (e == 0 && d > 0) {
                            for (String operand : operands) {
                                MacroParameter parameter = null;
                                for (int i = formalStack.size() - 1; i >= 0; i--) {
                                    parameter = formalStack.get(i);
                                    if (parameter.getName().equals(operand)) {
                                        line = line.replace(operand, parameter.getPair());
                                        break;
                                    }
                                }
                            }
                        }

                        if (!lineInterpreter.isCommentary())
                            if (d == 0)
                                output.write(line + "\n");
                            else
                                macroDefnTable.add(line);
                    }
            }

            if (e > 0) {
                // expansion mode
                // read line from current macro call
                line = macroDefnTable.get(macroCallStack.peek());
                Integer nextLine = macroCallStack.peek() + 1;
                macroCallStack.pop();
                macroCallStack.push(nextLine);

                lineInterpreter.run(line);

                if (lineInterpreter.hasOperand()) {
                    for (String operand : operands) {
                        MacroParameter parameter = null;
                        for (int i = actualStack.size() - 1; i >= 0; i--) {
                            parameter = actualStack.get(i);
                            if (parameter != null && parameter.getPair().equals(operand)) {
                                line = line.replace(operand, parameter.getName());
                                break;
                            }
                        }
                    }
                }
            } else {
                // read line from file
                if (sc.hasNextLine()) {
                    line = sc.nextLine();
                    lineInterpreter.run(line);
                } else line = null;
            }
        }

        for (int i = 0; i < macroDefnTable.size(); i++)
            System.out.println(macroDefnTable.get(i));

        output.close();
    }
}