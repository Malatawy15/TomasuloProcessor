package Memory;

public interface MemoryInterface {
	
	public MemoryReturnValue read(short address, int blockSize);
	public int write(short address, short data);
	public byte[] getBlock(int address, int blockSize);
}
