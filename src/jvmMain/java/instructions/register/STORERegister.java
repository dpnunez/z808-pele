package instructions.register;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class STORERegister extends Instruction { // COMOOO

    public STORERegister() {
        super("STORE-Register", (short) 0x07, 2);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regSource = registers.getRegisterByOpcode(op);

    }
}
