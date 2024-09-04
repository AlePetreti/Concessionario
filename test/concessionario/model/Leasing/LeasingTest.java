package concessionario.model.Leasing;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LeasingTest {

    private Leasing leasing;

    @BeforeEach
    public void setUp() {
        // Crea un'istanza di Leasing con valori di esempio
        leasing = new Leasing("Fiat Panda", 36, 199.99, 15000);
    }

    @Test
    public void testGetAutoSelezionata() {
        // Verifica che il metodo getAutoSelezionata restituisca il valore corretto
        assertEquals("Fiat Panda", leasing.getAutoSelezionata(), "L'auto selezionata dovrebbe essere 'Fiat Panda'");
    }

    @Test
    public void testGetDurataContratto() {
        // Verifica che il metodo getDurataContratto restituisca il valore corretto
        assertEquals(36, leasing.getDurataContratto(), "La durata del contratto dovrebbe essere 36 mesi");
    }

    @Test
    public void testGetPrezzoMensile() {
        // Verifica che il metodo getPrezzoMensile restituisca il valore corretto
        assertEquals(199.99, leasing.getPrezzoMensile(), "Il prezzo mensile dovrebbe essere 199.99");
    }

    @Test
    public void testGetLimiteChilometrico() {
        // Verifica che il metodo getLimiteChilometrico restituisca il valore corretto
        assertEquals(15000, leasing.getLimiteChilometrico(), "Il limite chilometrico dovrebbe essere 15000 km");
    }
}

