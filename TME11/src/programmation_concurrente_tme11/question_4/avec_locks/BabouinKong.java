package programmation_concurrente_tme11.question_4.avec_locks;

public class BabouinKong extends Babouin {

	public BabouinKong(Corde c, Position p) {
		super(c, p);
	}
	
	@Override
	public void run() {
		try {
			super.batifoler();
			super.getLaCorde().accederKong(super.getPosition());
			System.out.println(this.toString() + " a pris la corde.");
			super.traverser();
			System.out.println(this.toString() + " est arrive.");
			super.getLaCorde().lacherKong(super.getPosition());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "BabouinKong " + super.getId();
	}
}
