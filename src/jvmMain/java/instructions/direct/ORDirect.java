package instructions.direct;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class ORDirect extends Instruction {
    public ORDirect() {
        super("OR.D", (short) 0x0D, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        short value = memory.getCell(op);
        Register regDestination = registers.getRegisterByName("AX");
        Register regFlag = registers.getFlagRegister();

        short result = (short) (regDestination.getValue() | value);
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
