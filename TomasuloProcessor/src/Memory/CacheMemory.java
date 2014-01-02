package Memory;

import java.util.Random;

public class CacheMemory implements MemoryInterface {

	int s; // size
	int l; // line size
	int m; // associativity
	boolean wpHit;
	boolean wpMiss;
	int accessTime;
	CacheLineEntry[][] cacheMemory;

	MemoryInterface ancestor;
	MainMemory mainMemory;

	int offsetBits;
	int indexBits;
	int tagBits;

	final boolean write_through = true;
	final boolean write_allocate = true;

	public CacheMemory(int s, int l, int m, boolean wpHit, boolean wpMiss,
			int accessTime) {
		this.s = s;
		this.l = l;
		this.m = m;
		this.wpHit = wpHit;
		this.wpMiss = wpMiss;
		this.accessTime = accessTime;
		cacheMemory = new CacheLineEntry[s / m][m];
		for (int i = 0; i < cacheMemory.length; i++) {
			for (int j = 0; j < cacheMemory[i].length; j++) {
				cacheMemory[i][j] = new CacheLineEntry(l);
			}
		}
		offsetBits = (int) Math.log(l);
		indexBits = (int) Math.log(s / m);
		tagBits = 16 - offsetBits - indexBits;
	}

	public MemoryReturnValue read(short address) {
		MemoryReturnValue mrv = read(address, l);
		return mrv;
	}

	public MemoryReturnValue read(short address, int blockSize) {
		int offset = 0;
		for (int i = 0; i < offsetBits; i++) {
			offset |= address & (1 << i);
		}
		address >>= offsetBits;
		int index = 0;
		for (int i = 0; i < indexBits; i++) {
			index |= address & (1 << i);
		}
		address >>= indexBits;
		int tag = 0;
		for (int i = 0; i < tagBits; i++) {
			tag |= address & (1 << i);
		}
		for (int i = 0; i < cacheMemory[index].length; i++) {
			if (cacheMemory[index][i].valid && cacheMemory[index][i].tag == tag) {
				byte data1 = cacheMemory[index][i].data[offset];
				byte data2 = cacheMemory[index][i].data[offset + 1];
				short myData = (short) ((short) data1 << 8 + data2);
				return new MemoryReturnValue(accessTime, myData, getBlock(
						address, blockSize));
			}
		}
		MemoryReturnValue mrv = ancestor.read(address, l);
		mrv.stall += accessTime;
		int writeStall = directWrite(address, mrv.data);
		mrv.stall += writeStall;
		return mrv;
	}

	public int write(short address, short data) {
		int offset = 0;
		for (int i = 0; i < offsetBits; i++) {
			offset |= address & (1 << i);
		}
		address >>= offsetBits;
		int index = 0;
		for (int i = 0; i < indexBits; i++) {
			index |= address & (1 << i);
		}
		address >>= indexBits;
		int tag = 0;
		for (int i = 0; i < tagBits; i++) {
			tag |= address & (1 << i);
		}
		for (int i = 0; i < cacheMemory[index].length; i++) {
			if (cacheMemory[index][i].valid && cacheMemory[index][i].tag == tag) {
				if (wpHit == write_through) {
					cacheMemory[index][i].data[offset + 1] = (byte) data;
					data >>= 8;
					cacheMemory[index][i].data[offset] = (byte) data;
					int stall = ancestor.write(address, data);
					return stall += accessTime;
				} else {
					mainMemory.write(address, data);
					return mainMemory.accessTime;
				}
			}
		}
		if (wpMiss == write_allocate) {
			Random rand = new Random();
			int r = rand.nextInt(m);
			cacheMemory[index][r].data = ancestor.getBlock(address, l);
			return accessTime;
		} else {
			mainMemory.write(address, data);
			return mainMemory.accessTime;
		}
	}

	public int directWrite(short address, short data) {
		int offset = 0;
		for (int i = 0; i < offsetBits; i++) {
			offset |= address & (1 << i);
		}
		address >>= offsetBits;
		int index = 0;
		for (int i = 0; i < indexBits; i++) {
			index |= address & (1 << i);
		}
		address >>= indexBits;
		int tag = 0;
		for (int i = 0; i < tagBits; i++) {
			tag |= address & (1 << i);
		}
		Random rand = new Random();
		int r = rand.nextInt(m);
		cacheMemory[index][r].data = ancestor.getBlock(address, l);
		return accessTime;
	}

	public byte[] getBlock(int address, int blockSize) {
		int offset = 0;
		for (int i = 0; i < offsetBits; i++) {
			offset |= address & (1 << i);
		}
		address >>= offsetBits;
		int index = 0;
		for (int i = 0; i < indexBits; i++) {
			index |= address & (1 << i);
		}
		address >>= indexBits;
		int tag = 0;
		for (int i = 0; i < tagBits; i++) {
			tag |= address & (1 << i);
		}
		int blockNumber = (address / blockSize) * blockSize;
		byte[] block = new byte[blockSize];
		for (int i = 0; i < cacheMemory[index].length; i++) {
			if (cacheMemory[index][i].valid && cacheMemory[index][i].tag == tag) {
				int j = 0;
				for (int k = 0; k < blockNumber; k++) {
					block[j++] = cacheMemory[index][i].data[k];
				}
			}
		}
		return block;
	}

}
