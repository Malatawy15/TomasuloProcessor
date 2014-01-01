package MainProgram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Instructions.Instruction;
import RegisterFile.Register;
import RegisterFile.RegisterFile;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		RegisterFile regFile = new RegisterFile();
		
		// Load Memory Hierarchy
		
		// Load Hardware Organization

		// Load Program
		ArrayList<Instruction> program = loadProgram(br, regFile);

	}

	private static ArrayList<Instruction> loadProgram(BufferedReader br,
			RegisterFile regFile) throws NumberFormatException, IOException {
		ArrayList<Instruction> program = new ArrayList<>();
		String line;
		StringTokenizer st;
		while ((line = br.readLine()) != "") {
			st = new StringTokenizer(line, " ,");

			Register regA, regB, regC;
			int immVal;

			switch (st.nextToken().toUpperCase()) {
			case "LW":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				immVal = Integer.parseInt(st.nextToken());

				break;
			case "SW":
				regA = regFile.getRegister(st.nextToken());
				immVal = Integer.parseInt(st.nextToken());

				break;
			case "JMP":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				immVal = Integer.parseInt(st.nextToken());

				break;
			case "BEQ":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				immVal = Integer.parseInt(st.nextToken());

				break;
			case "JALR":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());

				break;
			case "RET":
				regA = regFile.getRegister(st.nextToken());

				break;
			case "ADD":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				regC = regFile.getRegister(st.nextToken());

				break;
			case "SUB":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				regC = regFile.getRegister(st.nextToken());

				break;
			case "ADDI":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				immVal = Integer.parseInt(st.nextToken());

				break;
			case "NAND":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				regC = regFile.getRegister(st.nextToken());

				break;
			case "MUL":
				regA = regFile.getRegister(st.nextToken());
				regB = regFile.getRegister(st.nextToken());
				regC = regFile.getRegister(st.nextToken());

				break;
			default:
				break;
			}
		}
		return program;
	}
}
