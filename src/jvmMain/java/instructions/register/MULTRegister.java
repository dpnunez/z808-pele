package instructions.register;

import instructions.Instruction;
import main.Memory;
import main.Register;
import main.Registers;

public class MULTRegister extends Instruction {
    public MULTRegister() {
        super("MULT-Register", (short) 0xF7, 2);
    }

    public void execute(Registers registers, Memory memory, Short op) {
        Register regDestinationAX = registers.getRegisterByName("AX");
        Register regDestinationDX = registers.getRegisterByName("DX");
        Register regFlag = registers.getFlagRegister();

        short valueDestinationAX = regDestinationAX.getValue();

        long result = (short) valueDestinationAX * (short) valueDestinationAX; // AX * AX
        short resultAX = (short) (result); // Primeros 16 bits da multiplicação vão para o AX
        short resultDX = (short) 0x0000; // Se não der overflow recebe 0
        if (result != resultAX) { // Teste do overflow do AX
            resultDX = (short) (result >> 16); // O extouro vai para o DX
        }
        short flags = (short) 0x0000;

        regDestinationAX.setValue(resultAX); //
        regDestinationDX.setValue(resultDX); // Armazena os resultados nos registradores

        // flags CF,PF,ZF,SF,OF
        // SF
        if (result < 0) {
            flags += 0x0200; // 1*2^9 decimal = 512
        }
        // ZF
        if (result == 0) {
            flags += 0x0100; // 1*2^8 decimal = 256
        }
        // PF
        if (regDestinationAX.getBitParity()) {
            flags += 0x0040; // 1*2^6 decimal = 64
        }
        // OF e CF
        if (result > 1073741823 || result < -1073741824) {
            flags += 0x1000; // 1*2^12 decimal = 4.096
            flags += 0x0000; // 1*2^00 decimal = 0
        }

        regFlag.setValue(flags);
    }
}
