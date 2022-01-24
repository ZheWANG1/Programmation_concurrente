package programmation_concurrente_tme11.question_4.avec_locks;

import java.util.Random;

public class Babouin implements Runnable {
	private static final int DUREE_MAX_TRAVERSEE = 1000;
	private static final int DUREE_MAX_BATIFOLEE = 1000;
	
	private final Position position;
	private final Corde laCorde;
	private final Random gen = new Random();
	private static int compteur = 0;
	private static final Object o = new Object();
	private final int id;
	
	@Override
	public String toString() {
		return "Babouin " + getId();
	}

	public Corde getLaCorde() {
		return laCorde;
	}

	public Babouin(Corde c, Position p) {
		this.laCorde = c;
		this.position = p;
		synchronized (o) {
			this.id = compteur++;
		}
	}

	@Override
	public void run() {
		try {
			batifoler();
			laCorde.acceder(getPosition());
			System.out.println(this.toString() + " a pris la corde.");
			traverser();
			System.out.println(this.toString() + " est arrive.");
			laCorde.lacher(getPosition());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected void traverser() {
		try {
			Thread.sleep(gen.nextInt(DUREE_MAX_TRAVERSEE));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected void batifoler() {
		try {
			Thread.sleep(gen.nextInt(DUREE_MAX_BATIFOLEE));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Position getPosition() {
		return position;
	}

	public int getId() {
		return id;
	}

}
