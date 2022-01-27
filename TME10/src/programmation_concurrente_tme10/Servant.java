package programmation_concurrente_tme10;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Servant implements Runnable {
	private Requete req;
	private static final int MAX_VAL_REPONSE = 100;
	private static final int FIXED_WAIT = 100;
	private static final int MAX_RANDOM_WAIT = 500;
	private static final Random gen = new Random();
	
	public Servant(Requete r) {
		this.req = r;
	}

	@Override
	public void run() {
		System.out.println("Treating request from " + req.getClient() + " with ID " + req.getId());
		if (req.getId() % 3 != 0) {
			try {
				Thread.sleep(FIXED_WAIT + gen.nextInt(MAX_RANDOM_WAIT));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Infinite loop on request " + req.getId() + " from " + req.getClient());
			while (true) {
				// Infinite loop
			}
		}
		req.getClient().requeteServie(new ReponseRequete(req.getClient(), req.getId(), gen.nextInt(MAX_VAL_REPONSE)));
	}
}
