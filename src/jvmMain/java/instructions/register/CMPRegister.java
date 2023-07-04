package instructions.register;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class CMPRegister extends Instruction {

    public CMPRegister() {
        super("CMP-Register", (short) 0x3b, 2);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        //Os regs da op são hard coded
        Register regComp1 = registers.getRegisterByName("DX");
        Register regComp2 = registers.getRegisterByName("AX");
        Register regFlag = registers.getFlagRegister();

        short valueRegComp1 = regComp1.getValue();
        short valueRegComp2 = regComp2.getValue();
        //Quando a comparação for igual ZF = 1, se não ZF = 0

        if(valueRegComp1 == valueRegComp2){
            //setando flag ZF = 1
            regFlag.setValue((short)(regFlag.getValue() | 0x0100));

        }
        else
            //setando flag ZF = 0
            regFlag.setValue((short)(regFlag.getValue() & 0xFEFF));


    }
}
