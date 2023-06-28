public class Memory {
    public final int MEMORY_SIZE_BYTES;
    public final int BLOCK_SIZE = 16;
    private byte[] cells;

    public Memory(int memorySize) {
        MEMORY_SIZE_BYTES = memorySize;
        cells = new byte[MEMORY_SIZE_BYTES];
    }

    public Memory() {
        this(1024);
    }
}
