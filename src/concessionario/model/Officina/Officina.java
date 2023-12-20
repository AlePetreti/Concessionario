package concessionario.model.Officina;

import concessionario.model.assicurazione.LivelloDanni;

public class Officina {

    // Funzione principale per gestire la riparazione in base al livello di danni
    public void gestisciRiparazione(LivelloDanni livelloDanni) {
        switch (livelloDanni) {
            case DANNO_GRAVE:
                riparaVeicolo();
                break;
            case DANNO_LIEVE:
                riparazioneLieveVeicolo();
                break;
            default:
                System.out.println("Nessuna riparazione necessaria.");
        }
    }

    // Funzione per riparare un veicolo con DANNO_GRAVE
    public void riparaVeicolo() {
        System.out.println("Il veicolo durante il noleggio ha subito danni gravi, verrà portato in officina per essere riparato.");

        // Attendi 5 secondi
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double costoRiparazione = Math.random() * (1000 - 250) + 250;
        System.out.println("Il veicolo è stato riparato dall'officina, il costo ammonta a: " + String.format("%.2f", costoRiparazione) + " euro.");

    }

    // Funzione per riparare un veicolo con DANNO_LIEVE
    public void riparazioneLieveVeicolo() {
        System.out.println("Il veicolo durante il noleggio ha subito danni lievi, verrà portato in officina per essere riparato.");

        // Attendi 5 secondi
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        double costoRiparazione = Math.random() * (250 - 50) + 50;
        System.out.println("Il veicolo è stato riparato dall'officina, il costo ammonta a: " + String.format("%.2f", costoRiparazione) + " euro.");
    }

    // Funzione per eseguire un tagliando casuale
    public void tagliando() {
        
        boolean eseguiTagliando = Math.random() < 0.8 ;

        if (eseguiTagliando) {
            System.out.println("Eseguito tagliando.");
        } else {
            System.out.println("Nessun tagliando eseguito.");
        }
    }
}
