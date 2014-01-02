package MainProgram;

import java.util.LinkedList;

import reservationStations.ReservationStation;
import reservationStations.Stations;

public class Processor {
	
	private static Processor singletonProcessor;
	
	int cycles;
	Stations stations;
	
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
	
}
