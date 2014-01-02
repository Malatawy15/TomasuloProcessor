package RegisterFile;

import reservationStations.ReservationStation;

public class Register {
	private short val;
	private final boolean locked;
	private ReservationStation state;
	private int index;
	
	public Register(int ind, short val, boolean locked) {
		index = ind;
		this.val = val;
		this.locked = locked;
	}

	public short getVal() {
		return val;
	}

	public void setVal(short val) {
		if(locked) return;
		this.val = val;
	}
	
	public void setState(ReservationStation rs){
		state = rs;
	}
	
	public void clearState(){
		state = null;
	}
	
	public ReservationStation getState(){
		return state;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
