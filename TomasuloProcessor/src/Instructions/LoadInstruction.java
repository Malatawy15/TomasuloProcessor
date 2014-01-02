package Instructions;

import RegisterFile.Register;

public class LoadInstruction extends Instruction{
	public LoadInstruction(Register regA, Register regB, short immVal) {
		super(4);
	}
}
