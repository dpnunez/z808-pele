package instructions.register;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class STORERegister extends Instruction {

    public STORERegister() {
        super("STORE.R", (short) 0x07, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regSource = registers.getRegisterByName("AX");
        memory.setCell(op, regSource.getValue());
    }
}
