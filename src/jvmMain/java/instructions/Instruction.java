package instructions;

import main.Memory;
import main.Registers;

public abstract class Instruction {
    private final Short opcode;
    private final String name;

    private final int size;

    public Instruction(String name, Short opcode, int size) {
        this.name = name;
        this.opcode = opcode;
        this.size = size;
    }
    public abstract void execute(Registers registers, Memory memory, Short op);

    public String getName() {
        return name;
    }

    public Short getOpcode() {
        return opcode;
    }

    public int getSize() {
        return size;
    }
}
