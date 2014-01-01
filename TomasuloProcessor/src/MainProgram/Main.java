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
		return program;
	}
}
