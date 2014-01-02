package Instructions;

import RegisterFile.Register;

public class JALRInstruction extends Instruction{
	public JALRInstruction(Register regA, Register regB) {
		super(2);
	}
}
