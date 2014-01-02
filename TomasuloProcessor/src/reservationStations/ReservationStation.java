package reservationStations;

import java.util.LinkedList;

import Instructions.Instruction;

public abstract class ReservationStation {
	Instruction ins;
	short address, vals[];
	boolean busy, opsReady[];
	int numToExec, curNum, robIndex;
	LinkedList<CommonDataBus> cdb;
	
	public void reset(){
		ins = null;
		vals = new short[2];
		address = 0;
		busy = false;
		opsReady = new boolean[2];
		curNum = -1;
		robIndex = -1;
		cdb = new LinkedList<CommonDataBus>();
	}
	
	public boolean exec(){
		curNum++;
		return curNum == numToExec;
	}
	
	public abstract short writeBack();
	
}
