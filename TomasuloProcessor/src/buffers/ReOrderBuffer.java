package buffers;

import MainProgram.Processor;

public class ReOrderBuffer extends Buffer<ReOrderObject>{
	
	public ReOrderBuffer(int size){
		super(size);
	}
	
	public void writeValue(int index, short value){
		ReOrderObject roo = getItem(index);
		roo.setValue(value);
		roo.setReady(true);
	}
	
	public void setDestination(int index, int des){
		ReOrderObject roo = getItem(index);
		roo.setDestination(des);
	}
	
	public void commit(){
		ReOrderObject roo = getFirst();
		//check if branch, then check for flushing
		//if passes all checks, commit changes to memory
		if (roo.isReady()){
			removeFirst();
			int cond = roo.regOrMem(); 
			if (cond==0){
				//memory
			}
			else if (cond==1){
				//register
			}
			
			if (roo.isAlterPC()){
				Processor.getProcessor().setInstructionAddress(roo.getValPC());
				//flush and reset
			}
			
		}
	}
	
}
