package programmation_concurrente_tme12;

public class Table {
	private final int id;
	private final int capacite;
	private boolean isReserve = false;

	public Table(int id, int capacite) {
		this.id = id;
		this.capacite = capacite;
	}

	public int getId() {
		return id;
	}

	public boolean isReserve() {
		return isReserve;
	}

	public void reserver() {
		isReserve = true;
	}

	public int getCapacite() {
		return this.capacite;
	}
	
	@Override
	public String toString() {
		return "Table " + Integer.toString(id);
	}
}