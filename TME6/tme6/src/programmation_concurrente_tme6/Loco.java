package programmation_concurrente_tme6;

public class Loco implements Runnable {
	private final int id;
	private final SegAccueil sAccueil;
	private final SegTournant sTournant;
	private final PoolHangars pHangars;
	
	public Loco(int id, SegAccueil sA, SegTournant sT, PoolHangars pH) {
		this.id = id;
		pHangars = pH;
		sAccueil = sA;
		sTournant = sT;
	}
	
	public void run() {
		try {
			sAccueil.reserver();
			sTournant.appeler(0);
			sTournant.attendrePositionOK();
			sTournant.entrer(id);
			sAccueil.liberer();
			sTournant.attendrePositionOK();
			pHangars.entrer(sTournant.getPosition(), id);
			sTournant.sortir(id);
		} catch (InterruptedException e) {
			System.out.println("Loco " + id + " interrompue (ne devrait pas arriver)");
		}
	}
}
