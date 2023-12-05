package concessionario.autonoleggio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import concessionario.assicurazione.Assicurazione;

public class Leasing {
    private List<Auto> autoLeasing;
    private Auto autoScelta;
    private Autonoleggio autonoleggio;
    private Scanner scanner;

    public Leasing(Autonoleggio autonoleggio) {
        this.autonoleggio = autonoleggio;
        this.autoLeasing = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
// Metodo per scegliere l'auto
    public void sceltaAuto() {
        try {
            System.out.println();
            System.out.println("Ha scelto di acquistare la sua nuova auto in leasing, controllo le auto presenti in concessionario...");
            System.out.println();
            Thread.sleep(5000); // Pausa di 5 secondi
        } catch (InterruptedException e) {
            System.err.println("Errore durante la pausa.");
        }

        System.out.println("Lista Auto disponibili, può scegliere tra quelle elencate digitando il numero corrispondente:");

        List<Auto> autoDisponibili = autonoleggio != null ? autonoleggio.getAutoDisponibili() : autoLeasing;

        int index = 1;
        for (Auto auto : autoDisponibili) {
            System.out.println(index + ". " + auto.getMarca() + " " + auto.getModello());
            index++;
        }

        int scelta = 0;

        while (true) {
            try {
                scelta = scanner.nextInt();
                if (scelta >= 1 && scelta <= autoDisponibili.size()) {
                    autoScelta = autoDisponibili.get(scelta - 1);
                    System.out.println("Ha scelto la seguente auto: " + autoScelta.getMarca() + " "
                            + autoScelta.getModello() + " " + autoScelta.getCavalli() + " (Cavalli)");
                    break;
                } else {
                    System.out.println("Scelta non valida. Riprova.");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.err.println("Inserire un valore numerico valido.");
                scanner.next();
            }
        }
    }
// Metodo per acquistare un auto
    public void acquistaAuto(int durataAnni, double prezzoTotale, double rataMensile) {
        
        final double TASSO_INTERESSE_ANNUO = 0.05; // Tasso interesse del 5%
        double interesse = prezzoTotale * TASSO_INTERESSE_ANNUO * durataAnni;
        double prezzoConInteresse = prezzoTotale + interesse;
    
        System.out.println();
        System.out.println("Contratto di acquisto dell'auto:");
        System.out.println();
        System.out.println("Durata del finanziamento: " + durataAnni + " anni");
        System.out.println("Rata mensile: " + String.format("%.3f", rataMensile) + " euro");
        System.out.println("Prezzo totale dell'auto senza interesse: " + String.format("%.3f", prezzoTotale) + " euro");
        System.out.println("Prezzo totale dell'auto con interesse: " + String.format("%.3f", prezzoConInteresse) + " euro");

    }
// Metodo per cambiare l'auto
    public void cambiaAuto() {
        try {
            System.out.println();
            Thread.sleep(5000); // Pausa di 5 secondi
        } catch (InterruptedException e) {
            System.err.println("Errore durante la pausa.");
        }
    
        System.out.println("Vuole cambiare l'auto attuale?");
        System.out.println("Digitare 1 per confermare il cambio auto o 2 per rimanere con l'auto attuale.");
    
        int scelta = 0;
    
        while (true) {
            try {
                scelta = scanner.nextInt();
    
                if (scelta == 1) {
                    sceltaAuto();
                    break;
                } else if (scelta == 2) {
                    System.out.println("Ha scelto di rimanere con l'auto attuale.");
                    Assicurazione assicurazione = new Assicurazione(autonoleggio);
                    assicurazione.gestisciAssicurazione();
                    break;
                } else {
                    System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Inserisca un valore numerico valido.");
                scanner.next();
            }
        }
    }
}