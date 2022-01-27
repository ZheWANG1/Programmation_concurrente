package programmation_concurrente_tme12;

import java.util.List;
import java.util.ArrayList;

public class Restaurant {
	private Table[] tables;
	private Reservation[] reservations;
	private static final Object mutex = new Object();

	public Restaurant(int nbTables) {
		tables = new Table[nbTables];
		for (int i = 0; i < tables.length; i++) {
			tables[i] = new Table(i, TestRestaurant.NB_CLIENTS_PER_TABLE);
		}
		reservations = new Reservation[0];
	}

	public synchronized Integer reserver(GroupeClients gC) {
		for (int i = 0; i < tables.length; i++) {
			if (!tables[i].isReserve()) {
				List<Table> tablesReservation = new ArrayList<>();
				int restantAReserver = gC.getNbClients();
				for (int j = i; j < tables.length; j++) {
					synchronized(mutex) {
						if (!tables[j].isReserve()) {
							restantAReserver -= tables[j].getCapacite(); // Peut devenir un numero negatif, mais ceci n'importe pas
							tablesReservation.add((Table) tables[j]);
							if (restantAReserver <= 0) {
								Table[] arrTablesRes = tablesReservation.toArray(new Table[0]);
								Reservation res = new Reservation(arrTablesRes);
								Reservation[] copyRes = new Reservation[reservations.length + 1];
								for (int k = 0; k < reservations.length; k++) {
									copyRes[k] = reservations[k];
								}
								copyRes[copyRes.length - 1] = res;
								reservations = copyRes;
								return res.getId();
							}
						}
					}
				}
			}
		}
		return null;
	}

	public Reservation getReservation(int id) {
		return reservations[id-1];
	}
}