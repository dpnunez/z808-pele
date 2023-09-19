package instructions.register;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class POPFRegister extends Instruction {

    public POPFRegister() {
        super("POPF.R", (byte) 0x9D, 2);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regDestination = registers.getRegisterByName("SR");
        Register regSource = registers.getRegisterByName("SP");
        regDestination.setValue(regSource.getValue());
    }
}
