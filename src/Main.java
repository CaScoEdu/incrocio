public class Main {

    public static void main(String[] args) {
        /*
         * sono sufficienti 4 automobili nella 4 direzioni diverse 
         * per simulare un deadlock, ma è possibile sperimentare
         * con un numero diverso di automobili
         */
        final int NUM_AUTOMOBILI = 3;
        final Provenienza[] PROVENIENZE = { Provenienza.NORD, Provenienza.EST, Provenienza.SUD, Provenienza.OVEST }; // Definito all'interno del main

        final Incrocio INCROCIO = new Incrocio(); // Costante final per l'oggetto incrocio
        final Thread[] AUTOMOBILI = new Thread[NUM_AUTOMOBILI]; // Dichiarato final

        for (int i = 0; i < NUM_AUTOMOBILI; i++) {
            // Automobili distribuite nelle 4 direzioni ciclicamente
            AUTOMOBILI[i] = new Thread(new Automobile(PROVENIENZE[i % 4], INCROCIO));
            AUTOMOBILI[i].start();
        }

        // Attendi il completamento di tutte le automobili
        for (int i = 0; i < NUM_AUTOMOBILI; i++) { 
            try {
                AUTOMOBILI[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Tutte le automobili hanno attraversato l'incrocio.");
    }
}

