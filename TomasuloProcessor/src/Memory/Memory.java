package Memory;

public class Memory {

	MainMemory mainMemory;
	CacheMemory[] cacheMemory;

	public Memory(int mainMemoryAccessTime, CacheMemory[] cacheMemory) {
		mainMemory = new MainMemory(mainMemoryAccessTime);
		this.cacheMemory = cacheMemory;
	}
	
	public short read(short address) {
		return 0;
	}
	
	public void write(short address, short data) {
		
	}

}
