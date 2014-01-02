package Instructions;

import RegisterFile.Register;

public class JMPInstruction extends Instruction{
	
	private Register regA;
	private short immVal;
	
	public JMPInstruction(Register regA, short immVal) {
		super(2);
		this.regA = regA;
		this.immVal = immVal;
	}

	public Register getRegA() {
		return regA;
	}

	public void setRegA(Register regA) {
		this.regA = regA;
	}

	public short getImmVal() {
		return immVal;
	}

	public void setImmVal(short immVal) {
		this.immVal = immVal;
	}
	
	
}
