package Instructions;

import RegisterFile.Register;

public class JMPInstruction extends Instruction{
	public JMPInstruction(Register regA, short immVal) {
		super(2);
	}
}
