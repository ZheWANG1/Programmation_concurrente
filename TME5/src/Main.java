package programmation_concurrente_tme5;

public class Main {

	public static void main(String[] args) {
		AleaStock s = new AleaStock(100); // 100 objects in stock
		Chariot c = new Chariot(40, 5); // Max weight: 40 kg; max number of objects: 5 objects
		Chargeur c1 = new Chargeur(s, c);
		Chargeur c2 =  new Chargeur(s, c);
		Dechargeur d = new Dechargeur(c);
		
		Thread t1 = new Thread(c1);
		Thread t2 = new Thread(c2);
		Thread t3 = new Thread(d);
		t1.start();
		t2.start();
		t3.start();
	}

}
