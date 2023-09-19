package instructions.direct;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class JNZDirect extends Instruction {
    public JNZDirect() {
        super("JNZ.D", (byte) 0x75, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        short value = memory.getCell(op);
        Register regDestination = registers.getRegisterByName("IP");
        short valueSR = registers.getRegisterByName("SR").getValue();
        String valueInBinary = String.format("%16s", Integer.toBinaryString(valueSR)).replace(' ', '0');
        char bit = valueInBinary.charAt(7);
        if (bit != '1') {
            regDestination.setValue(value);
        }
    }
}