package programmation_concurrente_tme11.question_4.avec_semaphores;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Corde {
	private final Semaphore s = new Semaphore(5);
	private final Semaphore mutex = new Semaphore(1);
	private int numBabouins =0;
	
	public void acceder(Position p) throws InterruptedException {
		if (numBabouins == 0) {
			mutex.acquire();
		}
		s.acquire();
		numBabouins++;
	}
	
	public void lacher(Position p) {
		numBabouins--;
		s.release();
		if (numBabouins == 0) {
			mutex.release();
		}
	}
	
	public void accederKong(Position p) throws InterruptedException {
		mutex.acquire();
	}
	
	public void lacherKong(Position p) {
		mutex.release();
	}
}
