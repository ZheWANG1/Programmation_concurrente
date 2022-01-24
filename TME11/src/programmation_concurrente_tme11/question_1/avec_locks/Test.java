package programmation_concurrente_tme11.question_1.avec_locks;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args) {
		int nbBabouins;
		final Random gen = new Random();
		final ArrayList<Babouin> babouins = new ArrayList<>();
		final Corde laCorde = new Corde();
		final ExecutorService exec = Executors.newSingleThreadExecutor();
		
		try {
			nbBabouins = Integer.parseInt(args[0]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			nbBabouins = 5;
			System.err.println("The first argument must be the number of babouins; assuming a default value of " + nbBabouins);
		}
		
		for (int i = 0; i < nbBabouins; i++) {
			babouins.add(new Babouin(laCorde, Position.fromInt(gen.nextInt(2))));
			exec.execute(babouins.get(i));
		}
		exec.shutdown();
	}
}
