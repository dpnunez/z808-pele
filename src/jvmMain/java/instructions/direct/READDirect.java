package instructions.direct;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class READDirect extends Instruction {
    public READDirect() {
        super("READ.D", (byte) 0x12, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        short value = memory.getCell(op);
        Register regSource = registers.getRegisterByName("SP");
        memory.setCell(value, regSource.getValue());
    }

}
