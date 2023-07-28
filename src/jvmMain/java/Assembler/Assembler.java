package Assembler;

import instructions.Instruction;
import main.Instructions;

import java.util.HashMap;
import java.util.Map;

public class Assembler {
    private final HashMap<String, Instruction> instructions;
//    private final HashMap<String, PseudoInstructions>
    private final SymbolTable table;

    public Assembler() {
        this.instructions = new HashMap<>();
        Instructions i = new Instructions();

        // Adicionar as intruções suportadas
        for (Map.Entry<Short,Instruction> instruction : i.getInstructions().entrySet()) {
            this.instructions.put(instruction.getValue().getName(), instruction.getValue());
        }

        // Adicionar as pseudo_instrucoes suportadas

        this.table = new SymbolTable();
    }

    public void run(String code) {
        String[] lines = code.split("\n");
        LineInterpreter lineInterpreter = new LineInterpreter();
        int LC = lines.length;
        short PC = 0;

        //Passo um: colocar labels em tabela de símbolos e tratamento de pseudocódigo
        for (String line : lines) {
            lineInterpreter.setLine(line);
            lineInterpreter.run();

            if(lineInterpreter.isCommentary()) {
                lineInterpreter.reset();
                continue;
            }

            String label = lineInterpreter.getLabel();
            String mnemonic = lineInterpreter.getMnemonic();
            String operand = lineInterpreter.getOperand();

            System.out.println("Label: " + label);
            System.out.println("Mnemonic: " + mnemonic);
            System.out.println("Operand: " + operand);

            if(!label.equals("")) {
                table.declareSymbol(label, PC);
            }

            boolean isPseudoInstruction = false;
            if (isPseudoInstruction) {
                // tratar pseudo instrução
            } else {
                // tratar instrução de máquina
                boolean isSupportedInstruction = instructions.containsKey(mnemonic);
                if (!isSupportedInstruction) {
                    System.out.println("Instruction not supported");
                    throw new RuntimeException("Instruction " + mnemonic + " not supported");
                } else {
                    Instruction instruction = instructions.get(mnemonic);
                    PC += instruction.getSize() * 8;
                }
            }

            lineInterpreter.reset();
        }

        // Printa tabela de símbolos
        for (Map.Entry<String, TableEntry> entry : table.getSymbolTable().entrySet()) {
            String key = entry.getKey();
            TableEntry value = entry.getValue();
            System.out.println("Key=" + key + ", Value=" + value.getValue());
        }
        PC = 0;

        /*
        //Passo dois: gerar arquivo em binário
        for (String line : lines) {
            lineInterpreter.setLine(line);
            lineInterpreter.run();

            String mnemonic = lineInterpreter.getMnemonic();
            String operand = lineInterpreter.getOperand();

            System.out.println("Mnemonic: " + mnemonic);
            System.out.println("Operand: " + operand);

            boolean isSupportedInstruction = instructions.containsKey(mnemonic);
            if (!isSupportedInstruction) {
                System.out.println("Instruction not supported");
                throw new RuntimeException("Instruction " + mnemonic + " not supported");
            } else {
                Instruction instruction = instructions.get(mnemonic);
                // escrever opcode no arquivo em binário

                // instrução com um operando
                if(instruction.getSize() > 1) {
                    if(instruction.getSize() == 2) {
                        // escrever registrador no arquivo
                    } else {
                        if(table.isSymbolInTable(operand)) {
                            // escrever valor da tabela no arquivo
                        } else {
                            // escrever operando no arquivo
                        }
                    }
                }

                PC += instruction.getSize() * 8;
            }

            lineInterpreter.reset();
        }
        */
    }
}
