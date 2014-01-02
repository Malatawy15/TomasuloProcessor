package MainProgram;

import java.util.LinkedList;

import buffers.ReOrderBuffer;
import buffers.ReOrderObject;

import reservationStations.ReservationStation;
import reservationStations.Stations;

public class Processor {
	
	private static Processor singletonProcessor;
	
	private int cycles;
	private Stations stations;
	private ReOrderBuffer rob;
	
	public static Processor getProcessor(){
		if(singletonProcessor==null){
			singletonProcessor = new Processor();
		}
		return singletonProcessor;
	}
	
	public Processor(){
		cycles = 0;
	}
	
	public int run(){
		while(cycles<Integer.MAX_VALUE){
			cycles++;
			//Fetch new instruction
			//Issue new instruction
			//Execute in reservation stations
			LinkedList<ReservationStation> doneStations = stations.runCycle(); 
			for (ReservationStation rs : doneStations){
				rs.writeBack();
				rs .reset();
			}
			//commit changes
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
