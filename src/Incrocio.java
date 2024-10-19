public class Incrocio {
    private final boolean LIBERO = true;
    private final boolean OCCUPATO = false;
    private final boolean[] ACCESSO = new boolean[4]; // Array che rappresenta se una provenienza è occupata
    private boolean attraversamento = LIBERO;; 

    public Incrocio(){
        for (int i = 0; i <4; i++)
            ACCESSO[i] = LIBERO;
    }

    public synchronized void raggiungi(final Provenienza PROVENIENZA) throws InterruptedException {
        
        while (ACCESSO[PROVENIENZA.getValore()] == OCCUPATO) {
            wait(); // Aspetta finché l'accesso è occupato
        }
        ACCESSO[PROVENIENZA.getValore()] = OCCUPATO;
        System.out.println("Automobile da " + PROVENIENZA + " ha raggiunto l'incrocio");
    }

    public synchronized void attraversa(final Provenienza PROVENIENZA) throws InterruptedException {
        /* 
         * si suppone che le automobili procedano dritte o girino a sinistra
         * all'incrocio. Pertanto devono assicurarsi che non ci siano auto alla loro
         * destra alle quali debbano dare precedenza
         */
        final int INDICE_PROVENIENZA_DESTRA = (PROVENIENZA.getValore() + 3) % 4; // Provenienza alla destra
        while (ACCESSO[INDICE_PROVENIENZA_DESTRA] == OCCUPATO || attraversamento == OCCUPATO) {
            wait(); // Aspetta finché ci sono auto provenienti da destra o in fase di attraversamento
        }
        ACCESSO[PROVENIENZA.getValore()] = LIBERO;
        attraversamento = OCCUPATO;
        notifyAll();
        System.out.println("Automobile da " + PROVENIENZA + " sta attraversando l'incrocio");
    }

    public synchronized void esci(final Provenienza PROVENIENZA ) {
        attraversamento = true;
        notifyAll(); // Notifica tutte le automobili in attesa
        System.out.println("Automobile da " + PROVENIENZA + " è uscita dall'incrocio");
    }
}
