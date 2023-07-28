package Assembler;

import instructions.Instruction;
import main.Instructions;

import java.util.HashMap;
import java.util.Map;

public class Assembler {
    private final HashMap<String, Instruction> instructions;
//    private final HashMap<String, PseudoInstructions>

    public Assembler() {
        this.instructions = new HashMap<>();
        Instructions i = new Instructions();

        // Adicionar as intruções suportadas
        for (Map.Entry<Short,Instruction> instruction : i.getInstructions().entrySet()) {
            this.instructions.put(instruction.getValue().getName(), instruction.getValue());
        }

        // Adicionar as pseudo_instrucoes suportadas
    }
    public void run(String code) {
        String[] lines = code.split("\n");
        int count = lines.length;
        LineInterpreter lineInterpreter = new LineInterpreter();

        // passar por todas as linhas e printar
        for (String line : lines) {
            lineInterpreter.setLine(line);
            lineInterpreter.run();

            String label = lineInterpreter.getLabel();
            String mnemonic = lineInterpreter.getMnemonic();
            String operand = lineInterpreter.getOperand();

            System.out.println("Label: " + label);
            System.out.println("Mnemonic: " + mnemonic);
            System.out.println("Operand: " + operand);

            boolean isPseudoInstruction = false;

            if (isPseudoInstruction) {
                // executar pseudo instrução
            } else {
                // executar instrução
                boolean isSupportedInstruction = instructions.containsKey(mnemonic);
                if (!isSupportedInstruction) {
                    System.out.println("Instruction not supported");
                    throw new RuntimeException("Instruction " + mnemonic + " not supported");
                }

                Instruction instruction = instructions.get(mnemonic);
            }

            lineInterpreter.reset();
        }
    }
}
