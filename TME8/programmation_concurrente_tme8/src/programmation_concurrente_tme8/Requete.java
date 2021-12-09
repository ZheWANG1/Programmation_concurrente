package programmation_concurrente_tme8;

public class Requete {
	private Client c;
	private int numReq;
	
	public Requete(Client c, int numReq) {
		super();
		this.c = c;
		this.numReq = numReq;
	}
	
	public int getId() {
		return numReq;
	}
	
	public Client getClient() {
		return c;
	}
}
