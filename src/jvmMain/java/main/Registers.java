package main;

import java.util.HashMap;
import java.util.Map;

public class Registers {
    private final Map<Short, Register> values;

    public Registers(Map<Short, Register> r) {
        values =  r;
    }

    public Registers() {
        Map<Short, Register> regs = new HashMap<>();
        regs.put((short)0xC0, new Register("AX"));
        regs.put((short)0xC2, new Register("DX"));
        regs.put((short)0xC4, new Register("SP"));
        regs.put((short)0xC6, new Register("SI"));
        regs.put((short)0xC8, new Register("IP"));
        regs.put((short)0xCA, new Register("SR"));

        this.values = regs;
    }

    public Register getRegisterByName(String name) {
        for (Map.Entry<Short, Register> entry : values.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Map<Short, Register> getRegisters() {
        return values;
    }

    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<Short, Register> entry : values.entrySet()) {
            result += entry.getValue().getName() + ": " + entry.getValue().getValue() + "\n";
        }
        return result;
    }
}
