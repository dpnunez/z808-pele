package instructions.register;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class RETRegister extends Instruction {

    public RETRegister() {
        super("RET-Register", (short) 0xEF, 2);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regDestination = registers.getRegisterByName("IP");
        Register regSource = registers.getRegisterByName("SP");
        regDestination.setValue(regSource.getValue());
    }
}
