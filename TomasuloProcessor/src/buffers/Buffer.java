package buffers;

import java.util.ArrayList;

public class Buffer <T>{

	int head, capacity, tail, numItems;
	ArrayList<T> buffer;
	
	public Buffer (int size){
		capacity = size;
		buffer = new ArrayList<T>(capacity);
		head = 0;
		tail = -1;
		numItems = 0;
	}
	
	public boolean isEmpty(){
		return numItems == 0;
	}
	
	public boolean isFull(){
		return numItems == capacity;
	}
	
	public boolean validIndex(int index){
		return index>=head && index<=tail && index<capacity && index>=0;
	}
	
	public T removeFirst(){
		if (isEmpty()){
			return null;
		}
		T first = buffer.get(head);
		head = (head+1)%capacity;
		numItems--;
		return first;
	}
	
	public T getFirst(){
		if (isEmpty()){
			return null;
		}
		return buffer.get(head);
	}
	
	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getTail() {
		return tail;
	}

	public void setTail(int tail) {
		this.tail = tail;
	}

	public int getNumItems() {
		return numItems;
	}

	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}

	public ArrayList<T> getBuffer() {
		return buffer;
	}

	public void setBuffer(ArrayList<T> buffer) {
		this.buffer = buffer;
	}

	public T getItem(int index){
		if (isEmpty()){
			return null;
		}
		if (!validIndex(index)){
			return null;
		}
		return buffer.get(index);
	}
	
	public int insert(T ins){
		if (isFull()){
			return -1;
		}
		tail = (tail+1)%capacity;
		buffer.set(tail, ins);
		numItems++;
		return tail;
	}
	
}
