package programmation_concurrente_tme5;

import java.util.Random;

public class AleaObjet {
	private int poids;
	private static int nbTotal = 0;
	private static final Object moniteurNbTotal = new Object();
	private final int id;

	public AleaObjet(int min, int max) {
		super();
		synchronized (moniteurNbTotal) {
			this.id = nbTotal++;
		}
		this.poids = new Random().nextInt(max-min)+min;
	}
	
	public int getPoids() {
		return poids;
	}

	@Override
	public String toString() {
		return "AleaObjet [poids=" + poids + ", id=" + id + "]";
	}
}
