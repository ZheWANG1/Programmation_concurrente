package exam;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LeCentreMedical {

    private final static int CAPACITE_CENTRE = 10;
    private final Map<Integer, Integer> soignantMap = new HashMap<>();
    private final Lock lock = new ReentrantLock();
    private final Condition patientlibre = lock.newCondition();
    private final Condition soignantlibre = lock.newCondition();
    private static final Object o = new Object();
    private boolean occupe = false;
    private boolean premierpatient = false;
    private int soignantattent = 0;
    private int nbSoignant = 0;
    private int nbPatient = 0;

    public void soignantEntreDansCentre(int monId) {
        soignantMap.put(monId, 2);
        nbSoignant++;
    }

    public void soignantSortDuCentre(int monId) {
        soignantMap.remove(monId);
        nbSoignant--;
    }

    public void entrerDansSas(int monId, int porte) throws InterruptedException {
        lock.lock();
        try {
            while (occupe || soignantattent > 0 || nbPatient >= CAPACITE_CENTRE) {
                patientlibre.await();
                if (porte != 0 && !occupe && soignantattent == 0)
                    break;
            }
            occupe = true;
            if (porte == 0) {
                nbPatient++;
            }
        } finally {
            lock.unlock();
        }
    }

    public void sortirDuSas(int monId, int porte) {
        lock.lock();
        try {
            if (soignantMap.containsKey(monId)) {
                soignantMap.put(monId, porte);
            }
            occupe = false;
            System.out.println("=======");
            System.out.println(soignantattent + " " + nbPatient + " " + monId);
            System.out.println("=======");
            if (soignantattent == 0) {
                patientlibre.signalAll();
            }
            soignantlibre.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void jeSuisGueri(int monId) {
        nbPatient--;
    }

    public boolean fermerLeCentre() {
        if (nbPatient > 0) {
            premierpatient = true;
        }
        if (premierpatient)
            return nbPatient == 0;
        return false;
    }

    public void entrerPrioritaireDansSas(int qui, int viaPorte) throws InterruptedException {
        synchronized (o) {
            soignantattent++;
        }
        lock.lock();
        try {
            while (occupe) {
                soignantlibre.await();
            }
            occupe = true;
            soignantMap.put(qui, viaPorte);
        } finally {
            lock.unlock();
            synchronized (o) {
                soignantattent--;
            }
        }
    }
}
