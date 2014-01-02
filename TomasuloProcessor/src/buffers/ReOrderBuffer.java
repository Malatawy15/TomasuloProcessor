package buffers;

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
			if (roo.regOrMem()){
				//memory
			}
			else {
				//register
			}
		}
	}
	
}
