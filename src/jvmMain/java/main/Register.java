package main;

public class Register {
    private final String name;
    private short value;

    public Register(String n, short v) {
        this.value = v;
        this.name = n;
    }

    public Register(String name) {
        this(name, (short)0);
    }

    public Register() {
        this("não nomeado");
    }

    public short getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setValue(short v) {
        value = v;
    }

    public boolean getBitParity() {
        String valueInBinary = Integer.toBinaryString(value);
        int count = 0;
        for (int i = 0; i < valueInBinary.length(); i++) {
            if (valueInBinary.charAt(i) == '1') {
                count++;
            }
        }
        return count % 2 == 0;
    }
}
