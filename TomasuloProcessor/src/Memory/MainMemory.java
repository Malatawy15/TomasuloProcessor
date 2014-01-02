package Memory;

public class MainMemory {
	int accessTime;
	private byte[] mainMemory;
	
	public MainMemory(int accessTime) {
		mainMemory = new byte[65536];
		this.accessTime = accessTime;
	}
	
	public short readMainMemory(short address) {
		short data = (short) (((short) mainMemory[address] << 8) + mainMemory[address + 1]);
		return data;
	}
	
	public void writeMainMemory(short address, short data) {
		mainMemory[address + 1] = (byte) data;
		data >>= 8;
		mainMemory[address] = (byte) data;
	}
}
