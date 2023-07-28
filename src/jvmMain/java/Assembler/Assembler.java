package Assembler;

import instructions.Instruction;
import main.Instructions;

import java.util.HashMap;
import java.util.Map;

public class Assembler {
    private final HashMap<String, Instruction> instructions;
//    private final HashMap<String, PseudoInstructions>

    public Assembler() {
        this.instructions = new HashMap<>();
        Instructions i = new Instructions();

        // Adicionar as intruções suportadas
        for (Map.Entry<Short,Instruction> instruction : i.getInstructions().entrySet()) {
            this.instructions.put(instruction.getValue().getName(), instruction.getValue());
        }

        // Adicionar as pseudo instrucoes suportadas
    }
    public void run(String code) {
        String[] lines = code.split("\n");
        int count = lines.length;

        // passar por todas as linhas e printar
        for (int i = 0; i < count; i++) {
            System.out.println(lines[i]);
            System.out.println("Linha " + i);
        }
    }
}
