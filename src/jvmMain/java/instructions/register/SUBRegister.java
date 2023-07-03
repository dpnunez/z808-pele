package instructions.register;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class SUBRegister extends Instruction {

    public SUBRegister() {
        super("SUB-Register", (short) 0x2B, 2);
    }
    public void execute(Registers registers, Memory memory, Short op) {

    }
}
