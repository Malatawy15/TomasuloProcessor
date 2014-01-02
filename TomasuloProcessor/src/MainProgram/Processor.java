package MainProgram;

import java.util.LinkedList;

import reservationStations.ReservationStation;

public class Processor {
	
	int cycles;
	LinkedList<ReservationStation> doneStations;
	
	public Processor(){
		cycles = 0;
		doneStations = new LinkedList<ReservationStation>();
	}
	
	public int run(){
		while(cycles<Integer.MAX_VALUE){
			cycles++;
			//Fetch new instruction
			//Issue new instruction
			//Execute in reservation stations
			for (ReservationStation rs : doneStations){
				rs.writeBack();
				rs .reset();
			}
			doneStations.clear();
			//commit changes
		}
		return cycles;
	}
	
}
