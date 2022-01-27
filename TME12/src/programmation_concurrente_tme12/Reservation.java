package programmation_concurrente_tme12;

public class Reservation {
	private Table[] tables;
	private static int compteur = 0;
	private static final Object o = new Object();
	private int id;
	public Reservation(Table[] tables) {
		this.tables = tables;
		synchronized (o) {
			this.id = ++compteur;
		}
		for (int i = 0; i < tables.length; i++) {
			tables[i].reserver();
		}
	}

	public Table[] getTables() {
		return tables;
	}

	public int getId() {
		return id;
	}
}