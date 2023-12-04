package concessionario;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

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
        List<Cliente> clienti;
        FactoryAnagraficaFile anagraficaFile = new FactoryAnagraficaFile("Elenco_clienti.txt");
        FactoryAutomobili factoryAutomobili = new FactoryAutomobili();
        Listino listinoAuto = new Listino();
        Concessionario concessionario = new Concessionario(listinoAuto);
        Filtro filtro = new Filtro();
        CercatoreAuto cercatoreAuto = new CercatoreAuto();
        Scanner scanner = new Scanner(System.in);
        int scelta;
        Cliente cliente = new Cliente("porco", "dio", null, null, null);
        VenditorePrivato privato = new VenditorePrivato(cliente, new Automobile("ClasseA", "MERCEDES", 0, 5, 0, 0, StatoMacchina.USATO));

        
        // creazione automobili
        for(int i = 0; i < 2; i++) {
            listinoAuto.aggiungiAuto(factoryAutomobili.creaAutoRandom1(), 0);
        }
        listinoAuto.aggiungiAuto(new Automobile("ClasseA", "MERCEDES", 0, 5, 0, 0, StatoMacchina.NUOVO), 0);
        
        // creazione clienti
        for (int i = 0; i < 10; i++) {
            anagrafica.registraCliente(clientefactory.creaClienteRandom());
        }

        do {
            System.out.println("selezionare l'operazione che si vuole eseguire:");
            System.out.println("1. registra cliente");
            System.out.println("2. ricerca clienti in base a codice fiscale");
            System.out.println("3. ricerca cliente in base a parole chiave");
            System.out.println("4. ricerca auto");
            System.out.println("5. acquisto auto da privato");
            System.out.println("0. ESCI");

            scelta = scanner.nextInt();
            scanner.nextLine();

            switch(scelta) {
                case 1:
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
                break;

                case 2:
                String cf;
                System.out.println("inserisci il codice fiscale(cf):");
                cf = scanner.nextLine();
                Cliente clienteTrovato = anagrafica.cercaCliente(cf);
                System.out.println(clienteTrovato);
                break;
                
                case 3:
                String key;
                System.out.println("inserisci la parola chiave");
                key = scanner.nextLine();
                clienti = anagrafica.cercaClienti(key);
                System.out.println(clienti);
                break;

                case 4:
                String modello, marca;
                int km, numeroPorte, cilindrata;
                StatoMacchina statoMacchina;
                double prezzoMax;
                // da sistemare problemi col null
                System.out.println("Marca o null se non si vuole inserire:");
                marca = scanner.nextLine();
                filtro.setMarca(marca);
                System.out.println(marca);
                // da sistemare problemi col null
                System.out.println("Modello o null se non si vuole inserire:");
                modello = scanner.nextLine();
                filtro.setModello(modello);
                System.out.println(modello);

                System.out.println("Km o -1 se non si vuole inserire:");
                km = scanner.nextInt();
                filtro.setKm(km);
                System.out.println(km);

                System.out.println("Numero porte o 0 se non si vuole inserire:");
                numeroPorte = scanner.nextInt();
                filtro.setNumeroPorte(numeroPorte);
                System.out.println(numeroPorte);

                System.out.println("Cilindrata o 0 se non si vuole inserire:");
                cilindrata = scanner.nextInt();
                filtro.setCilindrata(cilindrata);
                System.out.println(cilindrata);

                System.out.println("Stato macchina(NUOVO, USATO):");
                String statoInput = scanner.next();
                statoMacchina = StatoMacchina.valueOf(statoInput.toUpperCase());
                filtro.setStatoMacchina(statoMacchina);
                System.out.println(statoMacchina);

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
                        System.out.println(auto); // Assicurati che la classe Automobile abbia un toString() appropriato
                    }
                }
                break;
            }
        }while(scelta != 0);
        scanner.close();
       
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
    }
}
