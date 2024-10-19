public class Automobile implements Runnable {
    private final Provenienza DIREZIONE; // Costante final
    private final Incrocio INCROCIO;   // Costante final

    public Automobile(Provenienza direzione, Incrocio incrocio) {
        this.DIREZIONE = direzione;
        this.INCROCIO = incrocio;
    }

    @Override
    public void run() {
        try {
            
            INCROCIO.raggiungi(DIREZIONE);

            /* con questo ritardo il ritardo, è probabile
            che l'incrocio sia occupato in tutte le direzioni e che quindi
            le automobili finiscano in una situazione di deadlock
            */
            Thread.sleep((int)(Math.random() * 5000) + 1000); // Sleep tra 500ms e 2500ms          
            INCROCIO.attraversa(DIREZIONE);
            Thread.sleep(1000); // Simula il tempo necessario per attraversare l'incrocio
            INCROCIO.esci(DIREZIONE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

