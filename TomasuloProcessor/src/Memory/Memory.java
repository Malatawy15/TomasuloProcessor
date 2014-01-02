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

	public short read(short address) {
		return 0;
	}

	public void write(short address, short data) {

	}

}
