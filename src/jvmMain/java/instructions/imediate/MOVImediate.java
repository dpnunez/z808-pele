package instructions.imediate;


import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class MOVImediate extends Instruction {

    public MOVImediate() {
        super("MOV-Imediate", (short) 0x02, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        // Memory will not be used in this instruction
        // op is a number
        Register regDestination = registers.getRegisterByName("AX");
        regDestination.setValue(op);
    }
}