package programmation_concurrente_tme8;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Serveur implements Runnable {
	private ArrayBlockingQueue<Requete> queue = new ArrayBlockingQueue<>(3);
	private Lock l = new ReentrantLock();
	private Condition requestArrived = l.newCondition();

	@Override
	public void run() {
		try {
			while (true) {
				attendreRequete();
				traiterRequete();
			}
		} catch (InterruptedException e) {
			System.out.println("Serveur interrompu");
		}
	}

	public void attendreRequete() throws InterruptedException {
		l.lock();
		try {
			while (queue.isEmpty()) {
				requestArrived.await();
			}
		} finally {
			l.unlock();
		}
	}

	public void traiterRequete() {
		try {
			Requete requete = queue.take();
			Runnable servant = new Servant(requete);
			new Thread(servant).start(); // The treatment is done in a new Thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void soumettre(Requete r) throws InterruptedException {
		queue.put(r); // Peut bloquer le thread
		l.lock();
		try {
			requestArrived.signalAll(); // Same as signal()
		} finally {
			l.unlock();
		}
	}

}
