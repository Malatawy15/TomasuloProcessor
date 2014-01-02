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
	
	public void commit(){
		ReOrderObject roo = removeFirst();
		//check if branch, then check for flushing
		//if passes all checks, commit changes to memory
	}
	
}
