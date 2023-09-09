package instructions.imediate;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class CMPImediate extends Instruction {
    public CMPImediate() {
        super("CMP.I", (byte) 0x3C, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regDestination = registers.getRegisterByName("AX");
        short valueSource = regDestination.getValue();
        Register regFlag = registers.getFlagRegister();

        short flags = (short) 0x0000;
        // ZF
        if (valueSource == op) {
            flags += 0x0100; //1*2^8 decimal = 256
        }
        regFlag.setValue(flags);
    }
}
