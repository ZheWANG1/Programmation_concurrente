package programmation_concurrente_tme12;

public class TestRestaurant {
	public static final int NB_CLIENTS_PER_TABLE = 2;
	public static final int NB_TABLES = 10;
	public static final int NB_CLIENTS_PER_GROUPE = 5;
	
	public static void main(String[] args)
	 {
	 	Restaurant resto = new Restaurant(NB_TABLES);
	 	for (int i = 0; i < 15; i++) {
	 		GroupeClients gC = new GroupeClients(NB_CLIENTS_PER_GROUPE, resto);
	 	}
	 }
}