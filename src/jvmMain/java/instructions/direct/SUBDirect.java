package instructions.direct;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class SUBDirect extends Instruction {
    public SUBDirect() {
        super("SUB-Direct", (short) 0x25, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        int memoryIndex = registers.getRegisterByOpcode(op).getValue();
        short valueSource = memory.getCell(memoryIndex);
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

        //ToDo: como fazer o CF e o OF

        regFlag.setValue(flags);
    }
}
