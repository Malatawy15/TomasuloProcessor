package Memory;

public class Memory {

	MainMemory mainMemory;
	CacheMemory[] instructionCacheMemory;
	CacheMemory[] dataCacheMemory;

	Memory(int mainMemoryAccessTime, CacheMemory[] instructionCacheMemory,
			CacheMemory[] dataCacheMemory) {
		mainMemory = new MainMemory(mainMemoryAccessTime);
		this.instructionCacheMemory = instructionCacheMemory;
		this.dataCacheMemory = dataCacheMemory;
	}
	
	public short read(short address) {
		return 0;
	}
	
	public void write(short address, short data) {
		
	}

}
