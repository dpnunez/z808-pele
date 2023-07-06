package instructions.imediate;

import instructions.Instruction;
import main.Memory;
import main.Registers;
import main.Register;

public class CMPImediate extends Instruction {

    public CMPImediate(){

        super("CMP-Imediate", (short)0x3c, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        Register regComp = registers.getRegisterByName("AX");
        short valueComp = regComp.getValue();
        regComp.setValue(op);
        Register regFlag = registers.getFlagRegister();

        boolean compResult = (valueComp == op);
        short flags = (short) 0x0000;

        //Quando a comparação for igual ZF = 1, se não ZF = 0

        if(compResult == true){ // colocando true por redundancia
            //setando flag ZF = 1
            flags += 0x0100;// 2^8 = 256

        }
        else
            //setando flag ZF = 0
            flags -= 0x100;//2^8 = 256

        regFlag.setValue(flags);
    }


}
