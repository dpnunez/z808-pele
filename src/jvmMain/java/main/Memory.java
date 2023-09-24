package main;

public class Memory {
    public final int MEMORY_SIZE_BYTES;
    public final int BLOCK_SIZE = 16;
    private short[] cells;

    public Memory(int memorySize) {
        MEMORY_SIZE_BYTES = memorySize;
        cells = new short[MEMORY_SIZE_BYTES];
    }


    public Memory() {
        this(8192);
    }

    public short getCell(int i) {
        return cells[i];
    }

    public void setCell(int i, short value) {
        cells[i] = value;
    }

    public short[] getCells() {
        return cells;
    }

    public void printMemory() {
        for (int i = 0; i < MEMORY_SIZE_BYTES; i++) {
            System.out.println("Memory[" + i + "] = " + cells[i]);
        }
    }
}
