package programmation_concurrente_tme10;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestProgram {
	private final static int NB_CLIENTS = 10;

	public static void main(String[] args) {
		ArrayBlockingQueue<Requete> abq = new ArrayBlockingQueue<Requete>(3);
		
		ThreadGroup clients = new ThreadGroup("clients");
		CustomThreadFactory clientsFactory = new CustomThreadFactory(clients);
		ExecutorService poolClients = Executors.newFixedThreadPool(NB_CLIENTS, clientsFactory);
		
		Serveur server = new Serveur(abq, poolClients);
		Thread serverThread = new Thread(server);
		serverThread.start();
		
		for (int i = 0; i < NB_CLIENTS; i++) {
			Client c = new Client(abq);
			poolClients.execute(c);
		}
		
		poolClients.shutdown();
	}

}
