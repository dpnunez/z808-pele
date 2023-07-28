package instructions.register;


import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class NOTRegister extends Instruction {

    public NOTRegister() { super("NOT.R", (short) 0xF8, 2); }

    public void execute(Registers registers, Memory memory, Short op) {
        // Memory will not be used in this instruction
        // op is the operand
        // op is id for register

        Register regDestination = registers.getRegisterByOpcode(op);
        Register regFlag = registers.getFlagRegister();

        short regValue = regDestination.getValue();
        short flags = (short) 0x0000;

        regValue ^= 0xffff;  // Can't use '!' operand on short variable, so I used the XOR operand
        regDestination.setValue(regValue);

        // SF
        if(regValue < 0) {
            flags += 0x2000;  //1*2^9 decimal = 512
        }

        // ZF
        if(regValue == 0) {
            flags += 0x0100;  //1*2^8 decimal = 256
        }

        // PF
        if(regDestination.getBitParity()) {
            flags += 0x0040;   //1*2^6 decimal = 64
        }

        regFlag.setValue(flags);
    }
}
