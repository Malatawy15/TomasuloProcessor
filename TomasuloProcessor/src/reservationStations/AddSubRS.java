package reservationStations;

import Instructions.AddImmediateInstruction;
import Instructions.AddInstruction;
import Instructions.Instruction;
import Instructions.SubtractInstruction;
import MainProgram.Processor;

public class AddSubRS extends ReservationStation {
	
	public AddSubRS(int comp){
		reset();
		numToExec = comp;
	}
	
	public void loadInstruction(Instruction in, int indROB){
		reset();
		ins = in;
		busy = true;
		loadOperands();
		robIndex = indROB;
	}
	
	private void loadOperands(){
		if (ins instanceof AddInstruction){
			AddInstruction ai = (AddInstruction) ins;
			address = (short )ai.getRegA().getIndex();
			if (ai.getRegC().getState()==null){
				vals[0] = ai.getRegC().getVal();
				opsReady[0] = true;
			}
			else {
				ai.getRegC().getState().cdb.add(new CommonDataBus(this, 0));
			}
			if (ai.getRegB().getState()==null){
				vals[1] = ai.getRegB().getVal();
				opsReady[1] = true;
			}
			else {
				ai.getRegB().getState().cdb.add(new CommonDataBus(this, 1));
			}
		}
		else if (ins instanceof AddImmediateInstruction){
			AddImmediateInstruction ai = (AddImmediateInstruction) ins;
			address = (short )ai.getRegA().getIndex();
			vals[0] = ai.getImm();
			opsReady[0] = true;
			if (ai.getRegB().getState()==null){
				vals[1] = ai.getRegB().getVal();
				opsReady[1] = true;
			}
			else {
				ai.getRegB().getState().cdb.add(new CommonDataBus(this, 1));
			}
		}
		else if (ins instanceof SubtractInstruction){
			SubtractInstruction si = (SubtractInstruction) ins;
			address = (short )si.getRegA().getIndex();
			if (si.getRegC().getState()==null){
				vals[0] = si.getRegC().getVal();
				opsReady[0] = true;
			}
			else {
				si.getRegC().getState().cdb.add(new CommonDataBus(this, 0));
			}
			if (si.getRegB().getState()==null){
				vals[1] = si.getRegB().getVal();
				opsReady[1] = true;
			}
			else {
				si.getRegB().getState().cdb.add(new CommonDataBus(this, 1));
			}
		}
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
		Processor.getProcessor().getRob().setDestination(robIndex, address);
		reset();
		return res;
	}
	
	public short computeResult(){
		if (ins instanceof AddInstruction){
			return (short) (vals[0] + vals[1]);
		}
		else if (ins instanceof AddImmediateInstruction){
			return (short) (vals[0] + vals[1]);
		}
		else if (ins instanceof SubtractInstruction){
			return (short) (vals[1] - vals[0]);
		}
		return 0;
	}

}
