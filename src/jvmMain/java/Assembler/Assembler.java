package Assembler;

import Assembler.pseudoInstructions.DW;
import Assembler.pseudoInstructions.Equ;
import Assembler.pseudoInstructions.PseudoInstruction;
import Assembler.pseudoInstructions.Segment;
import instructions.Instruction;
import main.Instructions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Assembler {
    private final HashMap<String, Instruction> instructions;
//    private final HashMap<String, PseudoInstructions>
    private final PseudoInstructions pseudoInstructions = new PseudoInstructions();
    // list PseudoInstructions;
    private final SymbolTable table;

    public Assembler() {
        this.instructions = new HashMap<>();
        Instructions i = new Instructions();
        PseudoInstructions p = new PseudoInstructions();

        // Adicionar as intruções suportadas
        for (Map.Entry<Short,Instruction> instruction : i.getInstructions().entrySet()) {
            this.instructions.put(instruction.getValue().getName(), instruction.getValue());
        }

        // Adicionar as pseudo_instrucoes suportadas

        this.table = new SymbolTable();
    }

    public void run(String code, String fileName) {
        String[] lines = code.split("\n");
        LineInterpreter lineInterpreter = new LineInterpreter();
        //int LC = lines.length;
        short PC = 0;

        //Passo um: colocar labels em tabela de símbolos e tratamento de pseudocódigo
        for (String line : lines) {
            line = line.toUpperCase();
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

            if(!label.isEmpty()) {
                table.declareSymbol(label, PC);
            }

            if (pseudoInstructions.containsInstruction(mnemonic)) {
                // tratar pseudo instrução
                // label é o nome da PseudoInstrução
                // mnemonic o tipo ('SEGMENT', ...)
                PseudoInstruction pseudo = pseudoInstructions.getPseudoInstruction(label);
                if(pseudo instanceof Segment){
                    ((Segment) pseudo).setValues(mnemonic, PC);
                } else if (mnemonic.equals("DW")) {
                    if (pseudo == null){
                        pseudoInstructions.addListDWorDUP(label, operand, PC);
                    } else {
                        if (((DW)pseudo).getVariable() == null){
                            ((DW) pseudo).setVariable(operand);
                        } else {
                            System.out.println("DW: " + label + " = " + ((DW) pseudo).getVariable());
                        }
                    }
                } else if (mnemonic.equals("EQU")) {
                    if (pseudo == null && !operand.isEmpty()){
                        pseudoInstructions.addListEQU(label, operand);
                    }else{
                        System.out.println("Existing Pseudo-Instruction or invalid value.");
                        throw new RuntimeException(mnemonic + ": Existing Pseudo-Instruction or invalid value.");
                    }
                } else if (mnemonic.equals("ORG")) {
                    PC = pseudoInstructions.org(operand, PC);
                } else if (mnemonic.equals("ASSUME")) {
                    pseudoInstructions.assume(operand, PC);
                } else if (mnemonic.equals("PROC") || mnemonic.equals("ENDP")) {
                    pseudoInstructions.procEndp(label, PC);
                }

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

        try {
            // Abrir explorador de arquivos para escolher o nome e o local de onde salvar o arquivo

            //Passo um: criar arquivo
            FileWriter program = new FileWriter(fileName+".obj");


            //Passo dois: gerar arquivo em binário
            for (String line : lines) {
                line = line.toUpperCase();
                lineInterpreter.setLine(line);
                lineInterpreter.run();

                String label = lineInterpreter.getLabel();
                String mnemonic = lineInterpreter.getMnemonic();
                String operand = lineInterpreter.getOperand();

                System.out.println("Label: " + label);
                System.out.println("Mnemonic: " + mnemonic);
                System.out.println("Operand: " + operand);

                boolean isSupportedInstruction = instructions.containsKey(mnemonic);
                boolean isPseudoInstruction = pseudoInstructions.containsInstruction(mnemonic);
                if (!isSupportedInstruction) {
                    if (!isPseudoInstruction) {
                        System.out.println("Instruction not supported");
                        throw new RuntimeException("Instruction " + mnemonic + " not supported");
                    }
                } else{
                    Instruction instruction = instructions.get(mnemonic);
                    short opcode = instruction.getOpcode();
                    short operand1;
                    int instructionSize = instruction.getSize();
                    boolean org = false;

                    // teste para ver se não é um numero
                    if (!operand.matches("-?\\d+(\\.\\d+)?")) {
                        PseudoInstruction pseudo = pseudoInstructions.getPseudoInstruction(operand);
                        if (pseudo == null) {
                            System.out.println("Pseudo-Instruction doesn't exist");
                            throw new RuntimeException("Pseudo-Instruction doesn't exist: " + operand);
                        }
                        if (pseudo instanceof DW){
                            operand = ((DW) pseudo).getVariable();
                        }
                        if (pseudo instanceof Equ){
                            operand = ((Equ) pseudo).getVariable();
                        }
                        if (mnemonic.equals("ORG")){
                            org = true;
                            PC = pseudoInstructions.org(operand, PC);
                        }
                    }

                    // escrever opcode no arquivo em binário
                    program.write(Integer.toBinaryString((1 << 8) | opcode).substring( 1 ));

                    // instrução com um operando
                    if(instructionSize > 1) {
                        if(instructionSize == 2) {
                            // escrever registrador no arquivo
                            operand1 = Short.parseShort(operand);
                            program.write(Integer.toBinaryString((1 << 8) | operand1).substring( 1 ));
                        } else {
                            if(table.isSymbolInTable(operand)) {
                                // escrever valor da tabela no arquivo
                                operand1 = table.getTableEntry(operand).getValue();
                                program.write(Integer.toBinaryString((1 << 16) | operand1).substring( 1 ));
                            } else {
                                // escrever operando no arquivo
                                operand1 = Short.parseShort(operand);
                                program.write(Integer.toBinaryString((1 << 16) | operand1).substring( 1 ));
                            }
                        }
                    }
                    if (!org){
                        PC += instruction.getSize() * 8;
                    }
                }
                lineInterpreter.reset();
            }
            program.close();
        } catch (IOException ie) {
            System.out.println("An error occurred.");
        }
    }
}
