package Instructions;

import RegisterFile.Register;

public class BEQInstruction extends Instruction{
	public BEQInstruction(Register regA, Register regB, short immVal) {
		super(1);
	}
}
