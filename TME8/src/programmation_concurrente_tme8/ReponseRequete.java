package programmation_concurrente_tme8;

public class ReponseRequete {
	private Client c;
	private int numReq;
	private int resultat;

	public ReponseRequete(Client c, int numReq, int resultat) {
		super();
		this.c = c;
		this.numReq = numReq;
		this.resultat = resultat;
	}

	@Override
	public String toString() {
		return "ReponseRequete [c=" + c + ", numReq=" + numReq + "]";
	}
	
}
