package main;

import java.util.Scanner;

public class Sandbox {
    public void movDirect() {
        VirtualMachine vm = new VirtualMachine();
        Register dx = vm.getCPU().getRegisters().getRegisterByName("DX");
        vm.getMemory().setCell(1, (short) 233);                 // Setting cell indexed by 1
        vm.getCPU().execute("000000110000000000000001"); // MOV Direct pointing to cell indexed by 1
        System.out.println(vm.getCPU().getRegisters());
    }

    public void movImediate() {
        VirtualMachine vm = new VirtualMachine();
        System.out.println(vm.getCPU().getRegisters());
        vm.getCPU().execute("000000100000000000001000"); // MOV Imediate has 2 bytes operand
        System.out.println(vm.getCPU().getRegisters());
    }

    public void movByRegister() {
        VirtualMachine vm = new VirtualMachine();
        Register dx = vm.getCPU().getRegisters().getRegisterByName("DX");
        dx.setValue((short) 43);
        System.out.println(vm.getCPU().getRegisters());
        vm.getCPU().execute("0000000111000010"); // MOV
        System.out.println(vm.getCPU().getRegisters());
    }
    public void basicExample() {
        VirtualMachine vm = new VirtualMachine();
        System.out.println(vm.getName());
        System.out.println(vm.getCPU().getRegisters());
        System.out.println(vm.getCPU().getInstructions());
    }
    public void subByRegister() {
        VirtualMachine vm = new VirtualMachine();
        Register dx = vm.getCPU().getRegisters().getRegisterByName("DX");
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        dx.setValue((short) 10);
        ax.setValue((short) 19);
        vm.getCPU().execute("0010101111000010");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void orByRegister() {
        VirtualMachine vm = new VirtualMachine();
        Register dx = vm.getCPU().getRegisters().getRegisterByName("DX");
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 6);
        dx.setValue((short) 10);
        // 0BC0
        //vm.getCPU().execute("0000101111000000");
        // 0BC2
        vm.getCPU().execute("0000101111000010");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void orImediate() {
        VirtualMachine vm = new VirtualMachine();
        System.out.println(vm.getCPU().getRegisters());
        vm.getCPU().execute("000011000000000000000011"); // 0C 3 em binario
        System.out.println(vm.getCPU().getRegisters());
    }
    public void orDirect() {
        VirtualMachine vm = new VirtualMachine();
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 10);
        vm.getMemory().setCell(1, (short) 6);
        vm.getCPU().execute("000011010000000000000001"); // Acessando a celula 1 da memoria
        System.out.println(vm.getCPU().getRegisters());
    }
    public void xorByRegister() {
        VirtualMachine vm = new VirtualMachine();
        Register dx = vm.getCPU().getRegisters().getRegisterByName("DX");
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 6);
        dx.setValue((short) 10);
        System.out.println(vm.getCPU().getRegisters());
        // 33C0
        //vm.getCPU().execute("0011001111000000");
        // 33C2
        vm.getCPU().execute("0011001111000010");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void xorImediate() {
        VirtualMachine vm = new VirtualMachine();
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 6);
        System.out.println(vm.getCPU().getRegisters());
        vm.getCPU().execute("001101010000000000001010"); // 0C 10 em binario
        System.out.println(vm.getCPU().getRegisters());
    }
    public void xorDirect() {
        VirtualMachine vm = new VirtualMachine();
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 10);
        vm.getMemory().setCell(1, (short) 6);
        vm.getCPU().execute("001101010000000000000001");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void andByRegister() {
        VirtualMachine vm = new VirtualMachine();
        Register dx = vm.getCPU().getRegisters().getRegisterByName("DX");
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 6);
        dx.setValue((short) 10);
        System.out.println(vm.getCPU().getRegisters());
        // 23C0
        //vm.getCPU().execute("0010001111000000");
        // 23C2
        vm.getCPU().execute("0010001111000010");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void andImediate() {
        VirtualMachine vm = new VirtualMachine();
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 6);
        System.out.println(vm.getCPU().getRegisters());
        vm.getCPU().execute("001001000000000000001010"); // 0C 10 em binario
        System.out.println(vm.getCPU().getRegisters());
    }
    public void andDirect() {
        VirtualMachine vm = new VirtualMachine();
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 10);
        vm.getMemory().setCell(1, (short) 6);
        vm.getCPU().execute("001001010000000000000001");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void CMPRegister() {
        VirtualMachine vm = new VirtualMachine();
        Register dx = vm.getCPU().getRegisters().getRegisterByName("DX");
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 6);
        dx.setValue((short) 6);
        System.out.println(vm.getCPU().getRegisters());
        // 3BC2
        vm.getCPU().execute("0011101111000010");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void CMPImediate() {
        VirtualMachine vm = new VirtualMachine();
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 10);
        System.out.println(vm.getCPU().getRegisters());
        vm.getCPU().execute("001111000000000000001010"); // 0C 10 em binario
        System.out.println(vm.getCPU().getRegisters());
    }
    public void CMPDirect() {
        VirtualMachine vm = new VirtualMachine();
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 10);
        vm.getMemory().setCell(1, (short) 10);
        vm.getCPU().execute("001111010000000000000001");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void JMPDirect() {
        VirtualMachine vm = new VirtualMachine();
        vm.getMemory().setCell(1, (short) 10);
        vm.getCPU().execute("111010110000000000000001");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void JMPImediate() {
        VirtualMachine vm = new VirtualMachine();
        System.out.println(vm.getCPU().getRegisters());
        vm.getCPU().execute("111010100000000000001010"); // 0C 10 em binario
        System.out.println(vm.getCPU().getRegisters());
    }
    public void JZDirect() {
        VirtualMachine vm = new VirtualMachine();
        Register sr = vm.getCPU().getRegisters().getRegisterByName("SR");
        sr.setValue((short) 256);
        vm.getMemory().setCell(1, (short) 10);
        vm.getCPU().execute("011101000000000000000001");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void JNZDirect() {
        VirtualMachine vm = new VirtualMachine();
        Register sr = vm.getCPU().getRegisters().getRegisterByName("SR");
        sr.setValue((short) 128);
        vm.getMemory().setCell(1, (short) 10);
        vm.getCPU().execute("011101010000000000000001");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void JPDirect() {
        VirtualMachine vm = new VirtualMachine();
        Register sr = vm.getCPU().getRegisters().getRegisterByName("SR");
        sr.setValue((short) 256);
        vm.getMemory().setCell(1, (short) 10);
        System.out.println(vm.getCPU().getRegisters());
        vm.getCPU().execute("011110010000000000000001");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void CALLDirect() {
        VirtualMachine vm = new VirtualMachine();
        vm.getMemory().setCell(1, (short) 10);
        vm.getCPU().execute("111010010000000000000001");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void CALLImediate() {
        VirtualMachine vm = new VirtualMachine();
        System.out.println(vm.getCPU().getRegisters());
        vm.getCPU().execute("111010000000000000001010"); // 0C 10 em binario
        System.out.println(vm.getCPU().getRegisters());
    }
    public void POPRegister() {
        VirtualMachine vm = new VirtualMachine();
        Register sp = vm.getCPU().getRegisters().getRegisterByName("SP");
        sp.setValue((short) 6);
        System.out.println(vm.getCPU().getRegisters());
        // 58C0
        vm.getCPU().execute("0101100011000000");
        // 58C2
        //vm.getCPU().execute("0101100011000010");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void POPDirect() {
        VirtualMachine vm = new VirtualMachine();
        Register sp = vm.getCPU().getRegisters().getRegisterByName("SP");
        sp.setValue((short) 6);
        vm.getMemory().setCell(1, (short) 10);
        vm.getCPU().execute("010110010000000000000001");
        System.out.println(vm.getCPU().getRegisters());
        System.out.println(vm.getMemory().getCell(10));
    }
    public void POPImediate() {
        VirtualMachine vm = new VirtualMachine();
        Register sp = vm.getCPU().getRegisters().getRegisterByName("SP");
        sp.setValue((short) 6);
        vm.getCPU().execute("010110100000000000000001");
        System.out.println(vm.getCPU().getRegisters());
        System.out.println(vm.getMemory().getCell(1));
    }
    public void READDirect() { // COMOOOO
        VirtualMachine vm = new VirtualMachine();
        Scanner sc = new Scanner(System.in);
        short input = sc.nextShort();
        String valueInBinary = String.format("%16s", Integer.toBinaryString(input)).replace(' ', '0');
        vm.getMemory().setCell(1, (short) 10);
        vm.getCPU().execute("010110010000000000000001");
        System.out.println(vm.getCPU().getRegisters());
        System.out.println(vm.getMemory().getCell(10));
    }
    public void PUSHRegister() {
        VirtualMachine vm = new VirtualMachine();
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 6);
        System.out.println(vm.getCPU().getRegisters());
        // 50C0
        vm.getCPU().execute("0101000011000000");
        // 50C2
        //vm.getCPU().execute("0101000011000010");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void PUSHFRegister() {
        VirtualMachine vm = new VirtualMachine();
        Register sr = vm.getCPU().getRegisters().getRegisterByName("SR");
        sr.setValue((short) 6);
        System.out.println(vm.getCPU().getRegisters());
        vm.getCPU().execute("1001110010011100");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void RETRegister() {
        VirtualMachine vm = new VirtualMachine();
        Register sp = vm.getCPU().getRegisters().getRegisterByName("SP");
        sp.setValue((short) 6);
        System.out.println(vm.getCPU().getRegisters());
        vm.getCPU().execute("1110111111101111");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void HLTRegister() {
        VirtualMachine vm = new VirtualMachine();
        vm.getCPU().execute("1110111011101110");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void POPFRegister() {
        VirtualMachine vm = new VirtualMachine();
        Register sp = vm.getCPU().getRegisters().getRegisterByName("SP");
        sp.setValue((short) 6);
        vm.getCPU().execute("1001110110011101");
        System.out.println(vm.getCPU().getRegisters());
    }
    public void MULTRegister() {
        VirtualMachine vm = new VirtualMachine();
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 32767);
        vm.getCPU().execute("1111011111110000");
        System.out.println(vm.getCPU().getRegisters());
    }
}
