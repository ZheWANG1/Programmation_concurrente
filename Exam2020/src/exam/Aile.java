package exam;

public enum Aile {
    ENTREE(0), A(1), SOIGNANTS(2), B(3);

    private Aile(int porte) {
        noPorte = porte;
    }

    private final int noPorte;

    public int porte() {
        return noPorte;
    }
}
