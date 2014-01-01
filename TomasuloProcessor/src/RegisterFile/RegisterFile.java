package RegisterFile;

public class RegisterFile {
	private Register generalPuroseRegisters[];
	static final int REGISTERS_COUNT=8;
	
	public RegisterFile() {
		generalPuroseRegisters = new Register[REGISTERS_COUNT];
		for(int i=0; i<generalPuroseRegisters.length; i++){
			generalPuroseRegisters[i] = new Register((short) 0, i==0);
		}
	}
	
	public Register getRegister(String name){
		if(!name.matches("R[0-9]+")) {
			return null;
		}
		int registerNumber = Integer.parseInt(name.substring(1));
		if(registerNumber>=REGISTERS_COUNT) {
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
	
}
