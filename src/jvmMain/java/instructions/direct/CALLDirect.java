package instructions.direct;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class CALLDirect extends Instruction {
    public CALLDirect() {
        super("CALL-Direct", (short) 0xE9, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        short value = memory.getCell(op);
        Register regDestination = registers.getRegisterByName("SP");
        regDestination.setValue(value);
    }
}