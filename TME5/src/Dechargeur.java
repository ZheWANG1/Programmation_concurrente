package programmation_concurrente_tme5;

public class Dechargeur implements Runnable {
	private Chariot chariot;

	public Dechargeur(Chariot chariot) {
		super();
		this.chariot = chariot;
	}
	
	public void operer() {
		while (!chariot.isFinished()) {
			AleaObjet o = chariot.decharger();
			System.out.println("Extracted from cart: " + o);
		}
	}
	
	@Override
	public void run() {
		operer();
		System.out.println("Unloader finished");
	}
}
