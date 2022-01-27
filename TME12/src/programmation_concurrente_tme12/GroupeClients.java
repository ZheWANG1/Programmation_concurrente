package programmation_concurrente_tme12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GroupeClients {
	private static int compteur = 0;
	private static final Object o = new Object();
	private static final Object out = new Object();
	private int id;
	private Thread[] tC;
	private final Restaurant resto;
	private Integer numTable;
	private final ThreadGroup groupeThreads;
	private int nbArrivees = 0;
	private Lock l = new ReentrantLock();

	public GroupeClients(int number, Restaurant resto) {
		this.resto = resto;
		synchronized (o) {
			this.id = ++compteur;
		}
		groupeThreads = new ThreadGroup(Integer.toString(id));
		tC = new Thread[number];
		for (int i = 0; i < number; i++) {
			Client c = new Client(this);
			tC[i] = new Thread(c);
			tC[i].start();
		}
	}

	public int getNbClients() {
		return tC.length;
	}

	public int getId() {
		return this.id;
	}

	public synchronized Integer reserver() {
		if (numTable == null) {
			numTable = resto.reserver(this);
			if (numTable == null) {
				groupeThreads.interrupt();
				System.out.println("La reservation pour le groupe de clients " + Integer.toString(getId()) + " a echoue.");
			}
		}
		return numTable;
	}

	public void signalerArrivee() {
		l.lock();
		try {
			nbArrivees++;
			if (nbArrivees == tC.length) {
				synchronized (out) {
					System.out.println("Le groupe de clients " + Integer.toString(id) + " est arrive.");
					System.out.println("Tables du groupe (Reservation=" + resto.getReservation(numTable).getId() + "):");
					for (int i = 0; i < resto.getReservation(numTable).getTables().length; i++) {
						System.out.println(resto.getReservation(numTable).getTables()[i]);
					}
				}
			}
		} finally {
			l.unlock();
		}
	}
}