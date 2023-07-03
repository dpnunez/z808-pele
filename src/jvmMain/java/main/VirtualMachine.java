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

    public VirtualMachine(Memory m) {
        this(new CPU(m), m, "z808");
    }

    public VirtualMachine() {
        this(new Memory());
    }

    public String getName() {
        return name;
    }

    public CPU getCPU() {
        return cpu;
    }

    public Memory getMemory() {
        return memory;
    }
}
