package main;

import Assembler.Assembler;
import Assembler.Tables;
import Linker.Linker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VirtualMachine {
    private final String name;
    private final CPU cpu;
    private final Memory memory;
    private final Assembler asm;
    private final Linker linker;
    private File[] codeFile;

    public VirtualMachine(CPU c, Memory memory, String name) {
        this.cpu = c;
        this.memory = memory;
        this.name = name;
        this.asm = new Assembler();
        this.linker = new Linker();
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

    public void loadProgram(File[] codeFile) throws IllegalArgumentException {
        if (codeFile == null || codeFile.length == 0) {
            throw new IllegalArgumentException("codeFile cannot be null");
        }

        // ToDo: Alterar para executar todos os arquivos selecionados
        this.codeFile = codeFile;
    }
    public File[] getCodeFile() {
        return codeFile;
    }

    public void run() throws FileNotFoundException {
        // Montar vetor de strings com o nome de cada arquivo

        String[] codeFileName = new String[this.codeFile.length];
        Tables[] tables = new Tables[this.codeFile.length];
        for (int i = 0; i < this.codeFile.length; i++) {
            codeFileName[i] = this.codeFile[i].getName().split("\\.")[0];
        }

        for (String s : codeFileName) {
            System.out.println(s);
        }


        //ToDo: Processa macros


        // Monta
        System.out.println("Montando...");
        for (int i = 0; i < this.codeFile.length; i++) {
            // Get file content
            File file = this.codeFile[i];
            Scanner sc = new Scanner(file);
            StringBuilder code = new StringBuilder();

            while (sc.hasNextLine())
                code.append(sc.nextLine() + "\n");
            sc.close();


            System.out.println(code.toString());
            String fileName = file.getName().split("\\.")[0];
            tables[i] = this.assemble(code.toString(), fileName);
        }
        // Linka
        // ToDo: A "linkagem" dos arquivos acontece aqui. Nesse estagio recebemos um array de arquivos .obj que já passaram pelo processador de macros e assembler

        // Código de linker
        linker.run(codeFile, tables);


        // Depois de linkado e gerado o arquivo .bin final é que executamos o programa
        // ToDo: alterar para executar todos os arquivos selecionados
        // Carregar o arquivo .obj gerado pelo assembler

        File file = new File(codeFileName[0] + ".obj");
        System.out.println("Executando..." + file.getName());
        cpu.run(file);
    }

    public Memory getMemory() {
        return memory;
    }

    public Tables assemble(String code, String fileName) {
        return asm.run(code, fileName);
    }
}
