package instructions.register;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class ORRegister extends Instruction {

    public ORRegister() {
        super("OR-Register", (short) 0x0B, 1);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regDestination = registers.getRegisterByName("AX");
        Register regFlag = registers.getFlagRegister();

        short valueSource = registers.getRegisterByOpcode(op).getValue();
        short valueDestination = regDestination.getValue();

        int result = valueDestination | valueSource;
        short flags = (short) 0x0000;

        regDestination.setValue((short) result);

        // OF
        if ((result - (short) result) != 0) {
            flags += 0x1000; //1*2^12 decimal = 4096 OVERFLOW
            flags += 0x0001; //1*2^0 decimal = 1 se deu OV é pq vai-um
        }
        // SF
        if ((short)result < 0) {
            flags += 0x0200; //1*2^9 decimal = 512
        }
        // ZF
        if ((short)result == 0) {
            flags += 0x0100; //1*2^8 decimal = 256
        }
        // PF
        if (regDestination.getBitParity())  {
            flags += 0x0040; //1*2^6 decimal = 64
        }
        // CF
        if ((short)result == 0) {
            flags += 0x0001; //1*2^0 decimal = 1
        }

        regFlag.setValue(flags);
    }
}
