package buffers;

import Instructions.Instruction;

public class ReOrderObject {
	
	private boolean ready;
	private Instruction ins;
	private short value;
	private int destination;
	
	public ReOrderObject(Instruction i, int des){
		ins = i;
		destination = des;
		ready = false;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public Instruction getIns() {
		return ins;
	}

	public void setIns(Instruction ins) {
		this.ins = ins;
	}

	public short getValue() {
		return value;
	}

	public void setValue(short value) {
		this.value = value;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}
	
	
	
}
