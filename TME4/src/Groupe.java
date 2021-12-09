
public class Groupe implements Runnable {
    private final int nb;
    private int id;
    private Salle salle;
    private static int cpt = 0;

    public Groupe(int nb, Salle salle) {
        this.nb = nb;
        this.salle = salle;
        this.id = cpt;
        cpt++;
    }

    public int getNb() {
        return nb;
    }

    public void demandeReserver(Salle salle) {
        salle.reserver(getNb());
    }

    public void run() {
        demandeReserver(salle);
    }

}
