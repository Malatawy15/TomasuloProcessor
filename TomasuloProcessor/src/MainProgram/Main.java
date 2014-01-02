package MainProgram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Instructions.AddImmediateInstruction;
import Instructions.AddInstruction;
import Instructions.BEQInstruction;
import Instructions.Instruction;
import Instructions.JALRInstruction;
import Instructions.JMPInstruction;
import Instructions.LoadInstruction;
import Instructions.MultiplyInstruction;
import Instructions.NandInstruction;
import Instructions.RetInstruction;
import Instructions.StoreInstruction;
import Instructions.SubtractInstruction;
import Memory.CacheMemory;
import Memory.Memory;
import RegisterFile.Register;
import RegisterFile.RegisterFile;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		RegisterFile regFile = new RegisterFile();

		// Load Memory Hierarchy

		Memory dataMemory = loadMemorySpecs(br, "L1 data");
		Memory instructionMemory = loadMemorySpecs(br, "Instruction");

		// Load Hardware Organization
		System.out
				.println("Now, Please enter the size of the instruction buffer:");
		int instructionBufferSize = Integer.parseInt(br.readLine());

		System.out
				.println("Specify the number Load/Store reservation stations:");
		int nLSReservationStations = Integer.parseInt(br.readLine());

		System.out
				.println("Specify the number of cycles needed by Load/Store reservation stations:");
		int nCyclesLSReservationStations = Integer.parseInt(br.readLine());

		System.out
				.println("Specify the number Add/Subtract reservation stations:");
		int nASReservationStations = Integer.parseInt(br.readLine());

		System.out
				.println("Specify the number of cycles needed by Add/Subtract reservation stations:");
		int nCyclesASReservationStations = Integer.parseInt(br.readLine());

		System.out
				.println("Specify the number Multiply/Nand reservation stations:");
		int nMNReservationStations = Integer.parseInt(br.readLine());

		System.out
				.println("Specify the number of cycles needed by Multiply/Nand reservation stations:");
		int nCyclesMNReservationStations = Integer.parseInt(br.readLine());

		System.out.println("Specify the number of ROB entries:");
		int nROBEntries = Integer.parseInt(br.readLine());

		// TODO Init Hardware Organization

		System.out
				.println("Please Input Memory Address where Your Program Start:");
		int startAddress = Integer.parseInt(br.readLine().trim());
		// Load Program
		ArrayList<Instruction> program = loadProgram(br, regFile, instructionMemory, startAddress);
		

		// Load Program Data
		System.out.println("Please enter program data:");
		String line;
		StringTokenizer st;
		System.out
				.println("Enter [Address Value] for a new piece of data in the memory, or a new line to end:");
		while ((line = br.readLine()) != "") {
			st = new StringTokenizer(line, " ,");
			short address = Short.parseShort(st.nextToken());
			short value = Short.parseShort(st.nextToken());

			dataMemory.write(address, value);

			System.out
					.println("Enter [Address Value] for a new piece of data in the memory, or a new line to end:");
		}

		// TODO INIT processor, Run Program
	}

	private static Memory loadMemorySpecs(BufferedReader br, String memoryName)
			throws NumberFormatException, IOException {
		System.out
				.println("Please enter the number of cycles required to access the "
						+ memoryName + " Memory:");
		int memorynCycles = Integer.parseInt(br.readLine());

		System.out
				.println("Please enter the number of cashe levels(1/2/3) for the "
						+ memoryName + " Memory:");
		int nCaches = Integer.parseInt(br.readLine().trim());
		if (nCaches > 3 || nCaches < 1) {
			nCaches = 1;
		}
		CacheMemory cache[] = new CacheMemory[nCaches];
		for (int i = 0; i < nCaches; i++) {
			System.out.println("Please enter the full cashe geometry(S L m)"
					+ " of the Cashe Level" + (i + 1) + ":");
			String comps[] = br.readLine().split(" ");
			int S = Integer.parseInt(comps[0]), L = Integer.parseInt(comps[1]), m = Integer
					.parseInt(comps[2]);
			System.out
					.println("Please define the cashe hit policy, 1 for write-through, 2 for write-back:");
			boolean hitPolicy = br.readLine().trim().equals("1");

			System.out
					.println("Please define the cashe miss policy, 1 for write-allocate, 2 for write-around:");
			boolean missPolicy = br.readLine().trim().equals("1");

			System.out
					.println("Please enter the number of cycles required to access this cashe level:");
			int cacheLevelnCycles = Integer.parseInt(br.readLine());

			cache[i] = new CacheMemory(S, L, m, hitPolicy, missPolicy,
					cacheLevelnCycles);
		}

		return new Memory(memorynCycles, cache);
	}

	private static ArrayList<Instruction> loadProgram(BufferedReader br,
			RegisterFile regFile, Memory instructionMemory, int startAddress) throws NumberFormatException, IOException {
		System.out
				.println("Please Input Your program, end it with an empty line:");

		ArrayList<Instruction> program = new ArrayList<>();
		String line;
		StringTokenizer st;
		while (!(line = br.readLine()).equals("")) {
			st = new StringTokenizer(line, " ,");

			Register regA, regB, regC;
			short immVal;

			switch (st.nextToken().toUpperCase()) {
			case "LW":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				immVal = Short.parseShort(st.nextToken());

				program.add(new LoadInstruction(regA, regB, immVal));

				break;
			case "SW":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				immVal = Short.parseShort(st.nextToken());

				program.add(new StoreInstruction(regA, regB, immVal));

				break;
			case "JMP":
				regA = regFile.getRegister(st.nextToken());
				immVal = Short.parseShort(st.nextToken());

				program.add(new JMPInstruction(regA, immVal));

				break;
			case "BEQ":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				immVal = Short.parseShort(st.nextToken());

				program.add(new BEQInstruction(regA, regB, immVal));

				break;
			case "JALR":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());

				program.add(new JALRInstruction(regA, regB));

				break;
			case "RET":
				regA = regFile.getRegister(st.nextToken());

				program.add(new RetInstruction(regA));

				break;
			case "ADD":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				regC = regFile.getRegister(st.nextToken());

				program.add(new AddInstruction(regA, regB, regC));

				break;
			case "SUB":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				regC = regFile.getRegister(st.nextToken());

				program.add(new SubtractInstruction(regA, regB, regC));

				break;
			case "ADDI":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				immVal = Short.parseShort(st.nextToken());

				program.add(new AddImmediateInstruction(regA, regB, immVal));

				break;
			case "NAND":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				regC = regFile.getRegister(st.nextToken());

				program.add(new NandInstruction(regA, regB, regC));

				break;
			case "MUL":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				regC = regFile.getRegister(st.nextToken());

				program.add(new MultiplyInstruction(regA, regB, regC));

				break;
			default:
				break;
			}
		}
		for (int i = 0, curAddress = startAddress; i < program.size(); i++, curAddress += 2) {
			instructionMemory.write((short) curAddress, (short) i);
		}
		return program;
	}
}
