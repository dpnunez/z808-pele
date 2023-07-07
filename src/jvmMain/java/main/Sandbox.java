package main;

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
        dx.setValue((short) 50000);
        ax.setValue((short) -60000);
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
    public void addRegister() {
        VirtualMachine vm = new VirtualMachine();
        Register dx = vm.getCPU().getRegisters().getRegisterByName("DX");
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        dx.setValue((short) 1);
        ax.setValue((short) 16384);
        vm.getCPU().execute("0000001111000010"); // instrução em hex = 03 C2
        System.out.println(vm.getCPU().getRegisters());
    }
    public void addImediate() {
        VirtualMachine vm = new VirtualMachine();
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 60);
        vm.getCPU().execute("000001000000000000111100"); //
        System.out.println(vm.getCPU().getRegisters());
    }
    public void addDirect() {
        VirtualMachine vm = new VirtualMachine();
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 16384);
        vm.getMemory().setCell(1, (short) 1);
        vm.getCPU().execute("000001010000000000000001");
        System.out.println(vm.getCPU().getRegisters());
    }
}
