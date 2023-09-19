package main;

import instructions.Instruction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CPU {
    private final Registers registers;
    private final Instructions instructions;

    private final Memory memory;

    public CPU(Registers r, Instructions i, Memory m) {
        registers =  r;
        instructions = i;
        memory = m;
    }

    public CPU(Memory m){
        registers = new Registers();
        instructions = new Instructions();
        memory = m;
    }

    public Registers getRegisters() {
        return registers;
    }
    public Instructions getInstructions() {
        return instructions;
    }

    public void run(File file) throws IOException {
        FileInputStream code = new FileInputStream(file);
        byte[] codeBytes = code.readAllBytes();
        Register IP = registers.getRegisterByName("IP");
        IP.setValue((short) 0);

        while (IP.getValue() < codeBytes.length) {
            Instruction i = instructions.getInstructionByOpcode(codeBytes[IP.getValue()]);
            IP.setValue((short) (IP.getValue() + i.getSize()));
            switch(i.getSize()) {
                case 1:
                    short op1 = (short) codeBytes[IP.getValue() - 1];
                    i.execute(registers, memory, op1);
                    System.out.println("Executing instruction: " + i.getName());
                    break;

                case 2:
                    short op2 = (short) codeBytes[IP.getValue() - 1];
                    i.execute(registers, memory, op2);
                    System.out.println("Executing instruction: " + i.getName() + " with operand: " + codeBytes[IP.getValue() - 1]);
                    break;

                case 3:
                    short op3 = (short) ((codeBytes[IP.getValue() - 1] << 8) | codeBytes[IP.getValue() - 2]);
                    i.execute(registers, memory, op3);
                    System.out.println("Executing instruction: " + i.getName() + " with operand: " + op3);
                    break;
            }
        }
    }
}