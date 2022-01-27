package programmation_concurrente_tme12;

import java.util.Random;

public class Client implements Runnable {
	private final GroupeClients gC;
	private Integer numTable;
	private static final Random gen = new Random();
	private static final int MAX_TEMPS_ATTENTE = 2000;

	public Client(GroupeClients gC) {
		this.gC = gC;
	}
	@Override
	public void run() {
		if (Thread.currentThread().isInterrupted()) {
			return;
		}
		numTable = gC.reserver();
		if (Thread.currentThread().isInterrupted()) {
			return;
		}
		try {
			Thread.sleep(gen.nextInt(MAX_TEMPS_ATTENTE));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gC.signalerArrivee();
	}
}