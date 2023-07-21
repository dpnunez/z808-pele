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

        Instruction MOVReg = new MOVRegister();
        i.put(MOVReg.getOpcode(), MOVReg);
        Instruction MOVIm = new MOVImediate();
        i.put(MOVIm.getOpcode(), MOVIm);
        Instruction MOVDirect = new MOVDirect();
        i.put(MOVDirect.getOpcode(), MOVDirect);

        Instruction SUBReg = new SUBRegister();
        i.put(SUBReg.getOpcode(), SUBReg);
        Instruction ORReg = new ORRegister();
        i.put(ORReg.getOpcode(), ORReg);
        Instruction ORIm = new ORImediate();
        i.put(ORIm.getOpcode(), ORIm);
        Instruction ORDirect = new ORDirect();
        i.put(ORDirect.getOpcode(), ORDirect);
        Instruction ADDReg = new ADDRegister();
        i.put(ADDReg.getOpcode(), ADDReg);
        Instruction ADDIm = new ADDImediate();
        i.put(ADDIm.getOpcode(), ADDIm);
        Instruction ADDDirect = new ADDDirect();
        i.put(ADDDirect.getOpcode(), ADDDirect);

        Instruction DIVReg = new DIVRegister();
        i.put(DIVReg.getOpcode(), DIVReg);

        Instruction NOTReg = new NOTRegister();
        i.put(NOTReg.getOpcode(), NOTReg);

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
