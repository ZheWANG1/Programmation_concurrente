package programmation_concurrente_tme6;

public class Hangar {
	private final int id;
	private boolean occupied = false;
	
	public Hangar(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public synchronized void occupy() {
		occupied = true;
	}
	
	public synchronized void free() {
		occupied = false;
	}
	
	public synchronized boolean isOccupied() {
		return occupied;
	}
}
