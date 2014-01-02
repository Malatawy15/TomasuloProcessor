package Instructions;

public interface MemoryInstruction extends Instruction{
	public void execute();
	public void writeBack();
}
