package programmation_concurrente_tme10;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Serveur implements Runnable {
	private final ArrayBlockingQueue<Requete> queue;
	private final ThreadGroup servants = new ThreadGroup("servants");
	private final CustomThreadFactory servantsFactory = new CustomThreadFactory(servants);
	private final ExecutorService poolThreadsServants = Executors.newFixedThreadPool(3, servantsFactory);
	private final ExecutorService poolThreadsClients;
	
	public Serveur(ArrayBlockingQueue<Requete> abq, ExecutorService poolClients) {
		this.queue = abq;
		this.poolThreadsClients = poolClients;
	}

	@Override
	public void run() {
		while (! poolThreadsClients.isTerminated()) {
			try {
				Requete r = queue.take();
				Servant s = new Servant(r);
				poolThreadsServants.execute(s);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
