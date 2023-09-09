package instructions.register;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class PUSHFRegister extends Instruction {

    public PUSHFRegister() {
        super("PUSHF.R", (byte) 0x9C, 2);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regDestination = registers.getRegisterByName("SP");
        Register regSource = registers.getRegisterByName("SR");
        regDestination.setValue(regSource.getValue());
    }
}
