package concessionario.model.repartoVendita;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.GeneratoreTarga;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.automobile.TipoAlimentazione;
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.AnagraficaClientiImpl;
import concessionario.model.cliente.Cliente;
import concessionario.model.listino.Listino;

public class ServizioVenditeTest {

    private Listino listino;
    private Listino listinoUsato;
    private AnagraficaClienti anagraficaClienti;
    private RegistroVendite registroVendite;
    private ServizioVendite servizioVendite;
    private Automobile auto;
    private Cliente cliente;

    @BeforeEach
    public void init() {
        listino = new Listino();
        listinoUsato = new Listino();
        anagraficaClienti = new AnagraficaClientiImpl();
        registroVendite = new RegistroVendite();
        servizioVendite = new ServizioVendite(listino, listinoUsato, anagraficaClienti, registroVendite);
        
        // Aggiornamento del costruttore di Automobile con TipoAlimentazione
        auto = new Automobile("Golf", "Volkswagen", 0, 5, 1400, 120, GeneratoreTarga.generateTarga(), StatoMacchina.NUOVO, TipoAlimentazione.DIESEL);

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
        anagraficaClienti.registraCliente(cliente);
        listino.aggiungiAuto(auto, 25000.0);

        Optional<Preventivo> preventivoOpt = servizioVendite.generaPreventivo(auto, cliente);

        // Verifica che il preventivo non sia nullo e che contenga i dati corretti
        assertTrue(preventivoOpt.isPresent(), "Il preventivo generato dovrebbe essere presente");
        Preventivo preventivo = preventivoOpt.get();
        assertEquals(cliente, preventivo.getCliente(), "Il cliente del preventivo dovrebbe essere corretto");
        assertEquals(auto, preventivo.getAuto(), "L'auto del preventivo dovrebbe essere corretta");
        assertEquals(25000.0, preventivo.getPrezzoTotale(), 0.01, "Il prezzo totale del preventivo dovrebbe essere corretto");
    }

    @Test
    public void testGeneraPreventivoClienteNonPresente() {
        listino.aggiungiAuto(auto, 200000.0);

        // Tenta di generare un preventivo per un cliente non registrato
        Optional<Preventivo> preventivoOpt = servizioVendite.generaPreventivo(auto, cliente);

        // Verifica che il preventivo sia vuoto poiché il cliente non è registrato
        assertFalse(preventivoOpt.isPresent(), "Il preventivo dovrebbe essere assente se il cliente non è presente");
    }

    @Test
    public void testVendiAuto() {
        anagraficaClienti.registraCliente(cliente);
        listino.aggiungiAuto(auto, 20000.0);

        // Crea un preventivo e vendi l'auto
        Optional<Preventivo> preventivoOpt = servizioVendite.generaPreventivo(auto, cliente);
        assertTrue(preventivoOpt.isPresent(), "Il preventivo dovrebbe essere presente per un cliente registrato");
        Preventivo preventivo = preventivoOpt.get();
        boolean venditaEffettuata = servizioVendite.vendiAuto(preventivo);

        // Verifica che la vendita sia stata effettuata e che il preventivo sia stato registrato
        assertTrue(venditaEffettuata, "La vendita dovrebbe essere effettuata con successo");
        assertTrue(registroVendite.getListaPreventivi().contains(preventivo), "Il registro vendite dovrebbe contenere il preventivo");
    }
}
