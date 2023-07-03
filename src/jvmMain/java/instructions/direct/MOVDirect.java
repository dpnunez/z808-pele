package instructions.direct;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class MOVDirect extends Instruction {

        public MOVDirect() {
            super("MOV-Direct", (short) 0x03, 3);
        }
        public void execute(Registers registers, Memory memory, Short op) {
            // op is a register id
            // value in register is a cell position in memory

            Register regPointer = registers.getRegisterByOpcode(op);
            int memoryIndex = regPointer.getValue();
            int value = memory.getCell(memoryIndex);
            Register regDestination = registers.getRegisterByName("AX");
            regDestination.setValue((short) value);
        }
}
