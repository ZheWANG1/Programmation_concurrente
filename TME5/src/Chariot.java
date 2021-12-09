package programmation_concurrente_tme5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chariot {
	private final int poidsMax, nbMax;
	private final List<AleaObjet> objs = new ArrayList<>();
	private final Lock mutex = new ReentrantLock();
	private final Condition notPlein = mutex.newCondition();
	private final Condition notVide = mutex.newCondition();
	private boolean isFinished = false;
	private int nbChargeurs = 0;

	/**
	 * Tells if the chariot is still in use
	 * No need to synchronize it, because we are only doing a read
	 * @return If the chariot is still in use (true) or not (false)
	 */
	public boolean isFinished() {
		return isFinished;
	}
	
	public synchronized void flagBegin() {
		this.nbChargeurs++;
	}
	
	/**
	 * Indicates that we don't need to use this chariot anymore
	 */
	public synchronized void flagFinished() {
		nbChargeurs--;
		if (this.nbChargeurs==0) {
			this.isFinished = true;
		}
	}

	public Chariot(int poidsMax, int nbMax) {
		super();
		this.poidsMax = poidsMax;
		this.nbMax = nbMax;
	}

	public void ajouter(AleaObjet e) {
		mutex.lock();
		try {
			while (plein(e)) {
				System.out.println("Cart is full, going to sleep...");
				notPlein.await();
				System.out.println("Cart is not full now");
			}
			this.objs.add(e);
			notVide.signalAll(); // We could also use signal()
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} finally {
			mutex.unlock();
		}
	}

	public AleaObjet decharger() {
		AleaObjet o = null;
		mutex.lock();
		try {
			while (objs.isEmpty()) {
				System.out.println("Cart is empty, going to sleep...");
				notVide.await();
				System.out.println("Cart is not empty now");
			}
			o = this.objs.remove(objs.size()-1);
			notPlein.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.unlock();
		}
		return o;
	}

	/**
	 * Checks if, with the selected object, the chariot would be full. It does not need to be synchronized, because it is invoked from mutual exclusion regions only.
	 * @param o The object
	 * @return Whether the chariot would be full or not
	 */
	public boolean plein(AleaObjet o) {
		int sumPoids = objs.stream().mapToInt(obj -> obj.getPoids()).sum() + o.getPoids();
		if (sumPoids > poidsMax || (objs.size() + 1) > nbMax) {
			return true;
		} else {
			return false;
		}
	}
}
