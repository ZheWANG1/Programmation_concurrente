public class test {
    public static void main(String []args){
        Salle S1 = new Salle(5,5);
/*
        Groupe g = new Groupe(3,S1);
        Groupe g2 = new Groupe(2,S1);
        g.demandeReserver(S1);
        g2.demandeReserver(S1);
        */
        Thread t1 = new Thread(new Groupe(3, S1));
        Thread t2 = new Thread(new Groupe(2, S1));

        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        S1.afficher();
        //System.out.println(g.getNb());
        //new Thread(new Groupe(2, S1)).start();
        //S1.afficher();
        //new Thread(new Groupe(7, S1)).start();
        //S1.afficher();

    }
}
