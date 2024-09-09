package concessionario.model.autonoleggio.auto_noleggio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AutomobileNoleggioTest {

    private AutomobileNoleggio auto;

    @BeforeEach
    public void setUp() {
        auto = new AutomobileNoleggio("Kuga", "Ford", 5, 1600, 120, DisponibilitàAuto.Disponibile);
    }

    @Test
    public void testGetModello() {
        assertEquals("Kuga", auto.getModello());
    }

    @Test
    public void testGetMarca() {
        assertEquals("Ford", auto.getMarca());
    }

    @Test
    public void testGetNumeroPorte() {
        assertEquals(5, auto.getNumeroPorte());
    }

    @Test
    public void testGetCilindrata() {
        assertEquals(1600, auto.getCilindrata());
    }

    @Test
    public void testGetCavalli() {
        assertEquals(120, auto.getCavalli());
    }

    @Test
    public void testGetDisponibilitàAuto() {
        assertEquals(DisponibilitàAuto.Disponibile, auto.getDisponibilitàAuto());
    }

    @Test
    public void testSetCostoNoleggio() {
        auto.setCostoNoleggio(50.0);
        assertEquals(50.0, auto.getCostoNoleggio());
    }

    @Test
    public void testGetStato() {
        assertEquals("Disponibile", auto.getStato());
    }
}




