package instructions.register;


import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class MOVRegister extends Instruction {

    public MOVRegister() {
        super("MOV.R", (short) 0xA0, 2);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        // Memory will not be used in this instruction
        // op is the operand
        // op is id for register
        Register regSource = registers.getRegisterByOpcode(op);
        Register regDestination = registers.getRegisterByName("AX");

        regDestination.setValue(regSource.getValue());
    }
}
