package instructions.imediate;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class SUBImediate extends Instruction {
    public SUBImediate() {
        super("SUB-Imediate", (short) 0x24, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {

    }
}
