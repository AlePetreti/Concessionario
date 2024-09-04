package concessionario.model.repartoVendita;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.GeneratoreTarga;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.AnagraficaClientiImpl;
import concessionario.model.cliente.Cliente;
import concessionario.model.listino.Listino;

public class ServizioVenditeTest {
    
    private Listino listino;
    private AnagraficaClienti anagraficaClienti;
    private RegistroVendite registroVendite;
    private ServizioVendite servizioVendite;
    private Automobile auto;
    private Cliente cliente;

    @BeforeEach
    public void init() {
        listino = new Listino();
        anagraficaClienti = new AnagraficaClientiImpl();
        registroVendite = new RegistroVendite();
        servizioVendite = new ServizioVendite(listino, anagraficaClienti, registroVendite);
        auto = new Automobile("Golf", "Volkswagen", 0, 5, 1400, 120, GeneratoreTarga.generateTarga(),StatoMacchina.NUOVO);

        // Uso del pattern Builder per Cliente
        cliente = new Cliente.Builder()
                .nome("Mario")
                .cognome("Rossi")
                .email("mario.rossi@example.com")
                .telefono("1234567890")
                .codiceFiscale("RSSMRA70A01H501T")
                .build();
    }

    @Test
    public void testGeneraPreventivoClientePresente() {
        // Aggiungi il cliente e l'auto al listino
        anagraficaClienti.registraCliente(cliente);
        listino.aggiungiAuto(auto, 25000.0);

        // Genera il preventivo
        Preventivo preventivo = servizioVendite.generaPreventivo(auto, cliente);

        // Verifica che il preventivo non sia nullo e che contenga i dati corretti
        assertNotNull(preventivo, "Il preventivo generato non dovrebbe essere null");
        assertEquals(cliente, preventivo.getCliente(), "Il cliente del preventivo dovrebbe essere corretto");
        assertEquals(auto, preventivo.getAuto(), "L'auto del preventivo dovrebbe essere corretta");
        assertEquals(25000.0, preventivo.getPrezzoTotale(), 0.01, "Il prezzo totale del preventivo dovrebbe essere corretto");
    }

    @Test
    public void testGeneraPreventivoClienteNonPresente() {
        // Aggiungi solo l'auto al listino
        listino.aggiungiAuto(auto, 200000.0);

        // Tenta di generare un preventivo per un cliente non registrato
        Preventivo preventivo = servizioVendite.generaPreventivo(auto, cliente);

        // Verifica che il preventivo sia nullo poiché il cliente non è registrato
        assertNull(preventivo, "Il preventivo dovrebbe essere null se il cliente non è presente");
    }

    @Test
    public void testVendiAuto() {
        // Aggiungi il cliente e l'auto al listino
        anagraficaClienti.registraCliente(cliente);
        listino.aggiungiAuto(auto, 20000.0);

        // Crea un preventivo e vendi l'auto
        Preventivo preventivo = new Preventivo(auto, 20000.0, cliente);
        boolean venditaEffettuata = servizioVendite.vendiAuto(preventivo);

        // Verifica che la vendita sia stata effettuata e che il preventivo sia stato registrato
        assertTrue(venditaEffettuata, "La vendita dovrebbe essere effettuata con successo");
        assertTrue(registroVendite.getListaPreventivi().contains(preventivo), "Il registro vendite dovrebbe contenere il preventivo");
    }
}
