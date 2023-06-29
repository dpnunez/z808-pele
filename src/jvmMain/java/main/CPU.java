package main;

public class CPU {
    private final Registers registers;
    private final Instructions instructions;

    public CPU(Registers r, Instructions i) {
        registers =  r;
        instructions = i;
    }

    public CPU() {
        registers = new Registers();
        instructions = new Instructions();
    }

    public Registers getRegisters() {
        return registers;
    }
    public Instructions getInstructions() {
        return instructions;
    }
}
