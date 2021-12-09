package programmation_concurrente_tme5;

public class AleaStock {
	private final AleaObjet[] objets;
	private int size;
	private static final int POIDS_MAX = 20;
	private static final int POIDS_MIN = 5;

	public AleaStock(int taille) {
		objets = new AleaObjet[taille];
		this.remplir();
	}

	public int getSize() {
		return size;
	}

	/**
	 * Empties the stock, then fills it. It is a synchronized method because the modifications should be done sequentially; however, a more granular approach could be used to fill the stock. We don't do it here, because it is not going to be executed more than once in the program.
	 */
	public synchronized void remplir() {
		size = 0;
		for (int i = 0; i < objets.length; i++) {
			objets[i] = new AleaObjet(POIDS_MIN, POIDS_MAX);
			size++;
		}
	}

	public synchronized boolean estVide() {
		return size == 0;
	}

	public AleaObjet extraire() {
		int objAExtraire;
		synchronized (this) {
			objAExtraire = size - 1; // No other concurrent threads will be able to modify this local value
			size--;
		}
		AleaObjet obj = objets[objAExtraire]; // We can do this outside of the critical section, because no other thread will access the same index (as we are guaranteed that all modifications to the size variable are done sequentially)
		return obj;
	}
}
