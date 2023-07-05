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

    }
}
