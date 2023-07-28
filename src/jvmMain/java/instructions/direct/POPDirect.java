package instructions.direct;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class POPDirect extends Instruction {
    public POPDirect() {
        super("POP.D", (short) 0x59, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        short value = memory.getCell(op);
        Register regSource = registers.getRegisterByName("SP");
        memory.setCell(value, regSource.getValue());
    }

}
