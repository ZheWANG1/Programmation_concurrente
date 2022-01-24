package exam;

import java.util.Random;

public class Patient implements Runnable {
    private int monId;
    private LeCentreMedical monCM;
    private Aile monAile;
    private Random monG = new Random();

    public Patient(int monId, LeCentreMedical monCM, Aile monAile) {
        this.monId = monId;
        this.monCM = monCM;
        this.monAile = monAile;
    }

    public void trace(String s) {
        System.out.println(s);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(monG.nextInt(1000));
            trace("je suis malaaade");
            monCM.entrerDansSas(monId, Aile.ENTREE.porte());
            monCM.sortirDuSas(monId, monAile.porte());
            trace("je suis dans un lit de l’aile " + monAile);
            Thread.sleep(monG.nextInt(10000));
            trace("je suis gueri");
            monCM.entrerDansSas(monId, monAile.porte());
            monCM.sortirDuSas(monId, Aile.SOIGNANTS.porte());
            trace("je suis dehors, merci aux soignants");
            monCM.jeSuisGueri(monId);
        } catch (Exception e) {
            trace("=====+> la cata! je meurs sur " + e);
        }
    }

}
