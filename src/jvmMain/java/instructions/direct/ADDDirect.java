package instructions.direct;


import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class ADDDirect extends Instruction {

    public ADDDirect() {
        super("ADD.D", (byte) 0x05, 3);
    }

    public void execute(Registers registers, Memory memory, Short op) {
        Register regDestination = registers.getRegisterByName("AX");
        short value = memory.getCell(op);
        Register regFlag = registers.getFlagRegister();
        short flags = (short) 0x0000;

        regDestination.setValue((short)(regDestination.getValue() + value));

        // of & cf
        if (regDestination.getValue() < -16384 || regDestination.getValue() > 16384) {
            flags += 0x1000; //1*2^12 decimal = 4096
            flags += 0x01; //1*2^0 decimal = 1
        }
        // zf
        if (regDestination.getValue() == 0) {
            flags += 0x0100; //1*2^8 decimal = 256
        }
        // pf
        if (regDestination.getBitParity())  {
            flags += 0x0040; //1*2^6 decimal = 64
        }
        regFlag.setValue(flags);

    }
}
