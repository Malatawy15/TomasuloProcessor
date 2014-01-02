package Instructions;

import RegisterFile.Register;

public class RetInstruction extends Instruction{
	
	private Register regA;
	public RetInstruction(Register regA) {
		super(7);
		this.regA = regA;
	}
	public Register getRegA() {
		return regA;
	}
	public void setRegA(Register regA) {
		this.regA = regA;
	}
}
