package concessionario.model.cliente;
import java.util.Random;

public class FactoryCliente {

    private Random rnd;

    public FactoryCliente() {
        this.rnd = new Random();
    }

    public Cliente creaClienteRandom() {
        String[] nomi = {"Alessandro", "Andrea", "Giovanni", "Fabio", "Luca", "Ciro"};
        String[] cognomi = {"Petreti", "Rossi", "Bianchi", "Verdi", "Esposito", "Messi"};
        String nome = nomi[rnd.nextInt(nomi.length)];
        String cognome = cognomi[rnd.nextInt(cognomi.length)];
        String email = nome.toLowerCase() + "." + cognome.toLowerCase() + "@gmail.com";
        String telefono = String.valueOf(3000000000L + rnd.nextInt(1000000000));
        String codiceFiscale = generaCodiceFiscaleRandom();
        double redditoAnnuale = 20000 + rnd.nextInt(80000); // Valore casuale tra 20.000 e 100.000
        String preferenzeMarca = generaPreferenzeMarcaRandom();

        return new Cliente.Builder()
                .nome(nome)
                .cognome(cognome)
                .email(email)
                .telefono(telefono)
                .codiceFiscale(codiceFiscale)
                .redditoAnnuale(redditoAnnuale)
                .preferenzeAuto(preferenzeMarca)
                .build();
    }

    private String generaCodiceFiscaleRandom() {
        StringBuilder cf = new StringBuilder();
        String simboli = "ABCDEFGHIJKLMOPQRSTUVXYZ0123456789";
        for (int i = 0; i < 16; i++) {
            cf.append(simboli.charAt(rnd.nextInt(simboli.length())));
        }
        return cf.toString();
    }

    private String generaPreferenzeMarcaRandom() {
        String[] marche = {"MERCEDES", "FIAT", "TOYOTA", "AUDI", "VOLKSWAGEN", "ALFAROMEO", "SUZUKI"};
        return marche[rnd.nextInt(marche.length)];
    }
}
