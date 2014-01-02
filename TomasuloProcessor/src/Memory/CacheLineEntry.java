package Memory;

public class CacheLineEntry {
	
	boolean valid;
	int tag;
	byte[] data;
	
	public CacheLineEntry(int lineSize) {
		valid = false;
		tag = 0;
		data = new byte[lineSize];
	}

}
