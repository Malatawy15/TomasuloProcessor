package Instructions;

import RegisterFile.Register;

public class JALRInstruction extends Instruction{
	
	private Register regA, regB;
	
	public JALRInstruction(Register regA, Register regB) {
		super(2);
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
}
