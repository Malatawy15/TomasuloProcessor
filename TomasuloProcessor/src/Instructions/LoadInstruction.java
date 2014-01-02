package Instructions;

import RegisterFile.Register;

public class LoadInstruction extends Instruction{
	
	private Register regA, regB;
	private short immVal;
	
	public LoadInstruction(Register regA, Register regB, short immVal) {
		super(4);
		this.regA = regA;
		this.regB = regB;
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
