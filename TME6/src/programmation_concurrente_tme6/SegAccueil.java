package programmation_concurrente_tme6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SegAccueil {
	private final Lock l = new ReentrantLock();
	
	public void reserver() {
		l.lock();
		System.out.println("[Reception segment]: RESERVED");
	}
	
	public void liberer() {
		l.unlock();
		System.out.println("[Reception segment]: FREED");
	}
}
