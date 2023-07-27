package main;

import Assembler.Assembler;

import java.io.File;
import java.io.FileNotFoundException;

public class VirtualMachine {
    private final String name;
    private final CPU cpu;
    private final Memory memory;
    private final Assembler asm;
    private File codeFile;

    public VirtualMachine(CPU c, Memory memory, String name) {
        this.cpu = c;
        this.memory = memory;
        this.name = name;
        this.asm = new Assembler();
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

    public void loadProgram(File codeFile) throws IllegalArgumentException {
        if (codeFile == null) {
            throw new IllegalArgumentException("codeFile cannot be null");
        }
        this.codeFile = codeFile;
    }
    public File getCodeFile() {
        return codeFile;
    }

    public void run() throws FileNotFoundException {
        cpu.run(this.codeFile);
    }

    public Memory getMemory() {
        return memory;
    }

    public void assemble(String code) {
        asm.run(code);
    }
}
