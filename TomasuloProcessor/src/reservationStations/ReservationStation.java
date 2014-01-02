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
	
	public void operandAvailable(int ind, short value){
		opsReady[ind] = true;
		vals[ind] = value;
		if (opsReady[0]&&opsReady[1]){
			curNum++;
		}
	}
	
	public void notifyWaiters(short res){
		for (CommonDataBus c : cdb){
			c.rs.operandAvailable(c.operandIndex, res);
		}
	}
	
	/*
	public boolean exec(){
		curNum++;
		return curNum == numToExec;
	}
	*/
	
	public abstract void loadInstruction(Instruction in, int indROB);
	
	public abstract boolean exec();
	
	public abstract short writeBack();

	public boolean isBusy() {
		// TODO Auto-generated method stub
		return busy;
	}
	
}
