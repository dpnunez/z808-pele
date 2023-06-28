public class CPU {
    private final Registers registers;

    public CPU(Registers r) {
        registers = r;
    }

    public CPU() {
        this(new Registers());
    }
}
