package concessionario.autonoleggio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import concessionario.assicurazione.Assicurazione;

public class Leasing {
    private List<Auto> autoLeasing;
    private Auto autoScelta;
    private Autonoleggio autonoleggio;

    // Costruttore che accetta un'istanza di Autonoleggio
    public Leasing(Autonoleggio autonoleggio) {
        this.autonoleggio = autonoleggio;
        this.autoLeasing = new ArrayList<>();
    }

    // Costruttore che accetta direttamente una lista di auto
    public Leasing(List<Auto> autoLeasing) {
        this.autoLeasing = autoLeasing;
        
    }


    // Codice per la scelta dell'auto.
    public void sceltaAuto() {
        try {
            System.out.println();
            System.out.println("Hai scelto di acquistare la tua nuova auto in leasing, controllo le auto presenti in concessionario...");
            Thread.sleep(5000); // Pausa di 5 secondi
        } catch (InterruptedException e) {
            System.err.println("Errore durante la pausa.");
        }

        System.out.println("Lista Auto disponibili, puoi scegliere tra quelle elencate:");

        List<Auto> autoDisponibili = autonoleggio != null ? autonoleggio.getAutoDisponibili() : autoLeasing;

        int index = 1;
        for (Auto auto : autoDisponibili) {
            System.out.println(index + ". " + auto.getMarca() + " " + auto.getModello());
            index++;
        }

        Scanner scanner = new Scanner(System.in);
        int scelta = 0;

        try {
            scelta = scanner.nextInt();
        } catch (Exception e) {
            System.err.println("Inserire un valore numerico valido.");
            return;
        }

        if (scelta >= 1 && scelta <= autoDisponibili.size()) {
            autoScelta = autoDisponibili.get(scelta - 1);
            System.out.println("Hai scelto la seguente auto: " + autoScelta.getMarca() + " " + autoScelta.getModello() + " " + autoScelta.getCavalli() + " (Cavalli)");
        } else {
            System.out.println("Scelta non valida.");
        }
    }
    
    // Codice per l'acquisto dell'auto
    public void acquistaAuto(int durataAnni, double prezzoTotale, double rataMensile) {
        final double TASSO_INTERESSE_ANNUO = 0.05; // Tasso interesse del 5%

        double interesse = prezzoTotale * TASSO_INTERESSE_ANNUO * durataAnni;
        double prezzoConInteresse = prezzoTotale + interesse;

        System.out.println();
        System.out.println("Conferma acquisto dell'auto:");
        System.out.println();
        System.out.println("Durata del finanziamento: " + durataAnni + " anni");
        System.out.println("Rata mensile: " + String.format("%.3f", rataMensile) + " euro");
        System.out.println("Prezzo totale dell'auto senza interesse: " + String.format("%.3f", prezzoTotale) + " euro");
        System.out.println("Prezzo totale dell'auto con interesse: " + String.format("%.3f", prezzoConInteresse) + " euro");

        System.out.println("Pagherai un totale di " + String.format("%.3f", prezzoConInteresse) + " euro. Per l'auto: " + autoScelta.getMarca() + " " + autoScelta.getModello() + " " + autoScelta.getCavalli() + " (Cavalli)");
        System.out.println("La ringraziamo per il suo acquisto!");
    }

    // Codice per il cambio dell'auto
    public void cambiaAuto() {
        try {
            System.out.println();
            Thread.sleep(5000); // Pausa di 5 secondi
        } catch (InterruptedException e) {
            System.err.println("Errore durante la pausa.");
        }

        System.out.println("Vuoi cambiare l'auto attuale?");
        System.out.println("Digitare 1 per confermare il cambio auto o 2 per rimanere con l'auto attuale.");

        Scanner scanner = new Scanner(System.in);
        int sceltaCambio = 0;

        try {
            sceltaCambio = scanner.nextInt();
        } catch (Exception e) {
            System.err.println("Inserire un valore numerico valido.");
            return;
        }
        
        Assicurazione assicurazione = new Assicurazione(autonoleggio);
        assicurazione.gestisciAssicurazione();

        if (sceltaCambio == 1) {
            System.out.println("Lista Auto disponibili, puoi scegliere tra quelle elencate digitando il numero corrispondente all'auto che si vuole scegliere:");
            System.out.println();
            List<Auto> autoDisponibili = autonoleggio != null ? autonoleggio.getAutoDisponibili() : autoLeasing;

            int index = 1;
            for (Auto auto : autoDisponibili) {
                System.out.println(index + ". " + auto.getMarca() + " " + auto.getModello());
                index++;
            }

            int scelta = 0;

            try {
                scelta = scanner.nextInt();
            } catch (Exception e) {
                System.err.println("Inserire un valore numerico valido.");
                return;
            }

            if (scelta >= 1 && scelta <= autoDisponibili.size()) {
                autoScelta = autoDisponibili.get(scelta - 1);
                System.out.println("Hai scelto la seguente auto: " + autoScelta.getMarca() + " " + autoScelta.getModello() + " " + autoScelta.getCavalli() + " (Cavalli)");

                System.out.println("Vuoi acquistare l'auto? (Si/No)");
                String rispostaAcquisto = scanner.next().toLowerCase();

                if (rispostaAcquisto.equals("si")) {
                    double rataMensile = Math.random() * (500 - 100) + 100;
                    int durataAnni = 5;
                    double prezzo = rataMensile * 12 * durataAnni;
                    acquistaAuto(durataAnni, prezzo, rataMensile);
                }
            } else {
                System.out.println("Scelta non valida.");
            }
        } else {
            System.out.println("Grazie e arrivederci!");
        }
    }
}
