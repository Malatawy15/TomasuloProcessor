package reservationStations;

import Instructions.Instruction;
import Instructions.JALRInstruction;
import Instructions.JMPInstruction;

public class JALRRS extends ReservationStation {

	public JALRRS(int i) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadInstruction(Instruction in, int indROB) {
		// TODO Auto-generated method stub
		reset();
		ins = in;
		busy = true;
		loadOperands();
		robIndex = indROB;
	}
	
	private void loadOperands() {
		// TODO Auto-generated method stub
		JALRInstruction ji = (JALRInstruction) ins;
		address = (short)ji.getRegA().getIndex();
		if (ji.getRegB().getState()==null){
			vals[0] = ji.getRegB().getVal();
			opsReady[0] = true;
		}
		else {
			ji.getRegB().getState().cdb.add(new CommonDataBus(this, 0));
		}
		opsReady[1] = true;
	}
	
	@Override
	public boolean exec() {
		if (opsReady[0]&&opsReady[1]){
			curNum++;
			return curNum == numToExec;
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public short writeBack() {
		// TODO Auto-generated method stub
		return 0;
	}

}
