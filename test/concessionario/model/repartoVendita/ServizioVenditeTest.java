package concessionario.model.repartoVendita;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import concessionario.model.Listino;
import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.AnagraficaClientiImpl;
import concessionario.model.cliente.Cliente;

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
        auto = new Automobile("Golf", "Volkswagen", 0, 5, 1400, 120, StatoMacchina.NUOVO);
        cliente = new Cliente("Mario", "Rossi", "mario.rossi@example.com", "1234567890", "RSSMRA70A01H501T");
    }

    @Test
    public void testGeneraPreventivoClientePresente() {
        anagraficaClienti.registraCliente(cliente);
        listino.aggiungiAuto(auto, 25000.0);

        Preventivo preventivo = servizioVendite.generaPreventivo(auto, cliente);

        assertNotNull(preventivo);
        assertEquals(cliente, preventivo.getCliente());
        assertEquals(auto, preventivo.getAuto());
        assertEquals(25000.0, preventivo.getPrezzoTotale(), 0.01);
    }

    @Test
    public void testGeneraPreventivoClienteNonPresente() {

        listino.aggiungiAuto(auto, 200000);
        Preventivo preventivo = servizioVendite.generaPreventivo(auto, cliente);
        assertNull(preventivo);
    }

    @Test
    void testVendiAuto() {
        anagraficaClienti.registraCliente(cliente);
        listino.aggiungiAuto(auto, 20000);

        Preventivo preventivo = new Preventivo(auto, 20000, cliente);
        boolean venditaEffettuata = servizioVendite.vendiAuto(preventivo);

        assertTrue(venditaEffettuata);
        assertTrue(registroVendite.getListaPreventivi().contains(preventivo));
    }


}
