package instructions.direct;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class CMPDirect extends Instruction {
    public CMPDirect() {
        super("CMP-Direct", (short) 0x3D, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        short value = memory.getCell(op);
        short valueSource = registers.getRegisterByName("AX").getValue();
        Register regFlag = registers.getFlagRegister();

        short flags = (short) 0x0000;
        // ZF
        if (value == valueSource) {
            flags += 0x0100; //1*2^8 decimal = 256
        }
        regFlag.setValue(flags);
    }

}
