package instructions.register;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class PUSHRegister extends Instruction {

    public PUSHRegister() {
        super("PUSH.R", (byte) 0x50, 2);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regDestination = registers.getRegisterByName("SP");
        Register regSource = registers.getRegisterByOpcode(op);

        regDestination.setValue(regSource.getValue());
    }
}
