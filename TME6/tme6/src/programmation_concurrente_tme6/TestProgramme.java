package programmation_concurrente_tme6;

public class TestProgramme {
	public static void main(String[] args) {
		PoolHangars poolHangars = new PoolHangars();
		SegAccueil segAccueil = new SegAccueil();
		SegTournant segTournant = new SegTournant(poolHangars);
		new Thread(segTournant).start();
		for (int i = 0; i < 17; i++) {
			Loco l = new Loco(i+1, segAccueil, segTournant, poolHangars);
			new Thread(l).start();
		}
	}
}
