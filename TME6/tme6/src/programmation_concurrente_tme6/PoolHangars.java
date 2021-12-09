package programmation_concurrente_tme6;

import java.util.HashMap;
import java.util.Map;

public class PoolHangars {
	private final Map<Integer, Hangar> hangars = new HashMap<Integer, Hangar>();
	
	public synchronized void entrer(int hangar, int id) {
		Hangar h = new Hangar(hangar);
		h.occupy();
		System.out.println("[TRAIN " + id + "]: ENTERING hangar " + hangar);
		hangars.put(id,h);
	}
	
	public synchronized int chercherDispo() {
		for (int i = 1; true; i++) { // Infinite loop until we find a free spot
			if (!hangars.containsKey(i) || !hangars.get(i).isOccupied()) { // A free spot is an unexisting hangar, or an existing free hangar
				System.out.println("[Pool]: First available spot is " + i);
				return i;
			}
		}
	}
}
