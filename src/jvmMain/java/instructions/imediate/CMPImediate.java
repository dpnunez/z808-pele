package instructions.imediate;

import instructions.Instruction;
import main.Memory;
import main.Registers;
import main.Register;

public class CMPImediate extends Instruction {

    public CMPImediate(){ super("CMP-Imediate", (short)0x3c, 3);}
    public void execute(Registers registers, Memory memory, Short op) {
        //Op é id de um registrador a ser comparado com o registrador AX
        //Sempre sera usado o registrador AX
        Register regComp1 = registers.getRegisterByOpcode(op);
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
