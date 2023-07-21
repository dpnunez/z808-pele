package instructions.register;


import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;


public class DIVRegister extends Instruction {

    public DIVRegister() { super("DIV-Register", (short) 0xf7, 2); }

    public void execute(Registers registers, Memory memory, Short op) {
        // Memory will not be used in this instruction
        // op is the operand
        // op is id for register

        Register regDestination = registers.getRegisterByName("AX");
        Register regFlag = registers.getFlagRegister();

        short divisor;

        // Check if operand is register 'SI'.
        if(op == (short) 0xf6) {
            divisor = memory.getCell(registers.getRegisterByName("SI").getValue());
        } else divisor = registers.getRegisterByOpcode(op).getValue();

        short dividend = regDestination.getValue();
        short flags = regFlag.getValue();

        if(divisor == 0) {
            System.out.println("ERROR: Can't divide by zero.\n");
            return;
        }

        short divResult = (short) (dividend / divisor);
        short modResult = (short) (dividend % divisor);
        regDestination.setValue(divResult);
        registers.getRegisterByName("DX").setValue(modResult);

        // SF
        if(divResult < 0) {
            flags += 0x2000;  //1*2^9 decimal = 512
        }

        // ZF
        if(divResult == 0) {
            flags += 0x0100;  //1*2^8 decimal = 256
        }

        // PF
        if(regDestination.getBitParity()) {
            flags += 0x0040;   //1*2^6 decimal = 64
        }

        regFlag.setValue(flags);
    }
}
