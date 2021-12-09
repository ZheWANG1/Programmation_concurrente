package programmation_concurrente_tme8;

import java.util.Iterator;

public class TestProgram {
	private final static int NB_CLIENTS = 100;

	public static void main(String[] args) {
		Serveur server = new Serveur();
		Thread serverThread = new Thread(server);
		serverThread.start();
		
		Client[] clients = new Client[NB_CLIENTS];
		Thread[] clientsThreads = new Thread[NB_CLIENTS];
		
		for (int i = 0; i < NB_CLIENTS; i++) {
			clients[i] = new Client(server);
			clientsThreads[i] = new Thread(clients[i]);
			clientsThreads[i].start();
		}
	}

}
