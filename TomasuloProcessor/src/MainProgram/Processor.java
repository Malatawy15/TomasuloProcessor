package MainProgram;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import Instructions.Instruction;
import Memory.Memory;
import buffers.InstructionBuffer;
import buffers.ReOrderBuffer;
import buffers.ReOrderObject;

import reservationStations.ReservationStation;
import reservationStations.Stations;

public class Processor {

	private static Processor singletonProcessor;

	private int cycles, instructionAddress;
	private Stations stations;
	private ReOrderBuffer rob;
	private InstructionBuffer instructionBuffer;
	private Memory dataMemory, instMemory;
	private ArrayList<Instruction> program;

	public static Processor getProcessor() {
		if (singletonProcessor == null) {
			singletonProcessor = new Processor();
		}
		return singletonProcessor;
	}

	public Processor() {

	}

	public Processor(int instructionBufferSize, int instructionAddress,
			Memory dataMemory, Memory instMemory, ArrayList<Instruction> program) {
		cycles = 0;
		this.instructionBuffer = new InstructionBuffer(instructionBufferSize);
		this.instructionAddress = instructionAddress;
		this.dataMemory = dataMemory;
		this.instMemory = instMemory;
		this.program = program;
	}

	public int run() {
		int curInstAddress = instructionAddress;
		while (cycles < Integer.MAX_VALUE) {
			cycles++;
			// Fetch new instruction
			if (!instructionBuffer.isFull()) {
				int instIndex = instMemory.read((short) curInstAddress);
				instructionBuffer.insert(program.get(instIndex));
				curInstAddress+=2;
			}
			// Issue new instruction
			Instruction myIn = instructionBuffer.getFirst();
			int type = myIn.getType();
			int index = stations.checkFree(type);
			if (index != -1) {
				int robIndex = rob.insert(new ReOrderObject(myIn, 0)); //REPLACE ZERO BY MEMORY ADDRESS
				stations.getStations()[type][index].loadInstruction(myIn, robIndex);
			}
			// Execute in reservation stations
			LinkedList<ReservationStation> doneStations = stations.runCycle();
			for (ReservationStation rs : doneStations) {
				rs.writeBack();
				rs.reset();
			}
			// commit changes
			rob.commit();
		}
		return cycles;
	}

	public int getCycles() {
		return cycles;
	}

	public void setCycles(int cycles) {
		this.cycles = cycles;
	}

	public Stations getStations() {
		return stations;
	}

	public void setStations(Stations stations) {
		this.stations = stations;
	}

	public ReOrderBuffer getRob() {
		return rob;
	}

	public void setRob(ReOrderBuffer rob) {
		this.rob = rob;
	}

}
