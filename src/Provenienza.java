public enum Provenienza {
    NORD(0),
    EST(1),
    SUD(2),
    OVEST(3);

    private final int valore;

    Provenienza(int valore) {
        this.valore = valore;
    }

    public int getValore() {
        return valore;
    }
}
