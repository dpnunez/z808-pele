package main;

import instructions.Instruction;
import java.util.HashMap;

public class Instructions {
    private final HashMap<Short, Instruction> instructions;

    public Instructions() {
        HashMap<Short, Instruction> i = new HashMap<>();
        Instruction Mov = new instructions.Mov();
        i.put(Mov.getOpcode(), Mov);

        this.instructions = i;
    }

    public Instruction getInstructionByOpcode(Short opcode) {
        return instructions.get(opcode);
    }

    public Instruction getInstructionByName(String name) {
        for (HashMap.Entry<Short, Instruction> entry : instructions.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String result = "";
        for (HashMap.Entry<Short, Instruction> entry : instructions.entrySet()) {
            result += entry.getValue().getName() + ": " + entry.getValue().getOpcode() + "\n";
        }
        return result;
    }
}
