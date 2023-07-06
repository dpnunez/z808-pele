package instructions.direct;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class CMPDirect extends Instruction {

    public CMPDirect() {

        super("CMP-Direct", (short) 0x3d, 3);
    }
    public void execute(Registers registers, Memory memory, Short op) {
        //Op é id de um registrador a ser comparado com o registrador AX
        //Sempre sera usado o registrador AX
        short valueComp = memory.getCell(op);
        Register regValueComp = registers.getRegisterByName("AX");
        Register regFlag = registers.getFlagRegister();

        boolean compResult = (regValueComp.getValue() == valueComp);
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
