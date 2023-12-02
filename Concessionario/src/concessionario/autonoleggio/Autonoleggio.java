package concessionario.autonoleggio;

import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Autonoleggio {
    private int scelta;
    private Leasing leasing;
    

    public Autonoleggio() {
        this.scelta = 0;
        this.leasing = new Leasing(this);
        
    }

    public void scegliOperazione() {
        
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Vuoi noleggiare l'auto o effettuare il leasing?");
            System.out.println("Digitare 1 per il noleggio oppure digitare 2 per il leasing.");
            System.out.println("1. Noleggio");
            System.out.println("2. Leasing");

            while (!scanner.hasNextInt()) {
                System.out.println("Inserisci un numero valido.");
                scanner.next(); // consuma l'input non valido
            }

            this.scelta = scanner.nextInt();

            switch (this.scelta) {
                case 1:
                    noleggio();
                    break;
                case 2:
                    leasing();
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        } while (this.scelta != 1 && this.scelta != 2);
    }

    private void noleggio() {
        Auto[][] autoDisponibili = generaAutoCasuali();

        System.out.println("Digitare il numero corrispondente alla marca di auto che si vuole scegliere.");
        System.out.println("Scegli la marca dell'auto:");
        System.out.println("1. Mercedes");
        System.out.println("2. BMW");
        System.out.println("3. Audi");
        System.out.println("4. Toyota");
        System.out.println("5. Tesla");
        System.out.println("6. Volkswagen");

        Scanner scanner = new Scanner(System.in);
        int sceltaMarca = scanner.nextInt();

        if (sceltaMarca >= 1 && sceltaMarca <= 6) {
            Auto[] autoMarcaScelta = autoDisponibili[sceltaMarca - 1];

            System.out.println("Controllo della disponibilità delle vetture in sede...");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Ecco le auto disponibili:");
            for (int i = 0; i < 3; i++) {
                System.out.println((i + 1) + ". " + autoMarcaScelta[i].getMarca() + " " +
                        autoMarcaScelta[i].getModello() + " (Cavalli: " + autoMarcaScelta[i].getCavalli() + ")");
            }

            System.out.println("Scegli un'auto:");
            int sceltaAuto = scanner.nextInt();
            
            
            if (sceltaAuto >= 1 && sceltaAuto <= 3) {
                noleggiaAuto(autoMarcaScelta[sceltaAuto - 1]);

                String durataNoleggio = scegliDurataNoleggio();
                System.out.println("Hai scelto di noleggiarla per " + durataNoleggio + ".");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                /*{
                    Assicurazione assicurazione = new Assicurazione(null);
                    assicurazione.gestisciAssicurazione();
                }*/
                restituisciAuto();
            } else {
                System.out.println("Scelta non valida.");
            }
        } else {
            System.out.println("Scelta non valida.");
        }
    }
            

    private void leasing() {
        leasing.sceltaAuto();
        System.out.println("Vuoi acquistare l'auto? (Si/No)");

        Scanner scanner = new Scanner(System.in);
        String rispostaAcquisto = scanner.next().toLowerCase();

        if (rispostaAcquisto.equals("si")) {
            double rataMensile = Math.random() * (500 - 100) + 100;
            int durataAnni = 5;
            double prezzo = rataMensile * 12 * durataAnni;

            leasing.acquistaAuto(durataAnni, prezzo, rataMensile);
        }

        leasing.cambiaAuto();
    }
    
    private Auto[][] generaAutoCasuali() {
        String[] marche = {"Mercedes", "BMW", "Audi", "Toyota", "Tesla", "Volkswagen"};
        int[] cavalliMercedes = {110, 150, 200, 500};
        int[] cavalliBMW = {110, 150, 200, 300};
        int[] cavalliAudi = {110, 150, 200, 300};
        int[] cavalliToyota = {90, 100, 150, 200};
        int[] cavalliTesla = {200, 300, 350, 900};
        int[] cavalliVolkswagen = {90, 150, 200, 200};

        Auto[][] autoArray = new Auto[marche.length][3];

        Random random = new Random();

        for (int i = 0; i < marche.length; i++) {
            Auto auto1, auto2, auto3;

            switch (marche[i]) {
                case "Mercedes":
                    auto1 = new Auto("Mercedes", "Classe A", cavalliMercedes[0]);
                    auto2 = new Auto("Mercedes", "Classe B", cavalliMercedes[1]);
                    auto3 = new Auto("Mercedes", "A200d", cavalliMercedes[2]);
                    break;
                case "BMW":
                    auto1 = new Auto("BMW", "Serie 1", cavalliBMW[0]);
                    auto2 = new Auto("BMW", "X2", cavalliBMW[1]);
                    auto3 = new Auto("BMW", "X3", cavalliBMW[2]);
                    break;
                case "Audi":
                    auto1 = new Auto("Audi", "A1", cavalliAudi[0]);
                    auto2 = new Auto("Audi", "A3", cavalliAudi[1]);
                    auto3 = new Auto("Audi", "A6", cavalliAudi[2]);
                    break;
                case "Toyota":
                    auto1 = new Auto("Toyota", "Aygo", cavalliToyota[0]);
                    auto2 = new Auto("Toyota", "C-HR", cavalliToyota[1]);
                    auto3 = new Auto("Toyota", "Rav4", cavalliToyota[2]);
                    break;
                case "Tesla":
                    auto1 = new Auto("Tesla", "Model 3", cavalliTesla[0]);
                    auto2 = new Auto("Tesla", "Model 3 performance", cavalliTesla[1]);
                    auto3 = new Auto("Tesla", "Model Y", cavalliTesla[2]);
                    break;
                case "Volkswagen":
                    auto1 = new Auto("Volkswagen", "Polo", cavalliVolkswagen[0]);
                    auto2 = new Auto("Volkswagen", "Golf", cavalliVolkswagen[1]);
                    auto3 = new Auto("Volkswagen", "T-Cross", cavalliVolkswagen[2]);
                    break;
                default:
                    auto1 = auto2 = auto3 = null;
            }

            autoArray[i][0] = auto1;
            autoArray[i][1] = auto2;
            autoArray[i][2] = auto3;
        }

        return autoArray;
    }

    private String scegliDurataNoleggio() {
        System.out.println("Che tipo di noleggio vuoi effettuare?");
        System.out.println("1. Noleggio a breve termine (Da 1 a 30 giorni)");
        System.out.println("2. Noleggio a medio termine (Da 1 a 6 mesi)");
        System.out.println("3. Noleggio a lungo termine (Minimo 6 mesi)");

        Scanner scanner = new Scanner(System.in);
        int sceltaDurata = scanner.nextInt();

        switch (sceltaDurata) {
            case 1:
                return "breve termine";
            case 2:
                return "medio termine";
            case 3:
                return "lungo termine";
            default:
                return "";
        }
    }

    private void noleggiaAuto(Auto auto) {
        System.out.println("Hai scelto la seguente auto:");
        System.out.println(auto.toString());
    }

    private void restituisciAuto() {
        Scanner scanner = new Scanner(System.in);
        boolean inputValido = false;
    
        do {
            System.out.println("Vuoi riportare l'auto? (Si/No)");
    
            if (scanner.hasNext()) {
                String risposta = scanner.next().toLowerCase();
    
                if (risposta.equals("si")) {
                    eseguiControlli();
                    inputValido = true;
                } else if (risposta.equals("no")) {
                    System.out.println("Grazie e arrivederci!");
                    inputValido = true;
                } else {
                    System.out.println("Selezione non valida. Inserisci Si o No.");
                }
            } else {
                System.out.println("Input non disponibile. Programma terminato.");
                break;
            }
    
        } while (!inputValido);
    }

    private void eseguiControlli() {
        System.out.println("Esecuzione dei seguenti controlli:");
        System.out.println("2) Controllo del carburante");
        System.out.println("3) Controllo della pulizia");

        System.out.println("Si prega di attendere nel mentre che vengono eseguiti i controlli di routine...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        controlloCarburante();
        controlloPulizia();
    }

    private void controlloCarburante() {
        Random random = new Random();
        int carburanteRimasto = random.nextInt(70) + 1; // Valore casuale tra 1 e 70 litri

        System.out.println("Il carburante rimasto è: " + carburanteRimasto + " litri.");
    }

    private void controlloPulizia() {
        String[] condizioniPulizia = {"pessime", "buone", "ottime"};
        Random random = new Random();
        String condizione = condizioniPulizia[random.nextInt(condizioniPulizia.length)];

        System.out.println("L'auto si trova in " + condizione + " condizioni di pulizia.");
    }

    public List<Auto> getAutoDisponibili() {
        Auto[][] autoDisponibili = generaAutoCasuali();
        return Arrays.stream(autoDisponibili)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }
}