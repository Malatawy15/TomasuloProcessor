package reservationStations;

import Instructions.Instruction;
import Instructions.MultiplyInstruction;
import MainProgram.Processor;

public class MultiplyRS extends ReservationStation {

	public MultiplyRS(int comp) {
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
		MultiplyInstruction mi = (MultiplyInstruction) ins;
		if (mi.getRegC().getState() == null) {
			vals[0] = mi.getRegC().getVal();
			opsReady[0] = true;
		} else {
			mi.getRegC().getState().cdb.add(new CommonDataBus(this, 0));
		}
		if (mi.getRegB().getState() == null) {
			vals[1] = mi.getRegB().getVal();
			opsReady[1] = true;
		} else {
			mi.getRegB().getState().cdb.add(new CommonDataBus(this, 1));
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
		return (short) (vals[1] * vals[0]);
	}
}
