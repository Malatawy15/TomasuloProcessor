package buffers;

import Instructions.BEQInstruction;
import Instructions.Instruction;
import Instructions.JALRInstruction;
import Instructions.JMPInstruction;
import Instructions.RetInstruction;
import Instructions.StoreInstruction;

public class ReOrderObject {
	
	private boolean ready, alterPC;
	private Instruction ins;
	private short value, valPC;
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
	
	public int regOrMem(){
		if (isBranch()){
			alterPC = true;
		}
		if (ins instanceof StoreInstruction){
			return 0;
		}
		else if (ins instanceof JALRInstruction || !isBranch()){
			return 1;
		}
		else return 2;
	}
	
	public boolean isBranch(){
		return (ins instanceof BEQInstruction) || (ins instanceof JALRInstruction) || 
				(ins instanceof JMPInstruction) || (ins instanceof RetInstruction);
	}

	public boolean isAlterPC() {
		return alterPC;
	}

	public void setAlterPC(boolean alterPC) {
		this.alterPC = alterPC;
	}

	public short getValPC() {
		return valPC;
	}

	public void setValPC(short valPC) {
		this.valPC = valPC;
	}
	
}
