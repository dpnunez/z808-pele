package instructions.imediate;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class POPImediate extends Instruction {
    public POPImediate() {
        super("POP.I", (byte) 0x5A, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regSource = registers.getRegisterByName("SP");
        memory.setCell(op, regSource.getValue());
    }
}
