package Instructions;

import RegisterFile.Register;

public class BEQInstruction extends Instruction{
	
	private Register regA;
	private Register regB;
	private short immVal;
	
	public BEQInstruction(Register regA, Register regB, short immVal) {
		super(1);
		this.regA = regA;
		this.regB = regB;
		this.immVal = immVal;
	}

	public Register getRegA() {
		return regA;
	}

	public void setRegA(Register regA) {
		this.regA = regA;
	}

	public Register getRegB() {
		return regB;
	}

	public void setRegB(Register regB) {
		this.regB = regB;
	}

	public short getImmVal() {
		return immVal;
	}

	public void setImmVal(short immVal) {
		this.immVal = immVal;
	}
}
