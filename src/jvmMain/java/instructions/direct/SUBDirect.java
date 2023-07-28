package instructions.direct;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class SUBDirect extends Instruction {
    public SUBDirect() {
        super("SUB.D", (short) 0x25, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        short valueSource = memory.getCell(op);
        Register regDestination = registers.getRegisterByName("AX");
        Register regFlag = registers.getFlagRegister();

        short result = (short) (regDestination.getValue() - valueSource);
        short flags = (short) 0x0000;

        regDestination.setValue(result);

        if (result < 0) {
            //setando a flag SF
            flags += 0x0200;
        }

        if (regDestination.getBitParity()) {
            //setando a flag PF
            flags += 0x0040;
        }

        if (result == 0) {
            //setando a flag ZF
            flags += 0x0100;
        }

        if (regDestination.getValue() < -16384 || regDestination.getValue() > 16384) {
            //setando a flag OF
            flags += 0x1000;
            //setando a flag CF
            flags += 0x01;
        }

        regFlag.setValue(flags);
    }
}
