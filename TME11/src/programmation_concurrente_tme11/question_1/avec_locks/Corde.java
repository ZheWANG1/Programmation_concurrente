package programmation_concurrente_tme11.question_1.avec_locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Corde {
	private final Lock l = new ReentrantLock();
	
	public void acceder(Position p) throws InterruptedException {
		l.lock();
	}
	
	public void lacher(Position p) {
		l.unlock();
	}
}
