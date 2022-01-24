package exam;

import java.util.Random;

public class Soignant implements Runnable {
    private int monId;
    private LeCentreMedical monCM;
    private Aile monAile;
    private Random monG = new Random();

    public Soignant(int monId, LeCentreMedical monCM, Aile monAile) {
        this.monId = monId;
        this.monCM = monCM;
        this.monAile = monAile;
    }

    public void trace(String s) {
        System.out.println(s);
    }

    public void run() {
        try {
            trace("je rentre dans le centre medical");
            monCM.soignantEntreDansCentre(monId);
            while (!monCM.fermerLeCentre()) {
                Thread.sleep(monG.nextInt(2000));
                trace("je vais aller m’occuper de mes patients dans l’aile " + monAile);
                monCM.entrerPrioritaireDansSas(monId, Aile.SOIGNANTS.porte());
                monCM.sortirDuSas(monId, monAile.porte());
                trace("je m’occupe de mes patients dans l’aile " + monAile);
                Thread.sleep(monG.nextInt(200));
                trace("je reviens me reposer dans l’aile des soignants");
                monCM.entrerPrioritaireDansSas(monId, monAile.porte());
                monCM.sortirDuSas(monId, Aile.SOIGNANTS.porte());
            }
            monCM.soignantSortDuCentre(monId);
            trace("j’ai quitte le centre medical");
        } catch (Exception e) {
            trace("=====+> la cata! je meurs sur " + e);
        }
    }
}
