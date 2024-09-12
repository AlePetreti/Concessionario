package concessionario.model.automobile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutomobileTest {

    private Automobile auto;
    
    @BeforeEach
    private void init() {
        auto = new Automobile("Classe A", "Mercedes", 0, 5, 2000, 500, GeneratoreTarga.generateTarga(), StatoMacchina.NUOVO);
    }

    @Test
    public void testGetters() {
        assertEquals("Classe A", auto.getModello());
        assertEquals("Mercedes", auto.getMarca());
        assertEquals(0, auto.getKm());
        assertEquals(5, auto.getNumeroPorte());
        assertEquals(2000, auto.getCilindrata());
        assertEquals(500, auto.getCavalli());
        assertEquals(StatoMacchina.NUOVO, auto.getStatoMacchina());
    }
}
