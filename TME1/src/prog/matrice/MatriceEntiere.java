package prog.matrice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class MatriceEntiere {

	private int lignes;
	private int colonnes;
	private int[][] mat;

	public MatriceEntiere(int lignes, int colonnes) {
		this.lignes = lignes;
		this.colonnes = colonnes;
		mat = new int[lignes][colonnes];

	}

	public MatriceEntiere(File fichier) throws NumberFormatException, IOException {
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(fichier));
			lignes = Integer.valueOf(in.readLine());
			colonnes = Integer.valueOf(in.readLine());
			mat = new int[lignes][colonnes];
			for (int i = 0; i < lignes; i++) {
				String[] l = in.readLine().split(" ");
				for (int j = 0; j < colonnes; j++) {
					mat[i][j] = Integer.valueOf(l[j]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public int getElem(int i, int j) {
		if (i >= 0 && i < lignes && j >= 0 && j < colonnes)
			return mat[i][j];
		return -1;
	}

	public void setElem(int i, int j, int val) {
		if (i >= 0 && i < lignes && j >= 0 && j < colonnes) {
			mat[i][j] = val;
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < lignes; i++) {
			for (int j = 0; j < colonnes; j++) {
				sb.append(mat[i][j] + " ");
			}
			sb.append("\n");

		}
		return sb.toString();
	}

	public void initialisation() {
		this.lignes = 0;
		this.colonnes = 0;
		mat = new int[lignes][colonnes];
	}

	public MatriceEntiere transposee() {
		MatriceEntiere m = new MatriceEntiere(colonnes, lignes);
		for (int i = 0; i < lignes; i++) {
			for (int j = 0; j < colonnes; j++) {
				m.setElem(j, i, this.getElem(i, j));
			}
		}
		return m;
	}

	public MatriceEntiere somme(MatriceEntiere m2) throws TaillesNonConcordantesException {
		if (this.lignes != m2.lignes || this.colonnes != m2.colonnes)
			throw new TaillesNonConcordantesException("Tailles non concordantes !");
		else {
			MatriceEntiere resultat = new MatriceEntiere(lignes, colonnes);
			for (int i = 0; i < lignes; i++) {
				for (int j = 0; j < colonnes; j++) {
					resultat.setElem(i, j, this.getElem(i, j) + m2.getElem(i, j));
				}
			}
			return resultat;
		}
	}
	
	public MatriceEntiere scalaire(int scal) {
		MatriceEntiere resultat = new MatriceEntiere(lignes, colonnes);
		for (int i = 0; i < lignes; i++) {
			for (int j = 0; j < colonnes; j++) {
				resultat.setElem(i, j, this.getElem(i, j)*scal);
			}
		}
		return resultat;
	}

	public MatriceEntiere produit(MatriceEntiere m2) throws TaillesNonConcordantesException {
		if (this.colonnes != m2.lignes)
			throw new TaillesNonConcordantesException("Tailles non concordantes !");
		else {
			MatriceEntiere resultat = new MatriceEntiere(lignes, m2.colonnes);
			for (int i = 0; i < lignes; i++) {
				for (int j = 0; j < m2.colonnes; j++) {
					for (int k = 0; k < colonnes; k++) {
						resultat.setElem(i, j, resultat.getElem(i, j) + this.getElem(i, k) * m2.getElem(k, j));
					}
				}
			}
			return resultat;
		}
	}
}
