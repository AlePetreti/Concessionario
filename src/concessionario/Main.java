package concessionario;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import concessionario.automobile.Automobile;
import concessionario.automobile.FactoryAutomobili;
import concessionario.automobile.StatoMacchina;
import concessionario.cliente.AnagraficaClienti;
import concessionario.cliente.Cliente;
import concessionario.cliente.FactoryAnagraficaFile;
import concessionario.cliente.FactoryCliente;

public class Main {
    public static void main(String[] args) throws Exception {


    AnagraficaClienti anagrafica = new AnagraficaClienti();
    FactoryCliente clientefactory = new FactoryCliente();
    FactoryAnagraficaFile anagraficaFile = new FactoryAnagraficaFile("Elenco_clienti.txt");
    FactoryAutomobili factoryAutomobili = new FactoryAutomobili();
    Listino listinoAuto = new Listino();
    Concessionario concessionario = new Concessionario(listinoAuto);
    Filtro filtro = new Filtro();
    CercatoreAuto cercatoreAuto = new CercatoreAuto();

    Cliente cliente = new Cliente("porco", "dio", null, null, null);
    Automobile autoUsata  = new Automobile("ClasseA", "MERCEDES", 0, 5, 0, 0, StatoMacchina.USATO);
    VenditorePrivato privato = new VenditorePrivato(cliente, autoUsata, 2000);

    // Inizializzazione di automobili e clienti
    inizializzaAutomobili(listinoAuto, factoryAutomobili);
    inizializzaClienti(anagrafica, anagraficaFile, clientefactory);

    // ...

    eseguiMenu(anagrafica,filtro, cercatoreAuto, listinoAuto);
}

private static void inizializzaAutomobili(Listino listinoAuto, FactoryAutomobili factoryAutomobili) {
    for(int i = 0; i < 2; i++) {
        listinoAuto.aggiungiAuto(factoryAutomobili.creaAutoRandom1(), 0);
    }
    listinoAuto.aggiungiAuto(new Automobile("ClasseA", "MERCEDES", 0, 5, 0, 0, StatoMacchina.NUOVO), 0);
}

private static void inizializzaClienti(AnagraficaClienti anagrafica, FactoryAnagraficaFile anagraficaFile, FactoryCliente clientefactory) {
    for (int i = 0; i < 10; i++) {
        anagrafica.registraCliente(clientefactory.creaClienteRandom());
    }
}

private static void eseguiMenu(AnagraficaClienti anagrafica, Filtro filtro, CercatoreAuto cercatoreAuto, Listino listinoAuto) {
    Scanner scanner = new Scanner(System.in);
    int scelta;

    do {
        stampaMenuPrincipale();
        scelta = scanner.nextInt();
        scanner.nextLine();
        gestisciSceltaMenu(scelta, scanner, anagrafica, filtro, cercatoreAuto, listinoAuto);

    } while (scelta != 0);

    scanner.close();
}

private static void stampaMenuPrincipale() {
    System.out.println("selezionare l'operazione che si vuole eseguire:");
    System.out.println("1. gestione clienti");
    System.out.println("2. ricerca auto");
    System.out.println("3. acquisto auto da privato");
    System.out.println("0. ESCI");
}

private static void gestisciSceltaMenu(int scelta, Scanner scanner, AnagraficaClienti anagrafica, Filtro filtro, CercatoreAuto cercatoreAuto, Listino listinoAuto) {
    switch (scelta) {
        case 1:
            gestioneClienti(scanner, anagrafica);
            break;
        case 2:
            ricercaAuto(scanner, filtro, cercatoreAuto, listinoAuto);
            break;
    }
}

private static void gestioneClienti(Scanner scanner, AnagraficaClienti anagrafica) {
    int opzione;
    do {
        stampaMenuGestioneClienti();
        opzione = scanner.nextInt();
        scanner.nextLine();
        switch (opzione) {
            case 1:
                registraCliente(scanner, anagrafica);
                break;
            case 2:
                ricercaCliente(scanner, anagrafica);
                break;
            case 3:
                ricercaClientiPerParolaChiave(scanner, anagrafica);
                break;
        }
    } while (opzione != 0);
}

private static void stampaMenuGestioneClienti() {
    System.out.println("gestione clienti scegliere l'operazione da eseguire");
    System.out.println("1. registra cliente");
    System.out.println("2. ricerca cliente in base a codice fiscale");
    System.out.println("3. ricerca cliente in base a parola chiave");
    System.out.println("0. torna al menu principale");
}

private static void registraCliente(Scanner scanner, AnagraficaClienti anagrafica) {
    String nome, cognome, email, telefono, codiceFiscale;
    System.out.println("inserisci nome:");
    nome = scanner.nextLine();
    System.out.println("inserisci cognome:");
    cognome = scanner.nextLine();
    System.out.println("inserisci email:");
    email = scanner.nextLine();
    System.out.println("inserisci telefono:");
    telefono = scanner.nextLine();
    System.out.println("inserisci codice fiscale:");
    codiceFiscale = scanner.nextLine();
    Cliente nuovoCliente = new Cliente(nome, cognome, email, telefono, codiceFiscale);
    anagrafica.registraCliente(nuovoCliente);
}

private static void ricercaCliente(Scanner scanner, AnagraficaClienti anagrafica) {
    String cf;
    System.out.println("inserisci il codice fiscale(cf):");
    cf = scanner.nextLine();
    Cliente clienteTrovato = anagrafica.cercaCliente(cf);
    System.out.println(clienteTrovato);
}

private static void ricercaClientiPerParolaChiave(Scanner scanner, AnagraficaClienti anagrafica) {
    System.out.println("inserisci la parola chiave");
    String parolaChiave = scanner.nextLine();
    List<Cliente> clienti= anagrafica.cercaClienti(parolaChiave);
    System.out.println(clienti);
}

private static void ricercaAuto(Scanner scanner, Filtro filtro, CercatoreAuto cercatoreAuto, Listino listinoAuto) {
    String modello, marca;
        int km, numeroPorte, cilindrata;
        StatoMacchina statoMacchina;
        double prezzoMax;
        try {
            System.out.println("Marca o premere spazio se non si vuole inserire:");
            marca = scanner.nextLine();
            if(!marca.isEmpty()) {
                filtro.setMarca(marca);
            }
            System.out.println("Modello o premere se non si vuole inserire:");
            modello = scanner.nextLine();
            if(!modello.isEmpty()) {
                filtro.setModello(modello);
            }
            System.out.println("Km o -1 se non si vuole inserire:");
            km = scanner.nextInt();
            filtro.setKm(km);

            System.out.println("Numero porte o 0 se non si vuole inserire:");
            numeroPorte = scanner.nextInt();
            filtro.setNumeroPorte(numeroPorte);
                    
            System.out.println("Cilindrata o 0 se non si vuole inserire:");
            cilindrata = scanner.nextInt();
            filtro.setCilindrata(cilindrata);
            
            System.out.println("Stato macchina(NUOVO, USATO):");
            String statoInput = scanner.next();
            statoMacchina = StatoMacchina.valueOf(statoInput.toUpperCase());
            filtro.setStatoMacchina(statoMacchina);

            // da finire il prezzo
            /*System.out.println("Prezzo Max o 0 se non si vuole inserire:");
            prezzoMax = scanner.nextDouble();
            filtro.setPrezzoMax(prezzoMax); */

            List<Automobile> autoTrovate = cercatoreAuto.cercaAuto(filtro, listinoAuto);
            if (autoTrovate.isEmpty()) {
                System.out.println("Nessuna automobile trovata.");
            } else {
                System.out.println("Automobili trovate:");
                for (Automobile auto : autoTrovate) {
                    System.out.println(auto); 
                }
            }
            // sistemare eccezioni
        } catch(InputMismatchException e) {
            System.out.println("Input non valido. Assicurati di inserire il tipo corretto");
        } catch(IllegalArgumentException e) {
            System.out.println("Valore non valido inserito. Assicurati di rispettare le specifiche richieste");
        }
    }

// Altre funzioni secondo necessità
}

/* 
        concessionario.acquistaAutoDaPrivato(privato);
        
        System.out.println(listinoAuto.getListino());
        */

        // ricerca clienti
        /*  
        
        clienti = anagrafica.getClienti();
        System.out.println(clienti);

        anagrafica = anagraficaFile.creaAnagraficaClienti();
        clienti = anagrafica.getClienti();
        System.out.println(clienti); 
        
        anagrafica = anagraficaFile.creaAnagraficaClienti(); */
