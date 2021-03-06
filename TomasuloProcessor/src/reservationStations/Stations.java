package reservationStations;

import java.util.LinkedList;

public class Stations {

	/*
	 * Class to combine all stations with type arrays and number of stations for
	 * each type Type numbers and indices: 0) Add/Subtract 1) Conditional Branch
	 * 2) Jump and Link 3) Jump 4) Load 5) Multiply 6) Nand 7) Return 8) Store
	 */

	int numStations;
	ReservationStation[][] stations;
	int[] capacities, nCycles;

	public Stations(int size, int cap[], int nCycles[]) {
		this.nCycles = nCycles;
		numStations = size;
		capacities = cap;
		stations = new ReservationStation[numStations][];
		for (int i = 0; i < numStations; i++) {
			// Replace ReservationStation with actual type
			stations[i] = new ReservationStation[capacities[i]];
			for (int j = 0; j < capacities[i]; j++) {
				switch (i) {
				case 0:
					stations[i][j] = new AddSubRS(nCycles[i]); break;
				case 1:
					stations[i][j] = new BEQRS(nCycles[i]); break;
				case 2:
					stations[i][j] = new JALRRS(nCycles[i]); break;
				case 3:
					stations[i][j] = new JMPRS(nCycles[i]); break;
				case 4:
					stations[i][j] = new LoadRS(nCycles[i]); break;
				case 5:
					stations[i][j] = new MultiplyRS(nCycles[i]); break;
				case 6:
					stations[i][j] = new NandRS(nCycles[i]); break;
				case 7:
					stations[i][j] = new RETRS(nCycles[i]); break;
				case 8:
					stations[i][j] = new StoreRS(nCycles[i]); break;
				}
			}
		}
	}

	public LinkedList<ReservationStation> runCycle() {
		LinkedList<ReservationStation> done = new LinkedList<ReservationStation>();
		for (int i = 0; i < stations.length; i++) {
			for (int j = 0; j < stations[i].length; j++) {
				if (stations[i][j].isBusy() && stations[i][j].exec()) {
					done.add(stations[i][j]);
				}
			}
		}
		return done;
	}

	public int checkFree(int index) {
		for (int i = 0; i < stations[index].length; i++)
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
