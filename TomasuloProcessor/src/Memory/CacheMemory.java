package Memory;

public class CacheMemory {
	
	int s; //size
	int l; //line size
	int m; //associativity
	boolean wpHit;
	boolean wpMiss;
	int accessTime;
	CacheLineEntry[][] cacheMemory;
	
	int offsetBits;
	int indexBits;
	int tagBits;
	
	final boolean write_through = true;
	final boolean write_allocate = true;
	
	public CacheMemory(int s, int l, int m, boolean wpHit, boolean wpMiss, int accessTime) {
		this.s = s;
		this.l = l;
		this.m = m;
		this.wpHit = wpHit;
		this.wpMiss = wpMiss;
		this.accessTime = accessTime;
		cacheMemory = new CacheLineEntry[s/m][m];
		for (int i = 0; i < cacheMemory.length; i++) {
			for (int j = 0; j < cacheMemory[i].length; j++) {
				cacheMemory[i][j] = new CacheLineEntry(l);
			}
		}
		offsetBits = (int) Math.log(l);
		indexBits = (int) Math.log(s/m);
		tagBits = 16 - offsetBits - indexBits;
	}
	
	public short readCache(short address) {
		int offset = 0;
		for (int i = 0; i<offsetBits; i++) {
			offset |= address & (1<<i);
		}
		address >>= offsetBits;
		int index = 0;
		for (int i = 0; i<indexBits; i++) {
			index |= address & (1<<i);
		}
		address >>= indexBits;
		int tag = 0;
		for (int i = 0; i<tagBits; i++) {
			tag |= address & (1<<i);
		}
		for (int i = 0; i < cacheMemory[index].length; i++) {
			if (cacheMemory[index][i].valid && cacheMemory[index][i].tag == tag) {
				byte data1 = cacheMemory[index][i].data[offset];
				byte data2 = cacheMemory[index][i].data[offset + 1];
				return (short) ((short) data1 << 8 + data2);
			}
		}
		return -1;
	}
	
	public void writeCache(short address, short data) {
		
	}

}
