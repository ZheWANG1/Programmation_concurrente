package prog.matrice;

import java.io.File;
import java.io.IOException;

public class test {

	public static void main(String[] args) throws NumberFormatException, IOException {
		MatriceEntiere p1 = new MatriceEntiere(new File("data/donnees_produit1"));
		MatriceEntiere p2 = new MatriceEntiere(new File("data/donnees_produit2"));
		MatriceEntiere s1 = new MatriceEntiere(new File("data/donnees_somme1"));
		MatriceEntiere s2 = new MatriceEntiere(new File("data/donnees_somme2"));
		
		try {
			System.out.println(s1.somme(s2).toString());
		} catch (TaillesNonConcordantesException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(p1.produit(p2).toString());
		} catch (TaillesNonConcordantesException e) {
			e.printStackTrace();
		}
		
		System.out.println(p1.scalaire(2).toString());
		p1.initialisation();
		System.out.println(p1.toString());
		
		System.out.println(p2.transposee().toString());
	}

}
