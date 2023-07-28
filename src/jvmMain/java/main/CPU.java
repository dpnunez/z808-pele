package main;

import instructions.Instruction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

        System.out.println("Executing instruction: " + i.getName() + " with operand: " + operand);
        i.execute(registers, memory, operand);
    }

    public void run(File file) throws FileNotFoundException {
        int pointer = 0;
        Scanner sc = new Scanner(file);
        StringBuilder code = new StringBuilder();

        // ToDo: refatorar para evitar os 2 loops
        while (sc.hasNextLine())
            code.append(sc.nextLine());
        while (pointer < code.length()) {
            String opcode = code.substring(pointer, pointer + 8);
            int instructionSize = instructions.getInstructionByOpcode(Short.parseShort(opcode, 2)).getSize() * 8; // Covert to bits
            String instruction = code.substring(pointer, pointer + instructionSize);
            execute(instruction);
            pointer += instructionSize;
        }
    }
}
