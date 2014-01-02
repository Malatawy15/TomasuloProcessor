package reservationStations;

import java.util.LinkedList;

public class Stations {

	/* 
	 * Class to combine all stations with type arrays and number of stations for each type
	 * Type numbers and indices:
	 *	0) Add/Subtract
	 *	1) Conditional Branch
	 *	2) Jump and Link
	 *	3) Jump
	 *	4) Load
	 *	5) Multiply
	 *	6) Nand
	 *	7) Return
	 *	8) Store
	 */
	
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
	
	public int checkFree(int index){
		for (int i=0;i<stations[index].length;i++)
			if (!stations[index][i].isBusy())
				return i;
		return -1;
	}

	public int getNumStations() {
		return numStations;
	}

	public void setNumStations(int numStations) {
		this.numStations = numStations;
	}

	public ReservationStation[][] getStations() {
		return stations;
	}

	public void setStations(ReservationStation[][] stations) {
		this.stations = stations;
	}

	public int[] getCapacities() {
		return capacities;
	}

	public void setCapacities(int[] capacities) {
		this.capacities = capacities;
	}
	
}
