package programmation_concurrente_tme11.question_3.avec_semaphores;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Corde {
	private final Semaphore s = new Semaphore(5);
	
	public void acceder(Position p) throws InterruptedException {
		s.acquire();
	}
	
	public void lacher(Position p) {
		s.release();
	}
}
