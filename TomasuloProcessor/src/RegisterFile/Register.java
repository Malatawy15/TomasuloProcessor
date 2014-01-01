package RegisterFile;

public class Register {
	private short val;
	private final boolean locked;
	
	public Register(short val, boolean locked) {
		this.val = val;
		this.locked = locked;
	}

	public short getVal() {
		return val;
	}

	public void setVal(short val) {
		if(locked) return;
		this.val = val;
	}
	
}
