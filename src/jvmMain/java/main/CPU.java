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

    public void run(int endAddress) throws IOException {
        Register IP = registers.getRegisterByName("IP");
        IP.setValue((short) 0);

        while (IP.getValue() < endAddress) {
            Instruction i = instructions.getInstructionByOpcode((byte) memory.getCell(IP.getValue()));
            IP.setValue((short) (IP.getValue() + i.getSize()));
            short op;
            switch(i.getSize()) {
                case 1:
                    op = memory.getCell(IP.getValue() - 1);
                    i.execute(registers, memory, op);
                    System.out.println("Executing instruction: " + i.getName());
                    break;

                case 2:
                    op = memory.getCell(IP.getValue() - 1);
                    i.execute(registers, memory, op);
                    System.out.println("Executing instruction: " + i.getName() + " with operand: " + op);
                    break;

                case 3:
                    op = (short) (memory.getCell(IP.getValue() - 1) << 8);
                    op |= memory.getCell(IP.getValue() - 2);
                    i.execute(registers, memory, op);
                    System.out.println("Executing instruction: " + i.getName() + " with operand: " + op);
                    break;
            }
        }
    }
}