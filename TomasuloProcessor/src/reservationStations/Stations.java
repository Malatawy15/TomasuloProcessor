package reservationStations;

import java.util.LinkedList;

public class Stations {

	// Class to combine all stations with type arrays and number of stations for each type
	int numStations;
	ReservationStation [][] stations;
	int [] capacities;
	
	public Stations(int size, int [] cap){
		numStations = size;
		capacities = cap;
		stations = new ReservationStation[numStations][];
		for (int i=0;i<numStations;i++){
			// Replace ReservationStation with actual type
			stations[i] = new ReservationStation[capacities[i]];
			for (int j=0;j<capacities[i];j++){
				
			}
		}
	}
	
	public LinkedList<ReservationStation> runCycle(){
		LinkedList<ReservationStation> done = new LinkedList<ReservationStation>();
		for (int i=0;i<stations.length;i++){
			for (int j=0;j<stations[i].length;j++){
				if (stations[i][j].exec()){
					done.add(stations[i][j]);
				}
			}
		}
		return done;
	}
	
}
