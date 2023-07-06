package main;

public class Sandbox {
    public void movDirect() {
        VirtualMachine vm = new VirtualMachine();
        Register dx = vm.getCPU().getRegisters().getRegisterByName("DX");
        dx.setValue((short) 1);
        vm.getMemory().setCell(1, (short) 43);
        vm.getCPU().execute("000000110000000011000010"); // MOV Direct has 2 bytes operand
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

    public void DIVRegister(){
        VirtualMachine vm = new VirtualMachine();
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 0);
        System.out.println(vm.getCPU().getRegisters());
        vm.getCPU().execute("1111011111000000");
        System.out.println(vm.getCPU().getRegisters());
    }

    public void NOTRegister() {
        VirtualMachine vm = new VirtualMachine();
        Register ax = vm.getCPU().getRegisters().getRegisterByName("AX");
        ax.setValue((short) 0xffff);
        System.out.println(vm.getCPU().getRegisters());
        vm.getCPU().execute("1111100011000000");
        System.out.println(vm.getCPU().getRegisters());
    }
}
