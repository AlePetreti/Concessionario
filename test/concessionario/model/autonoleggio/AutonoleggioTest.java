package concessionario.model.autonoleggio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import concessionario.model.autonoleggio.auto_noleggio.AutomobileNoleggio;
import concessionario.model.autonoleggio.auto_noleggio.DisponibilitàAuto;

public class AutonoleggioTest {

    private Autonoleggio autonoleggio;
    private AutomobileNoleggio auto;

    @BeforeEach
    public void setUp() {
        // Inizializza l'istanza di Autonoleggio
        autonoleggio = new Autonoleggio();

        // Inizializza l'istanza di AutomobileNoleggio con stato iniziale Disponibile
        auto = new AutomobileNoleggio("CooperD", "Mini", 5, 2000, 116, DisponibilitàAuto.Disponibile);
    }

    @Test
    public void testNoleggiaAuto() throws InterruptedException {
        // Noleggia l'auto per 3 giorni
        autonoleggio.noleggiaAuto(auto, 3);

        // Verifica che l'auto sia settata come non disponibile
        assertEquals(DisponibilitàAuto.NonDisponibile, auto.getDisponibilitàAuto());

        // Verifica che il costo del noleggio sia stato impostato
        double costo = auto.getCostoNoleggio();
        assertTrue(costo >= 60.0 && costo <= 150.0, "Il costo del noleggio dovrebbe essere tra 60.0 e 150.0");

        // Attendere per un tempo superiore a 5 minuti per garantire che il thread di attesa sia passato
        Thread.sleep(6000); 

        // Verifica che l'auto diventi disponibile dopo il ritardo
        assertEquals(DisponibilitàAuto.Disponibile, auto.getDisponibilitàAuto());
    }

    @Test
    public void testGetAutomobili() {
        // Verifica che la lista delle automobili sia vuota inizialmente
        assertTrue(autonoleggio.getAutomobili().isEmpty(), "La lista delle automobili dovrebbe essere inizialmente vuota.");
    }

    @Test
    public void testAggiungiAuto() {
        // Aggiungi l'auto alla lista
        autonoleggio.getAutomobili().add(auto);

        // Verifica che l'auto sia stata aggiunta alla lista
        assertTrue(autonoleggio.getAutomobili().contains(auto), "La lista delle automobili dovrebbe contenere l'auto aggiunta.");
    }
}


