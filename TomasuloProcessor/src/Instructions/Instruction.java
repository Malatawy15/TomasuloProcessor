package Instructions;

public class Instruction {
	private int type;
	
	public Instruction(int t){
		type = t;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
