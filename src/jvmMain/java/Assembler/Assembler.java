package Assembler;

public class Assembler {
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
