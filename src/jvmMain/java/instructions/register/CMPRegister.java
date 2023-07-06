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
      Register regComp1 = registers.getRegisterByName("AX");
      Register regFlag = registers.getFlagRegister();

      short valueComp2 = registers.getRegisterByOpcode(op).getValue();
      short valueComp1 = regComp1.getValue();

      boolean result = (valueComp1 == valueComp2);
      short flags = (short) 0x0000;

      //Quando a comparação for igual ZF = 1, se não ZF = 0

      if(result == true){ // colocando true por redundancia
          //setando flag ZF = 1
          flags += 0x100;// 2^8 = 256

      }
      else //setando flag ZF = 0
          flags -= 0x100;// 2^8 = 256

     regFlag.setValue(flags);


    }
}
