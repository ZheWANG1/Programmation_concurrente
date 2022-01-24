package programmation_concurrente_tme11.question_2.avec_locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Corde {
	private final Lock l = new ReentrantLock();
	private final Condition cordeDisponible = l.newCondition();
	private int nbBabouins = 0;

	public void acceder(Position p) throws InterruptedException {
		l.lock();
		try {
			while (nbBabouins == 5) {
				cordeDisponible.await();
			}
			nbBabouins++;
		} finally {
			l.unlock();
		}
	}

	public void lacher(Position p) {
		l.lock();
		try {
			nbBabouins--;
			if (nbBabouins == 4) {
				cordeDisponible.signal(); // We just need one Babouin to cross
			}
		} finally {
			l.unlock();
		}
	}
}
