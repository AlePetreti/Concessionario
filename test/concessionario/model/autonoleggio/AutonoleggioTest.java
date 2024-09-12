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
    public void testNoleggiaAuto() {
        // Aggiungi l'auto alla lista
        autonoleggio.getAutomobili().add(auto);

        // Chiama il metodo da testare
        autonoleggio.noleggiaAuto(auto, 3); // Noleggio per 3 giorni

        // Attendi brevemente per permettere al thread di modificare lo stato (se necessario)
        try {
            Thread.sleep(100); // Attesa breve per assicurarsi che il thread non interferisca
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verifica che lo stato dell'auto sia stato impostato su "NonDisponibile"
        String statoPrevisto = DisponibilitàAuto.NonDisponibile.toString();
        String statoEffettivo = auto.getStato();

        // Log dello stato per il debug
        System.out.println("Stato previsto: " + statoPrevisto);
        System.out.println("Stato effettivo: " + statoEffettivo);

        // Verifica dello stato
        assertEquals(statoPrevisto, statoEffettivo, "L'auto dovrebbe essere non disponibile subito dopo il noleggio.");
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


