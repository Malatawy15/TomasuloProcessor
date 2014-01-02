package Instructions;

import RegisterFile.Register;

public class NandInstruction extends Instruction{
	
	private Register regA, regB, regC;
	
	public NandInstruction(Register regA, Register regB, Register regC) {
		super(6);
		this.regA = regA;
		this.regB = regB;
		this.regC = regC;
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

	public Register getRegC() {
		return regC;
	}

	public void setRegC(Register regC) {
		this.regC = regC;
	}
}
