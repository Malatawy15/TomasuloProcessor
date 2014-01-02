package RegisterFile;

import reservationStations.ReservationStation;

public class RegisterFile {
	private Register generalPuroseRegisters[];
	private ReservationStation registerStat[];
	static final int REGISTERS_COUNT=8;
	
	public RegisterFile() {
		generalPuroseRegisters = new Register[REGISTERS_COUNT];
		registerStat = new ReservationStation[REGISTERS_COUNT];
		for(int i=0; i<generalPuroseRegisters.length; i++){
			generalPuroseRegisters[i] = new Register((short) 0, i==0);
			registerStat[i] = null;
		}
	}
	
	public int nameToIndex(String name){
		if(name.length()>8 || !name.matches("R[0-9]+")) {
			return -1;
		}
		return Integer.parseInt(name.substring(1));		
	}
	
	public Register getRegister(String name){
		int registerNumber = nameToIndex(name);
		if(registerNumber>=REGISTERS_COUNT || registerNumber<0) {
			return null;
		}
		return generalPuroseRegisters[registerNumber];
	}
	
	public Register getRegister(int registerNumber){
		if(registerNumber<0||registerNumber>=REGISTERS_COUNT) {
			return null;
		}
		return generalPuroseRegisters[registerNumber];
	}
	
	public ReservationStation getState(int registerNumber){
		return registerStat[registerNumber];
	}
	
	public void setState(int registerNumber, ReservationStation r){
		registerStat[registerNumber] = r;
	}
	
	public void resetState(){
		for (int i=0;i<registerStat.length;i++){
			registerStat[i] = null;
		}
	}
	
}
