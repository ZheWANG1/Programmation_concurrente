package programmation_concurrente_tme8;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client implements Runnable {
	private Serveur server;
	private static final int FIXED_WAIT = 100;
	private static final int MAX_RANDOM_WAIT = 500;
	private Random gen = new Random();
	private boolean waitForCompletionOfRequest = false;
	private Lock l = new ReentrantLock();
	private Condition requestCompleted = l.newCondition();
	private int myId;
	private static int counter = 0;
	private final static Object o = new Object();
	
	public Client(Serveur s) {
		synchronized (o) {
			myId = counter++;
		}
		server = s;
	}
	
	@Override
	public void run() {
		boolean interrupted = false;
		for (int i = 0; i < 5; i++) {
			Requete req = new Requete(this, i);
			try {
				server.soumettre(req);
				Thread.sleep(FIXED_WAIT+gen.nextInt(MAX_RANDOM_WAIT));
				l.lock();
				try {
					while (waitForCompletionOfRequest) {
						requestCompleted.await();
					}
					waitForCompletionOfRequest = true;
				} finally {
					l.unlock();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				interrupted = true; // Could be used in the for loop condition; we won't use it because it says that the client *must always* send 5 requests
			}
		}
	}
	
	public void requeteServie(ReponseRequete r) {
		l.lock();
		try {
			waitForCompletionOfRequest = false;
			requestCompleted.signalAll(); // Same as signal() in this case
		}finally {
			l.unlock();
		}
		System.out.println("Received request response: " + r);
	}

	@Override
	public String toString() {
		return "Client " + myId;
	}
}
