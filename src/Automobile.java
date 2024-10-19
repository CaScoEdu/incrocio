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
            
            //Thread.sleep((int)(Math.random() * 5000) + 1000); // Sleep tra 500ms e 2500ms          
            INCROCIO.raggiungi(DIREZIONE);

            // Aggiungi un ritardo casuale prima di attraversare l'incrocio
            //Thread.sleep((int)(Math.random() * 5000) + 1000); // Sleep tra 500ms e 2500ms          
         
            INCROCIO.attraversa(DIREZIONE);
            Thread.sleep(1000); // Simula il tempo necessario per attraversare l'incrocio
            INCROCIO.esci(DIREZIONE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

