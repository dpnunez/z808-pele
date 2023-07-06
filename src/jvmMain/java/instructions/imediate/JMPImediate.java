package instructions.imediate;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class JMPImediate extends Instruction{

    public JMPImediate() { super("JMP-Imediate", (short) 0xeb, 3); }

    public void execute(Registers registers, Memory memory, Short op) {
        // Memory will not be used in this instruction
        // op is the operand
        // op is a constant
        // flag register is not modified in this instruction

        Register regDestination = registers.getRegisterByName("IP");

        if(op % 2 == 0) {
            regDestination.setValue(op);
        } else System.out.println("ERROR: Invalid address.\n");
    }
}
