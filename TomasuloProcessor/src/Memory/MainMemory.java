package Memory;

public class MainMemory implements MemoryInterface {
	int accessTime;
	private byte[] mainMemory;

	public MainMemory(int accessTime) {
		mainMemory = new byte[65536];
		this.accessTime = accessTime;
	}

	public MemoryReturnValue read(short address, int blockSize) {
		short data = (short) (((short) mainMemory[address] << 8) + mainMemory[address + 1]);
		return new MemoryReturnValue(accessTime, data, getBlock(address, blockSize));
	}

	public int write(short address, short data) {
		mainMemory[address + 1] = (byte) data;
		data >>= 8;
		mainMemory[address] = (byte) data;
		return accessTime;
	}

	public byte[] getBlock(int address, int blockSize) {
		int blockNumber = (address / blockSize) * blockSize;
		byte[] block = new byte[blockSize];
		int j = 0;
		for (int i = blockNumber; i < blockNumber + blockSize; i++) {
			block[j++] = mainMemory[i];
		}
		return block;
	}
}
