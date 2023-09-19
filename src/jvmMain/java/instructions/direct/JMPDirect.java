package instructions.direct;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class JMPDirect extends Instruction {
    public JMPDirect() {
        super("JMP.D", (byte) 0xEB, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        short value = memory.getCell(op);
        Register regDestination = registers.getRegisterByName("IP");
        regDestination.setValue(value);
    }

}
