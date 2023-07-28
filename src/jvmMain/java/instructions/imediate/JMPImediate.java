package instructions.imediate;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class JMPImediate extends Instruction {
    public JMPImediate() {
        super("JMP.I", (short) 0xEA, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regDestination = registers.getRegisterByName("IP");
        regDestination.setValue(op);
    }
}
