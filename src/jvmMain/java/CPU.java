public class CPU {
    private final Registers registers;

    public CPU(Registers r) {
        registers =  r;
    }

    public CPU() {
        registers = new Registers();
    }

    public Registers getRegisters() {
        return registers;
    }
}
