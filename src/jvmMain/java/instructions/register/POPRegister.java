package instructions.register;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class POPRegister extends Instruction {

    public POPRegister() {
        super("POP.R", (byte) 0x58, 2);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regSource = registers.getRegisterByName("SP");
        Register regDestination = registers.getRegisterByOpcode(op);

        regDestination.setValue(regSource.getValue());
    }
}
