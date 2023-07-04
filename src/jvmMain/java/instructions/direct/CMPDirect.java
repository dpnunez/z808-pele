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
        Register regPointer1 = registers.getRegisterByOpcode(op);
        Register regPointer2 = registers.getRegisterByName("AX");
        Register regFlag = registers.getFlagRegister();

        int memoryIndex1 = regPointer1.getValue();//Pegando End. de memoria do Registrador com id armazenado em "OP"
        int memoryIndex2 = regPointer2.getValue();//Pegando End. de memoria do Registrador com id "AX"
        int valueComp1 = memory.getCell(memoryIndex1);
        int valueComp2 = memory.getCell(memoryIndex2);
        //Pegando conteudo dos dois espaços de memoria

        //Quando a comparação for igual ZF = 1, se não ZF = 0

        if(valueComp1 == valueComp2){
            //setando flag ZF = 1
            regFlag.setValue((short)(regFlag.getValue() | 0x0100));

        }
        else
            //setando flag ZF = 0
            regFlag.setValue((short)(regFlag.getValue() & 0xFEFF));


    }
}
