package main;

import instructions.Instruction;

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

    public void execute(String instruction) {
        // first 8 bits are opcode
        // next n bits are operand
        // n is determined by instruction

        Short opcode = Short.parseShort(instruction.substring(0, 8), 2);
        Short operand = Short.parseShort(instruction.substring(8), 2);
        Instruction i = instructions.getInstructionByOpcode(opcode);
        i.execute(registers, memory, operand);
    }
}
