package instructions.imediate;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class ANDImediate extends Instruction {
    public ANDImediate() {
        super("AND.I", (short) 0x24, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regDestination = registers.getRegisterByName("AX");
        short valueSource = regDestination.getValue();
        regDestination.setValue(op);
        Register regFlag = registers.getFlagRegister();

        short result = (short) (op & valueSource);
        short flags = (short) 0x0000;

        regDestination.setValue(result);

        // SF
        if (result < 0) {
            flags += 0x0200; //1*2^9 decimal = 512
        }
        // ZF
        if (result == 0) {
            flags += 0x0100; //1*2^8 decimal = 256
        }
        // PF
        if (regDestination.getBitParity())  {
            flags += 0x0040; //1*2^6 decimal = 64
        }
        regFlag.setValue(flags);
    }
}
