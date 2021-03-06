package MainProgram;

import java.util.ArrayList;
import java.util.LinkedList;

import Instructions.Instruction;
import Memory.Memory;
import RegisterFile.RegisterFile;
import buffers.InstructionBuffer;
import buffers.ReOrderBuffer;
import buffers.ReOrderObject;

import reservationStations.ReservationStation;
import reservationStations.Stations;

public class Processor {

	private static Processor singletonProcessor;

	private int cycles;
	private short instructionAddress;
	private Stations stations;
	private ReOrderBuffer rob;
	private InstructionBuffer instructionBuffer;
	private Memory dataMemory, instMemory;
	private RegisterFile registerFile;
	private ArrayList<Instruction> program;

	public static Processor getProcessor() {
		if (singletonProcessor == null) {
			singletonProcessor = new Processor();
		}
		return singletonProcessor;
	}

	public Processor() {
	}

	public void initProcessor(int instructionBufferSize,
			int instructionAddress, Memory dataMemory, Memory instMemory,
			ArrayList<Instruction> program, RegisterFile registerFile,
			int nRobEntries, int nRS[], int nCyclesRS[]) {
		cycles = 0;
		this.instructionBuffer = new InstructionBuffer(instructionBufferSize);
		this.instructionAddress = (short) instructionAddress;
		this.dataMemory = dataMemory;
		this.instMemory = instMemory;
		this.program = program;
		this.registerFile = registerFile;
		this.stations = new Stations(9, nRS, nCyclesRS);
		this.rob = new ReOrderBuffer(nRobEntries);
	}
	
	public void flush() {
		for (int i = 0; i < stations.getStations().length; i++) {
			for (int j = 0; j < stations.getStations()[i].length; j++) {
				stations.getStations()[i][j].reset();
			}
		}
		rob = new ReOrderBuffer(rob.getCapacity());
		instructionBuffer = new InstructionBuffer(instructionBuffer.getCapacity());
	}

	public RegisterFile getRegisterFile() {
		return registerFile;
	}

	public void setRegisterFile(RegisterFile registerFile) {
		this.registerFile = registerFile;
	}

	public int run() {
		int curInstAddress = instructionAddress;
		while (cycles < Integer.MAX_VALUE) {
			cycles++;
			// Fetch new instruction
			if (!instructionBuffer.isFull()) {
				int instIndex = (instMemory.read((short) curInstAddress))
						.getData();
				if (!(instIndex < 0 || (curInstAddress-instructionAddress)/2 >= program.size())){		
					System.out.println((curInstAddress-instructionAddress)/2);
					
					instructionBuffer.insert(program.get((curInstAddress-instructionAddress)/2));
					curInstAddress += 2;
				}
			}
			// Issue new instruction
			if(!instructionBuffer.isEmpty()){
				Instruction myIn = instructionBuffer.getFirst();
				int type = myIn.getType();
				int index = stations.checkFree(type);
				if (index != -1) {
					int robIndex = rob.insert(new ReOrderObject(myIn, 0)); // REPLACE
																			// ZERO
																			// BY
																			// MEMORY
																			// ADDRESS
					stations.getStations()[type][index].loadInstruction(myIn,
							robIndex);
					instructionBuffer.removeFirst();
				}
			}
			// Execute in reservation stations
			LinkedList<ReservationStation> doneStations = stations.runCycle();
			for (ReservationStation rs : doneStations) {
				rs.writeBack();
				rs.reset();
			}
			// commit changes
			rob.commit();
			if (rob.isEmpty()){
				break;
			}
		}
		return cycles;
	}

	public short getInstructionAddress() {
		return instructionAddress;
	}

	public void setInstructionAddress(short instructionAddress) {
		this.instructionAddress = instructionAddress;
	}


	public InstructionBuffer getInstructionBuffer() {
		return instructionBuffer;
	}

	public void setInstructionBuffer(InstructionBuffer instructionBuffer) {
		this.instructionBuffer = instructionBuffer;
	}

	public Memory getDataMemory() {
		return dataMemory;
	}

	public void setDataMemory(Memory dataMemory) {
		this.dataMemory = dataMemory;
	}

	public Memory getInstMemory() {
		return instMemory;
	}

	public void setInstMemory(Memory instMemory) {
		this.instMemory = instMemory;
	}

	public ArrayList<Instruction> getProgram() {
		return program;
	}

	public void setProgram(ArrayList<Instruction> program) {
		this.program = program;
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
