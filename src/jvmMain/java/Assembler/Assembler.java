package Assembler;

import Assembler.pseudoInstructions.DW;
import Assembler.pseudoInstructions.Equ;
import Assembler.pseudoInstructions.PseudoInstruction;
import Assembler.pseudoInstructions.Segment;
import instructions.Instruction;
import main.Instructions;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Assembler {
    private final HashMap<String, Instruction> instructions;
    //    private final HashMap<String, PseudoInstructions>
    private final PseudoInstructions pseudoInstructions;
    // list PseudoInstructions;
    private final LinkerDirectives linkerDirectives;
    private Tables tables;

    public Assembler() {
        this.instructions = new HashMap<>();
        Instructions i = new Instructions();
        // Adicionar as intruções suportadas
        for (Map.Entry<Byte, Instruction> instruction : i.getInstructions().entrySet()) {
            this.instructions.put(instruction.getValue().getName(), instruction.getValue());
        }
        // Adicionar as pseudo_instrucoes suportadas
        this.pseudoInstructions = new PseudoInstructions();
        // Adicionar as diretivas de linkagem suportadas
        this.linkerDirectives = new LinkerDirectives();
    }

    public Tables run(String code, String fileName) {
        this.tables = new Tables();
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
            char relocationValue = 'r';
            char signalValue = '+';

            if (!label.isEmpty()) {
                if(mnemonic.equals("EQU")) {
                    relocationValue = 'a';
                }

                tables.newSymbolTableEntry(label, relocationValue, 'd', PC);

                if(tables.isSymbolInDT(label)) {
                    tables.newDefinitionTableEntry(label, relocationValue, PC);
                }
            }

            if (linkerDirectives.isLinkerDirective(mnemonic)) {
                if (mnemonic.equals("PUBLIC")) {
                    tables.declareSymbolTableEntry(operand);
                    tables.declareDefinitionTableEntry(operand);
                    continue;
                } else {
                    String extrnSignal[] = operand.split(":");
                    if(extrnSignal[1].equals("ABS"))
                        signalValue = '=';

                    tables.newSymbolTableEntry(extrnSignal[0], 'r', 'e', (short) 0);
                    tables.newUseTableEntry(extrnSignal[0], signalValue);
                    continue;
                }
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
                    PC += instruction.getSize();
                }
            }

            lineInterpreter.reset();
        }

        // Printa tabela de símbolos
        System.out.println("Tabela de simbolos:");
        for (Map.Entry<String, SymbolTableEntry> entry : tables.getSymbolTable().entrySet()) {
            String key = entry.getKey();
            if(entry.getValue() != null) {
                SymbolTableEntry value = entry.getValue();
                System.out.println("Key=" + key + ", Value=" + value.getValue());
            } else System.out.println("Key=" + key);
        }

        // Printa tabela de definição
        System.out.println("Tabela de definicao:");
        for (String key : tables.getDefinitionTable().keySet()) {
            System.out.println("Key=" + key);
        }

        // Printa tabela de uso
        System.out.println("Tabela de uso:");
        for (String key : tables.getUseTable().keySet()) {
            System.out.println("Key=" + key);
        }

        PC = 0;

        try {
            // Abrir explorador de arquivos para escolher o nome e o local de onde salvar o arquivo

            //Passo um: criar arquivo
            OutputStream output = new FileOutputStream(fileName + ".obj");


            //Passo dois: gerar arquivo em binário
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

                boolean isSupportedInstruction = instructions.containsKey(mnemonic);
                boolean isPseudoInstruction = pseudoInstructions.containsInstruction(mnemonic);
                if (!isSupportedInstruction) {
                    if (!isPseudoInstruction) {
                        //System.out.println("Instruction not supported");
                        //throw new RuntimeException("Instruction " + mnemonic + " not supported");
                    }
                } else{
                    Instruction instruction = instructions.get(mnemonic);
                    byte opcode = instruction.getOpcode();
                    byte bytesToWrite[] = new byte[2];
                    int instructionSize = instruction.getSize();
                    short operandToWrite;
                    boolean org = false;

                    // teste para ver se não é um numero
                    if (!operand.matches("-?\\d+(\\.\\d+)?")) {
                        PseudoInstruction pseudo = pseudoInstructions.getPseudoInstruction(operand);
                        if (pseudo == null) {
                            //System.out.println("Pseudo-Instruction doesn't exist");
                            //throw new RuntimeException("Pseudo-Instruction doesn't exist: " + operand);
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
                    output.write(opcode);


                    // instrução com um operando
                    if(instructionSize > 1) {
                        if(instructionSize == 2) {
                            // escrever registrador no arquivo
                            output.write(Byte.parseByte(operand));
                        } else {
                            if(tables.isSymbolInST(operand)) {
                                tables.newSymbolTableOccurrence(operand, (short) (PC + 1));
                                if(tables.isSymbolInUT(operand))
                                    tables.newUseTableEntryOccurrence(operand, (short) (PC + 1));
                                // escrever valor da tabela no arquivo
                                operandToWrite = tables.getSymbolTableEntry(operand).getValue();
                                bytesToWrite[0] = (byte) (operandToWrite & 0xff);
                                bytesToWrite[1] = (byte) ((operandToWrite >> 8) & 0xff);
                                output.write(bytesToWrite);
                            } else {
                                // escrever operando no arquivo
                                operandToWrite = Short.parseShort(operand);
                                bytesToWrite[0] = (byte) (operandToWrite & 0xff);
                                bytesToWrite[1] = (byte) ((operandToWrite >> 8) & 0xff);
                                output.write(bytesToWrite);
                            }
                        }
                    }
                    if (!org){
                        PC += instruction.getSize();
                    }
                }
                lineInterpreter.reset();
            }

            // Printa tabela de símbolos
            System.out.println("Tabela de simbolos:");
            for (Map.Entry<String, SymbolTableEntry> entry : tables.getSymbolTable().entrySet()) {
                String key = entry.getKey();
                if(entry.getValue() != null) {
                    SymbolTableEntry value = entry.getValue();
                    System.out.println("Key=" + key + ", Value=" + value.getValue());
                } else System.out.println("Key=" + key);
            }

            // Printa tabela de definição
            for (Map.Entry<String, DefinitionTableEntry> entry : tables.getDefinitionTable().entrySet()) {
                String key = entry.getKey();
                if(entry.getValue() != null) {
                    DefinitionTableEntry value = entry.getValue();
                    System.out.println("Key=" + key + ", Value=" + value.getValue());
                } else System.out.println("Key=" + key);
            }

            for (Map.Entry<String, UseTableEntry> entry : tables.getUseTable().entrySet()) {
                String key = entry.getKey();
                if(entry.getValue() != null) {
                    UseTableEntry value = entry.getValue();
                    ArrayList<Short> occurrences = value.getOccurrences();
                    System.out.println("Signal: " + value.getSignal());
                    for(int i = 0; i < occurrences.size(); i++)
                        System.out.println(occurrences.get(i));
                }
            }

            output.close();
        } catch (IOException ie) {
            System.out.println("An error occurred.");
        }

        return tables;
    }
}