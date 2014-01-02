package Memory;

public class CacheLineEntry {
	
	boolean valid;
	boolean dirty;
	int tag;
	byte[] data;
	
	public CacheLineEntry(int lineSize) {
		valid = false;
		dirty = false;
		tag = 0;
		data = new byte[lineSize];
	}

}
