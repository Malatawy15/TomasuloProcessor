package reservationStations;

import Instructions.Instruction;
import Instructions.JMPInstruction;
import MainProgram.Processor;

public class JMPRS extends ReservationStation {

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
		JMPInstruction ji = (JMPInstruction) ins;
		if (ji.getRegA().getState()==null){
			vals[0] = ji.getRegA().getVal();
			opsReady[0] = true;
		}
		else {
			ji.getRegA().getState().cdb.add(new CommonDataBus(this, 0));
		}
		vals[1] = ji.getImmVal();
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
		short res = computeResult();
		notifyWaiters(res);
		Processor.getProcessor().getRob().writeValue(robIndex, res);
		reset();
		return res;
	}
	
	public short computeResult(){
		return (short) (Processor.getProcessor().getInstructionAddress() + 1 + vals[0] + vals[1]);
	}

}
