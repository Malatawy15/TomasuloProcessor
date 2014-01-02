package Instructions;

import RegisterFile.Register;

public class StoreInstruction extends Instruction{
	public StoreInstruction(Register regA, Register regB, short immVal) {
		super(8);
	}
}
