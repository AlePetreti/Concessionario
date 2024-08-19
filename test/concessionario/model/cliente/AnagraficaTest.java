package concessionario.model.cliente;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnagraficaTest {

    private AnagraficaClienti anagraficaClienti;
    private Cliente cliente;
    private Cliente cliente2;

    @BeforeEach
    public void init() {
        anagraficaClienti = new AnagraficaClientiImpl();
        cliente = new Cliente("Mario", "Rossi", "mario.rossi@example.com", "1234567890", "MRORSS80A01H501T");
        cliente2 = new Cliente("Luigi", "Verdi", "luigi.verdi@example.com", "0987654321", "VRDLGU80A01H501Z");
    }

    @Test
    public void testRegistraClienteSuccesso() {

        assertTrue(anagraficaClienti.registraCliente(cliente));
        assertTrue(anagraficaClienti.registraCliente(cliente2));
        List<Cliente> clienti = anagraficaClienti.getClienti();
        assertEquals(2, clienti.size());
        assertTrue(clienti.contains(cliente));
        assertTrue(clienti.contains(cliente2));
    }

    @Test
    public void testRegistraClienteDuplicato() {
        assertTrue(anagraficaClienti.registraCliente(cliente));
        assertFalse(anagraficaClienti.registraCliente(cliente));
        assertEquals(1, anagraficaClienti.getClienti().size());
    }

    @Test
    public void testCercaClienteSuccesso() {
        anagraficaClienti.registraCliente(cliente);
        Cliente trovato = anagraficaClienti.cercaCliente("MRORSS80A01H501T");
        assertNotNull(trovato);
        assertEquals(cliente, trovato);
    }

    @Test
    public void testCercaClienteNonTrovato() {
        anagraficaClienti.registraCliente(cliente);
        Cliente trovato = anagraficaClienti.cercaCliente("VRDLGU80A01H501Z");
        assertNull(trovato);
    }

    @Test
    public void testCercaClientiSuccesso() {
        anagraficaClienti.registraCliente(cliente);
        anagraficaClienti.registraCliente(cliente2);
        List<Cliente> trovati = anagraficaClienti.cercaClienti("Mario");
        assertEquals(1, trovati.size());
        assertEquals(cliente, trovati.get(0));
    }

    @Test
    public void testCercaClientiParolaChiaveNonTrovata() {
        anagraficaClienti.registraCliente(cliente);
        anagraficaClienti.registraCliente(cliente2);
        List<Cliente> trovati = anagraficaClienti.cercaClienti("Giovanni");
        assertTrue(trovati.isEmpty());
    }

    @Test
    public void testGetClienti() {
        anagraficaClienti.registraCliente(cliente);
        anagraficaClienti.registraCliente(cliente2);
        List<Cliente> clienti = anagraficaClienti.getClienti();
        assertEquals(2, clienti.size());
        assertTrue(clienti.contains(cliente));
        assertTrue(clienti.contains(cliente2));

        // Verifico che la lista restituita sia una copia e non la lista originale
        clienti.add(new Cliente("Giovanni", "Bianchi", "giovanni.bianchi@example.com", "0987654322", "BNCGVN80A01H501L"));
        assertEquals(2, anagraficaClienti.getClienti().size());
    }
}
