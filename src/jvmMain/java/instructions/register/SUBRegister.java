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
        Register regSource = registers.getRegisterByOpcode(op);
        Register regDestination = registers.getRegisterByName("AX");
        Register regFlag = registers.getFlagRegister();

        short valueSource = regSource.getValue();
        short valueDestination = regDestination.getValue();

        short result = (short) (valueDestination - valueSource);
        int resultForOverflow = (int) valueDestination - (int) valueSource;

        regDestination.setValue(result);

        if (result < 0) {
            //setando a flag SF
            // ToDo: verificar como identificar o carry e o borrow
            regFlag.setValue((short)(regFlag.getValue() | 0x01));
            // ToDo: conferir com o ferrugem o resultado de AX
        }

        if (regDestination.getBitParity()) {
            //setando a flag PF
            regFlag.setValue((short)(regFlag.getValue() | 0x40));
        }
        else {
            //setando a flag PF
            regFlag.setValue((short)(regFlag.getValue() & 0xFFBF));
        }

        if (result == 0) {
            //setando a flag ZF
            regFlag.setValue((short)(regFlag.getValue() | 0x100));
        }
        else {
            //setando a flag ZF
            regFlag.setValue((short)(regFlag.getValue() & 0xFEFF));
        }

        //ToDo: arrumar o overflow
       if (resultForOverflow >= 0) {
            if (resultForOverflow >= 65536) {
                //setando a flag OF
                regFlag.setValue((short) (regFlag.getValue() | 0x1000));
            } else {
                //setando a flag OF
                regFlag.setValue((short) (regFlag.getValue() & 0xEFFF));
            }
        }
       else {
            if (resultForOverflow < -65536) {
                //setando a flag OF
                regFlag.setValue((short) (regFlag.getValue() | 0x1000));
            } else {
                //setando a flag OF
                regFlag.setValue((short) (regFlag.getValue() & 0xEFFF));
            }
        }
    }
}