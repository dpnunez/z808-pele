package instructions;

import main.Registers;

public abstract class Instruction {
    private final Short opcode;
    private final String name;

    public Instruction(String name, Short opcode) {
        this.name = name;
        this.opcode = opcode;
    }
    public abstract void execute();

    public String getName() {
        return name;
    }

    public Short getOpcode() {
        return opcode;
    }
}
