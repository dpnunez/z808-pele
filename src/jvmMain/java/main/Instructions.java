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
        // AND
        Instruction ANDReg = new ANDRegister();
        i.put(ANDReg.getOpcode(), ANDReg);
        Instruction ANDIm = new ANDImediate();
        i.put(ANDIm.getOpcode(), ANDIm);
        Instruction ANDDirect = new ANDDirect();
        i.put(ANDDirect.getOpcode(), ANDDirect);
        // CMP
        Instruction CMPReg = new CMPRegister();
        i.put(CMPReg.getOpcode(), CMPReg);
        Instruction CMPIm = new CMPImediate();
        i.put(CMPIm.getOpcode(), CMPIm);
        Instruction CMPDirect = new CMPDirect();
        i.put(CMPDirect.getOpcode(), CMPDirect);
        // JMP
        Instruction JMPDirect = new JMPDirect();
        i.put(JMPDirect.getOpcode(), JMPDirect);
        Instruction JMPIm = new JMPImediate();
        i.put(JMPIm.getOpcode(), JMPIm);
        // JZ
        Instruction JZDirect = new JZDirect();
        i.put(JZDirect.getOpcode(), JZDirect);
        // JNZ
        Instruction JNZDirect = new JNZDirect();
        i.put(JNZDirect.getOpcode(), JNZDirect);
        // JP
        Instruction JPDirect = new JPDirect();
        i.put(JPDirect.getOpcode(), JPDirect);
        // CALL
        Instruction CALLDirect = new CALLDirect();
        i.put(CALLDirect.getOpcode(), CALLDirect);
        Instruction CALLIm = new CALLImediate();
        i.put(CALLIm.getOpcode(), CALLIm);
        // POP
        Instruction POPReg = new POPRegister();
        i.put(POPReg.getOpcode(), POPReg);
        Instruction POPDirect = new POPDirect();
        i.put(POPDirect.getOpcode(), POPDirect);
        Instruction POPIm = new POPImediate();
        i.put(POPIm.getOpcode(), POPIm);
        // POPF
        Instruction POPFReg = new POPFRegister();
        i.put(POPFReg.getOpcode(), POPFReg);
        // PUSH
        Instruction PUSHReg = new PUSHRegister();
        i.put(PUSHReg.getOpcode(), PUSHReg);
        // PUSHF
        Instruction PUSHFReg = new PUSHFRegister();
        i.put(PUSHFReg.getOpcode(), PUSHFReg);
        // RET
        Instruction RETReg = new RETRegister();
        i.put(RETReg.getOpcode(), RETReg);
        // HLT
        Instruction HLTReg = new HLTRegister();
        i.put(HLTReg.getOpcode(), HLTReg);



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
