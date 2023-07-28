package instructions.imediate;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class CALLImediate extends Instruction {
    public CALLImediate() {
        super("CALL-Imediate", (short) 0xE8, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regDestination = registers.getRegisterByName("SP");
        regDestination.setValue(op);
    }
}
