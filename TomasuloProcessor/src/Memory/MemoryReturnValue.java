package Memory;

public class MemoryReturnValue {
	int stall;
	short data;
	byte[] block;
	
	public MemoryReturnValue(int stall, short data, byte[] block) {
		this.stall = stall;
		this.data = data;
		this.block = block;
	}
}
