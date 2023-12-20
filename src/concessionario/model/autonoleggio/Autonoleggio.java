package concessionario.model.autonoleggio;

import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.stream.Collectors;

public class Autonoleggio {
    private int scelta;
    private Leasing leasing;

    public Autonoleggio() {
        this.scelta = 0;
        this.leasing = new Leasing(this);
    }

    public int getScelta() {
        return scelta;
    }

    public void setScelta(int scelta) {
        this.scelta = scelta;
    }

    public Leasing getLeasing() {
        return leasing;
    }

    public void setLeasing(Leasing leasing) {
        this.leasing = leasing;
    }

    public void scegliOperazione() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Benvenuto nella nostra concessionaria; vuole noleggiare un'auto o effettuare il leasing?");
            System.out.println("Digitare 1 per il noleggio oppure 2 per il leasing.");
            System.out.println("1. Noleggio");
            System.out.println("2. Leasing");

            while (!scanner.hasNextInt()) {
                System.out.println("Inserisca un valore valido.");
                scanner.next();
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
// Metodo per il noleggio
    private void noleggio() {
        Auto[][] autoDisponibili = generaAutoCasuali();
        System.out.println();
        System.out.println("Digitare il numero corrispondente alla marca di auto che si vuole scegliere.");
        System.out.println("Scelga la marca dell'auto:");
        System.out.println("1. Mercedes");
        System.out.println("2. BMW");
        System.out.println("3. Audi");
        System.out.println("4. Toyota");
        System.out.println("5. Tesla");
        System.out.println("6. Volkswagen");
    
        Scanner scanner = new Scanner(System.in);
        int sceltaMarca;
    
        do {
            try {
                while (!scanner.hasNextInt()) {
                    System.out.println("Inserisca un valore valido.");
                    scanner.next(); 
                }
                sceltaMarca = scanner.nextInt();
    
                if (sceltaMarca >= 1 && sceltaMarca <= 6) {
                    Auto[] autoMarcaScelta = autoDisponibili[sceltaMarca - 1];
    
                    System.out.println("Controllo della disponibilità delle vetture in sede...");
                    System.out.println();
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
    
                    int sceltaAuto;
                    do {
                        System.out.println("Scelga un modello di auto tra quelli sopra elencati:");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Inserisca un valore valido.");
                            scanner.next();
                        }
                        sceltaAuto = scanner.nextInt();
    
                        if (sceltaAuto >= 1 && sceltaAuto <= 3) {
                            noleggiaAuto(autoMarcaScelta[sceltaAuto - 1]);
    
                            String durataNoleggio = scegliDurataNoleggio();
                            System.out.println();
                            System.out.println("Ha scelto di noleggiarla per " + durataNoleggio + ".");
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            restituisciAuto();
                        } else {
                            System.out.println("Scelta non valida.");
                        }
                    } while (sceltaAuto < 1 || sceltaAuto > 3);
                } else {
                    System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Inserisca un numero valido.");
                scanner.next();
                sceltaMarca = 0;
            }
        } while (sceltaMarca < 1 || sceltaMarca > 6);
    }
// Metodo per il leasing
    private void leasing() {
        leasing.sceltaAuto();
        System.out.println("Vuole acquistare l'auto? (Si/No)");

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
        Scanner scanner = new Scanner(System.in);
        int sceltaDurata;

        do {
            System.out.println();
            System.out.println("Che tipo di noleggio vuole effettuare?");
            System.out.println("1. Noleggio a breve termine (Da 1 a 30 giorni)");
            System.out.println("2. Noleggio a medio termine (Da 1 a 6 mesi)");
            System.out.println("3. Noleggio a lungo termine (Minimo 6 mesi)");

            try {
                sceltaDurata = scanner.nextInt();

                switch (sceltaDurata) {
                    case 1:
                        return "breve termine";
                    case 2:
                        return "medio termine";
                    case 3:
                        return "lungo termine";
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Inserisca un valore valido.");
                scanner.next();
                sceltaDurata = 0;
            }
        } while (sceltaDurata < 1 || sceltaDurata > 3);

        return "";
    }

    private void noleggiaAuto(Auto auto) {
        System.out.println("Ha scelto la seguente auto:");
        System.out.println(auto.toString());
    }

    private void restituisciAuto() {
        Scanner scanner = new Scanner(System.in);
        boolean inputValido = false;

        do {
            System.out.println();
            System.out.println("Vuole riportare l'auto? (Si/No)");

            if (scanner.hasNext()) {
                String risposta = scanner.next().toLowerCase();

                if (risposta.equals("si")) {
                    eseguiControlli();
                    inputValido = true;
                } else if (risposta.equals("no")) {
                    System.out.println("Grazie per la sua scelta, le auguriamo un buon proseguimento!.");
                    inputValido = true;
                } else {
                    System.out.println("Selezione non valida. Inserisca Si o No.");
                }
            } else {
                System.out.println("Input non disponibile. Programma terminato.");
                break;
            }

        } while (!inputValido);
    }
// Metodo per i controlli
    private void eseguiControlli() {
        System.out.println("Esecuzione dei seguenti controlli:");
        System.out.println("1) Controllo del carburante");
        System.out.println("2) Controllo della pulizia");

        System.out.println("Si prega di attendere nel mentre che vengono eseguiti i controlli di routine...");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        controlloCarburante();
        controlloPulizia();
    }
// Metodo per effettuare il controllo del carburante
    private void controlloCarburante() {
        Random random = new Random();
        int carburanteRimasto = random.nextInt(70) + 1; // Valore casuale tra 1 e 70 litri

        System.out.println("Il carburante rimasto è: " + carburanteRimasto + " litri.");
    }
// Metodo per effettuare il controllo sulla pulizia dell'auto
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