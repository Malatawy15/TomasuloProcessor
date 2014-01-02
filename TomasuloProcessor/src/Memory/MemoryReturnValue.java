package Memory;

public class MemoryReturnValue {
	int stall;
	public int getStall() {
		return stall;
	}

	public void setStall(int stall) {
		this.stall = stall;
	}

	public short getData() {
		return data;
	}

	public void setData(short data) {
		this.data = data;
	}

	public byte[] getBlock() {
		return block;
	}

	public void setBlock(byte[] block) {
		this.block = block;
	}

	short data;
	byte[] block;
	
	public MemoryReturnValue(int stall, short data, byte[] block) {
		this.stall = stall;
		this.data = data;
		this.block = block;
	}
}
