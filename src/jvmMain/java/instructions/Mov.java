package instructions;

public class Mov extends Instruction {
    public Mov() {
        super("MOV", (short) 0x01);
    }

    public void execute() {
        System.out.println("Executando Mov");
    }
}
