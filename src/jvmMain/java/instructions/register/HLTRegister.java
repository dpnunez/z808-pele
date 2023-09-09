package instructions.register;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class HLTRegister extends Instruction {

    public HLTRegister() {
        super("HLT.R", (byte) 0xEE, 2);
    }
    public void execute(Registers registers, Memory memory, Short op) {

    }
}
