package instructions.register;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class ORRegister extends Instruction {

    public ORRegister() {
        super("OR-Register", (short) 0x0B, 1);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regDestination = registers.getRegisterByName("AX");
        Register regFlag = registers.getFlagRegister();

        short valueSource = registers.getRegisterByOpcode(op).getValue();
        short valueDestination = regDestination.getValue();

        short result = (short) (valueDestination | valueSource);
        short flags = (short) 0x0000;

        regDestination.setValue(result);

        // flags CF,PF,ZF,SF,OF

        // SF
        if (result < 0) {
            flags += 0x0200; // decimal = 512
        }
        // ZF
        if (result == 0) {
            flags += 0x0200; // decimal = 512
        }

        regFlag.setValue(flags);
    }
}
