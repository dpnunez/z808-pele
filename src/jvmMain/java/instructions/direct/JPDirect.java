package instructions.direct;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class JPDirect extends Instruction {
    public JPDirect() {
        super("JP.D", (byte) 0x79, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        short value = memory.getCell(op);
        Register regDestination = registers.getRegisterByName("IP");
        short valueSR = registers.getRegisterByName("SR").getValue();
        String valueInBinary = String.format("%16s", Integer.toBinaryString(valueSR)).replace(' ', '0');
        char bit = valueInBinary.charAt(6);
        if (bit == '0') {
            regDestination.setValue(value);
        }
    }
}