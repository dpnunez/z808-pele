package main;

import instructions.Instruction;
import instructions.direct.*;
import instructions.register.*;
import instructions.imediate.*;

import java.util.HashMap;

public class Instructions {
    private final HashMap<Short, Instruction> instructions;

    public Instructions() {
        HashMap<Short, Instruction> i = new HashMap<>();
        // MOV
        Instruction MOVReg = new MOVRegister();
        i.put(MOVReg.getOpcode(), MOVReg);
        Instruction MOVIm = new MOVImediate();
        i.put(MOVIm.getOpcode(), MOVIm);
        Instruction MOVDirect = new MOVDirect();
        i.put(MOVDirect.getOpcode(), MOVDirect);
        // SUB
        Instruction SUBReg = new SUBRegister();
        i.put(SUBReg.getOpcode(), SUBReg);
        // OR
        Instruction ORReg = new ORRegister();
        i.put(ORReg.getOpcode(), ORReg);
        Instruction ORIm = new ORImediate();
        i.put(ORIm.getOpcode(), ORIm);
        Instruction ORDirect = new ORDirect();
        i.put(ORDirect.getOpcode(), ORDirect);
        // XOR
        Instruction XORReg = new XORRegister();
        i.put(XORReg.getOpcode(), XORReg);
        Instruction XORIm = new XORImediate();
        i.put(XORIm.getOpcode(), XORIm);
        Instruction XORDirect = new XORDirect();
        i.put(XORDirect.getOpcode(), XORDirect);

        this.instructions = i;
    }

    public Instruction getInstructionByOpcode(Short opcode) {
        return instructions.get(opcode);
    }

    public Instruction getInstructionByName(String name) {
        // ToDo: refactor to use get
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
