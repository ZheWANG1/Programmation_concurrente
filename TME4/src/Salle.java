
public class Salle {
    private int nbRangs;
    private int nbPlaceParRang;
    private boolean placesLibres[][];
    private int nbPlacesLibres;

    public Salle(int nbRangs, int nbPlaceParRang) {

        this.nbRangs = nbRangs;
        this.nbPlaceParRang = nbPlaceParRang;
        this.placesLibres = new boolean[nbRangs][nbPlaceParRang];
        this.nbPlacesLibres = nbRangs * nbPlaceParRang;

        for (int i = 0; i < nbRangs; i++) {
            for (int j = 0; j < nbPlaceParRang; j++) {
                placesLibres[i][j] = true;
            }
        }
    }

    public int getNbRangs() {
        return nbRangs;
    }

    public void setNbRangs(int nbRangs) {
        this.nbRangs = nbRangs;
    }

    public int getNbPlaceParRang() {
        return nbPlaceParRang;
    }

    public void setNbPlaceParRang(int nbPlaceParRang) {
        this.nbPlaceParRang = nbPlaceParRang;
    }

    public int getNbPlaceLibre() {
        return this.nbPlacesLibres;
    }

    public synchronized boolean capaciteOK(int n) {
        return getNbPlaceLibre() > n;
    }

    public synchronized int nContiguesAuRangI(int n, int i) {
        int j = 0;
        int cpt = 0; // compter le nombre de places libres consecutifs commencant placeLibre[i][j]
        while (j <= getNbPlaceParRang() - n) {
            cpt = 0;

            while (placesLibres[i][j] && cpt < n) {
                cpt++;
                j++;
                if (j == getNbPlaceParRang())
                    break;
            }
            if (cpt == n) {
                // System.out.println(j-cpt);
                return j - cpt;
            }
            j++;
        }

        return -1;
    }

    public synchronized boolean reserverContigues(int n) {

        boolean result = false;
        int i = 0;

        if (capaciteOK(n)) {
            while (i < getNbRangs() && !result) {
                int j = nContiguesAuRangI(n, i);
                if (j >= 0) {
                    result = true;
                    for (int k = j; k < j + n; k++) {
                        // System.out.print(k+" ");
                        placesLibres[i][k] = false;
                    }
                }
                i++;
            }
        }
        return result;
    }

    public synchronized boolean reserver(int n) {
        if (!capaciteOK(n))
            return false;
        if (reserverContigues(n))
            return true;

        int i = 0, j = 0;
        while (i < nbRangs && n > 0) {
            while (j < nbPlaceParRang && n > 0) {
                if (placesLibres[i][j]) {
                    placesLibres[i][j] = false;
                    n--;
                }
                j++;
            }
            i++;
        }
        return true;
    }

    public void afficher() {
        for (int i = 0; i < nbRangs; i++) {
            for (int j = 0; j < nbPlaceParRang; j++) {
                System.out.print(placesLibres[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
