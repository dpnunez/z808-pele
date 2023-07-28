package instructions.register;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class CMPRegister extends Instruction {

    public CMPRegister() {
        super("CMP.R", (short) 0x3B, 2);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regDestination = registers.getRegisterByName("AX");
        Register regFlag = registers.getFlagRegister();

        short valueSource = registers.getRegisterByOpcode(op).getValue();
        short valueDestination = regDestination.getValue();

        short flags = (short) 0x0000;

        // ZF
        if (valueSource == valueDestination) {
            flags += 0x0100; //1*2^8 decimal = 256
        }

        regFlag.setValue(flags);
    }
}
