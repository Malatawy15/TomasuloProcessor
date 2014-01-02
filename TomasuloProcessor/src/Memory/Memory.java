package Memory;

public class Memory {

	MainMemory mainMemory;
	CacheMemory[] cacheMemory;

	public Memory(int mainMemoryAccessTime, CacheMemory[] cacheMemory) {
		mainMemory = new MainMemory(mainMemoryAccessTime);
		this.cacheMemory = cacheMemory;
		for (int i = 0; i < cacheMemory.length - 1; i++) {
			cacheMemory[i].ancestor = cacheMemory[i + 1];
		}
		cacheMemory[cacheMemory.length - 1].ancestor = mainMemory;
		for (int i = 0; i < cacheMemory.length; i++) {
			cacheMemory[i].mainMemory = this.mainMemory;
		}
	}

	public MemoryReturnValue read(short address) {
		return cacheMemory[0].read(address);
	}

	public int write(short address, short data) {
		int stall = cacheMemory[0].write(address, data);
		return stall;
	}

}
