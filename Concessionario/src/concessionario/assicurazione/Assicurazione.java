package concessionario.assicurazione;

import concessionario.autonoleggio.Autonoleggio;
import concessionario.Officina.Officina;
import java.util.Scanner;

// Classe Assicurazione, sottoclasse di Autonoleggio
public class Assicurazione {

    // Attributi privati
    private Autonoleggio autonoleggio;
    private LivelloAssicurativo livelloAssicurativo;

    // Metodo costruttore per richiamare autonoleggio
    public Assicurazione(Autonoleggio autonoleggio) {
        this.autonoleggio = autonoleggio;
        this.livelloAssicurativo = LivelloAssicurativo.Copertura_Minima;
    }

    public void gestisciAssicurazione() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Vuoi effettuare l'assicurazione?. (si/no)");
        String risposta = scanner.nextLine().toUpperCase();

        if (risposta.equals("SI") || risposta.equals("SÌ")) {
            gestisciSelezioneAssicurazione(scanner);
        } else {
            System.out.println("Hai scelto di non effettuare l'assicurazione, il cliente si assume ogni responsabilità per eventuali danni.");
        }

        // Chiudi lo scanner
        scanner.close();
    }

    private void gestisciSelezioneAssicurazione(Scanner scanner) {
        System.out.println("Scegli il livello di assicurazione:");
        System.out.println("1) Copertura Minima, costo: 150 $.");
        System.out.println("2) Copertura Media, costo: 300 $.");
        System.out.println("3) Copertura Massima, costo: 500 $.");

        int sceltaLivello;
        try {
            sceltaLivello = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Input non valido. Livello COPERTURA_MINIMA assegnato di default.");
            tipoAssicurazione(LivelloAssicurativo.Copertura_Minima);
            return;
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
            default:
                System.out.println("Scelta non valida. Livello COPERTURA_MINIMA assegnato di default.");
                tipoAssicurazione(LivelloAssicurativo.Copertura_Minima);
                livelloAssicurazione = "Copertura Minima";
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

    private void gestisciPerizia(Scanner scanner, String livelloAssicurazione) {
        System.out.println("Vuoi riconsegnare l'auto ed eseguire la perizia per eventuali danni? (si/no)");
        String risposta = scanner.next().toUpperCase();

        if (risposta.equals("SI") || risposta.equals("SÌ")) {
            LivelloDanni livelloDanni = periziaAssicurativa();

            System.out.println("Tipo di assicurazione stipulata in precedenza: " + livelloAssicurazione);
            System.out.println("Livello danni segnalati: " + livelloDanni.getDescrizione());

            Officina officina = new Officina();
            officina.gestisciRiparazione(livelloDanni);
        } else {
            System.out.println("Hai scelto di non riconsegnare l'auto e di non effettuare la perizia. Grazie!");
        }
    }

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

    public LivelloAssicurativo getLivelloAssicurativo() {
        return livelloAssicurativo;
    }
}