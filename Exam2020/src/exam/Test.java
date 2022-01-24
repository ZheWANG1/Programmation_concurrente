package exam;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        ExecutorService e = Executors.newFixedThreadPool(100);

        LeCentreMedical cm = new LeCentreMedical();
        Soignant s1 = new Soignant(101, cm, Aile.A);
        Soignant s2 = new Soignant(102, cm, Aile.A);
        Soignant s3 = new Soignant(103, cm, Aile.B);
        Soignant s4 = new Soignant(104, cm, Aile.B);

        e.execute(s1);
        e.execute(s2);
        e.execute(s3);
        e.execute(s4);

        for(int i=0; i<30; i++){
            Patient p;
            if(i%2 == 0){
                p = new Patient(i, cm, Aile.A);
            }else{
                p = new Patient(i, cm, Aile.B);
            }
            e.execute(p);
        }

        e.shutdown();
    }
}
