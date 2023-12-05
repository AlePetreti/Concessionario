package concessionario.assicurazione;

import concessionario.autonoleggio.Autonoleggio;
import concessionario.Officina.Officina;
import java.util.Scanner;

// Classe Assicurazione, sottoclasse di Autonoleggio
public class Assicurazione {

    private LivelloAssicurativo livelloAssicurativo;

    // Metodo costruttore per richiamare autonoleggio
    public Assicurazione(Autonoleggio autonoleggio) {
        this.livelloAssicurativo = LivelloAssicurativo.Copertura_Minima;
    }

    // Metodo per gestire l'assicurazione
    public void gestisciAssicurazione() {
        Scanner scanner = new Scanner(System.in);
    
        boolean rispostaValida = false;
    
        while (!rispostaValida) {
            System.out.println("Vuole effettuare l'assicurazione? (si/no)");
            String risposta = scanner.nextLine().toUpperCase();
    
            switch (risposta) {
                case "SI":
                    rispostaValida = true;
                    selezioneAssicurazione(scanner);
                    break;
                case "NO":
                    rispostaValida = true;
                    System.out.println("Ha scelto di non effettuare l'assicurazione. Il cliente si assume ogni responsabilità per eventuali danni.");
                    break;
                default:
                    System.out.println("Risposta non valida. Inserisca 'Si' o 'No'.");
            }
        }
    
        // Chiudi lo scanner
        scanner.close();
    }

    // Metodo per gestire la selezione del livello di assicurazione
    private void selezioneAssicurazione(Scanner scanner) {
        int sceltaLivello = 0;
    
        while (true) {
            System.out.println("Scelga il livello di assicurazione:");
            System.out.println("1) Copertura Minima, costo: 150 $.");
            System.out.println("2) Copertura Media, costo: 300 $.");
            System.out.println("3) Copertura Massima, costo: 500 $.");
    
            try {
                sceltaLivello = Integer.parseInt(scanner.next());
    
                if (sceltaLivello >= 1 && sceltaLivello <= 3) {
                    break;
                } else {
                    System.out.println("Valore non valido. Inserisca un valore tra 1, 2 o 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserisca un numero valido.");
            }
        }
    
        String livelloAssicurazione = "";
    
        switch (sceltaLivello) {
            case 1:
                tipoAssicurazione(LivelloAssicurativo.Copertura_Minima);
                livelloAssicurazione = "Copertura Minima";
                break;
            case 2:
                tipoAssicurazione(LivelloAssicurativo.Copertura_Media);
                livelloAssicurazione = "Copertura Media";
                break;
            case 3:
                tipoAssicurazione(LivelloAssicurativo.Copertura_Massima);
                livelloAssicurazione = "Copertura Massima";
                break;
        }
    
        System.out.println("Tipo di assicurazione scelto: " + livelloAssicurazione);
    
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        gestisciPerizia(scanner, livelloAssicurazione);
    }

    // Metodo per gestire la perizia
    private void gestisciPerizia(Scanner scanner, String livelloAssicurazione) {
        boolean rispostaValida = false;
    
        while (!rispostaValida) {
            System.out.println("Vuole riconsegnare l'auto e fare eseguire la perizia per eventuali danni? (si/no)");
            String risposta = scanner.next().toUpperCase();
    
            switch (risposta) {
                case "SI":
                    rispostaValida = true;
                    LivelloDanni livelloDanni = periziaAssicurativa();
    
                    System.out.println("Tipo di assicurazione stipulata in precedenza: " + livelloAssicurazione);
                    System.out.println("Livello danni segnalati: " + livelloDanni.getDescrizione());
    
                    Officina officina = new Officina();
                    officina.gestisciRiparazione(livelloDanni);
                    break;
                case "NO":
                    rispostaValida = true;
                    System.out.println("Ha scelto di non riconsegnare l'auto e di non effettuare la perizia. Grazie!");
                    break;
                default:
                    System.out.println("Risposta non valida. Inserisca 'Si' o 'No'.");
            }
        }
    }

    // Metodo per impostare il tipo di assicurazione
    public void tipoAssicurazione(LivelloAssicurativo livello) {
        this.livelloAssicurativo = livello;

        switch (livello) {
            case Copertura_Minima:
                System.out.println("Livello di assicurazione scelto: 1");
                break;
            case Copertura_Media:
                System.out.println("Livello di assicurazione scelto: 2");
                break;
            case Copertura_Massima:
                System.out.println("Livello di assicurazione scelto: 3");
                break;
            default:
                System.out.println("Livello di assicurazione non riconosciuto.");
                break;
        }
    }

    // Metodo per ottenere il livello dei danni dalla perizia
    public LivelloDanni periziaAssicurativa() {
        int valoreCasuale = (int) (Math.random() * 3) + 1;

        switch (valoreCasuale) {
            case 1:
                return LivelloDanni.NESSUN_DANNO;
            case 2:
                return LivelloDanni.DANNO_LIEVE;
            case 3:
                return LivelloDanni.DANNO_GRAVE;
            default:
                return LivelloDanni.NESSUN_DANNO;
        }
    }

    // Metodo per ottenere il livello assicurativo corrente
    public LivelloAssicurativo getLivelloAssicurativo() {
        return livelloAssicurativo;
    }
}