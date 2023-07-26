package instructions.direct;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class MOVDirect extends Instruction {

        public MOVDirect() {
            super("MOV-Direct", (short) 0xA1, 3); }
        public void execute(Registers registers, Memory memory, Short op) {
            // op is a register id
            // value in register is a cell position in memory

            int value = memory.getCell(op);
            Register regDestination = registers.getRegisterByName("AX");
            regDestination.setValue((short) value);
        }
}
