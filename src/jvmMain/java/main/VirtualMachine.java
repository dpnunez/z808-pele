package main;

public class VirtualMachine {
    private final String name;
    private final CPU cpu;
    private final Memory memory;

    public VirtualMachine(CPU c, Memory memory, String name) {
        this.cpu = c;
        this.memory = memory;
        this.name = name;
    }

    public VirtualMachine() {
        this(new CPU(), new Memory(), "z808");
    }

    public String getName() {
        return name;
    }

    public CPU getCPU() {
        return cpu;
    }
}
