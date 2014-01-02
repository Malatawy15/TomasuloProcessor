package reservationStations;

import Instructions.Instruction;
import Instructions.NandInstruction;
import MainProgram.Processor;

public class NandRS extends ReservationStation {
	

	public NandRS(int comp) {
		reset();
		numToExec = comp;
	}

	public void loadInstruction(Instruction in, int indROB) {
		reset();
		ins = in;
		busy = true;
		loadOperands();
		robIndex = indROB;
	}

	private void loadOperands() {
		NandInstruction ni = (NandInstruction) ins;
		if (ni.getRegC().getState() == null) {
			vals[0] = ni.getRegC().getVal();
			opsReady[0] = true;
		} else {
			ni.getRegC().getState().cdb.add(new CommonDataBus(this, 0));
		}
		if (ni.getRegB().getState() == null) {
			vals[1] = ni.getRegB().getVal();
			opsReady[1] = true;
		} else {
			ni.getRegB().getState().cdb.add(new CommonDataBus(this, 1));
		}
	}

	@Override
	public boolean exec() {
		if (opsReady[0] && opsReady[1]) {
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

	public short computeResult() {
		return (short) (~(vals[1] & vals[0]));
	}
}
