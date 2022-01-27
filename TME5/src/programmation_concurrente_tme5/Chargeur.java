package programmation_concurrente_tme5;

public class Chargeur implements Runnable {
	private final AleaStock stock;
	private final Chariot chariot;

	public Chargeur(AleaStock stock, Chariot c) {
		this.stock = stock;
		this.chariot = c;
	}

	public void operer() {
		chariot.flagBegin();
		while (stock.getSize() != 0) {
			AleaObjet o = stock.extraire();
			System.out.println("Extracted from stock: " + o);
			chariot.ajouter(o);
			System.out.println("Added to cart: " + o);
		}
		chariot.flagFinished();
	}
	
	@Override
	public void run() {
		operer();
		System.out.println("Loader finished");
	}
}
