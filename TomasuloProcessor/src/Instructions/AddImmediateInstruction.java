package Instructions;

import RegisterFile.Register;

public class AddImmediateInstruction extends Instruction{
	
	private Register regA, regB;
	private short imm;
	
	public AddImmediateInstruction(Register regA, Register regB, short immVal) {
		super(0);
		this.regA = regA;
		this.regB = regB;
		imm = immVal;
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

	public short getImm() {
		return imm;
	}

	public void setImm(short imm) {
		this.imm = imm;
	}
	
}
