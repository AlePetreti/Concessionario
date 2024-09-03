package concessionario.model.autonoleggio.auto_noleggio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FactoryAutomobiliNoleggioTest {

    private FactoryAutomobiliNoleggio factory;

    @BeforeEach
    public void setUp() {
        factory = new FactoryAutomobiliNoleggio();
    }
    @Test
    public void testCreaAutoRandom() {
        List<AutomobileNoleggio> automobili = factory.creaAutoRandom();

        assertNotNull(automobili, "La lista di automobili non dovrebbe essere null");
        assertEquals(10, automobili.size(), "La lista dovrebbe contenere 10 automobili");

        for (AutomobileNoleggio auto : automobili) {
            assertNotNull(auto, "L'automobile non dovrebbe essere null");
            assertTrue(isMarcaValida(auto.getMarca()), "La marca dell'auto dovrebbe essere valida");
            assertTrue(isModelloValido(auto.getModello(), auto.getMarca()), "Il modello dell'auto dovrebbe essere valido");
            assertTrue(auto.getNumeroPorte() == 3 || auto.getNumeroPorte() == 5, "Il numero di porte dovrebbe essere 3 o 5");
            assertTrue(auto.getCilindrata() >= 1200 && auto.getCilindrata() <= 2500, "La cilindrata dovrebbe essere compresa tra 1200 e 2500");
            assertTrue(auto.getCavalli() >= 75 && auto.getCavalli() <= 400, "I cavalli dovrebbero essere compresi tra 75 e 400");
            assertEquals(DisponibilitàAuto.Disponibile, auto.getDisponibilitàAuto(), "L'auto dovrebbe essere disponibile");
        }
    }

    // Metodi di supporto per validare la marca e il modello
    private boolean isMarcaValida(String marca) {
        String[] marche = {"KIA", "NISSAN", "MINI", "BMW", "FORD", "TESLA", "HONDA"};
        for (String m : marche) {
            if (m.equals(marca)) {
                return true;
            }
        }
        return false;
    }

    private boolean isModelloValido(String modello, String marca) {
        switch (marca) {
            case "KIA":
                return List.of("SPORTAGE", "SORENTO", "PICANTO", "EV6", "XCEED").contains(modello);
            case "NISSAN":
                return List.of("QASHQAI", "LEAF", "JUKE", "TOWNSTAR", "GTR").contains(modello);
            case "MINI":
                return List.of("COOPERS", "COOPERD", "COUNTRYMAN", "COOPERSE").contains(modello);
            case "BMW":
                return List.of("SERIE1", "M2", "SERIE5", "I4").contains(modello);
            case "FORD":
                return List.of("FOCUS", "PUMA", "KUGA", "FIESTA").contains(modello);
            case "TESLA":
                return List.of("MODEL3", "MODELS", "MODELY", "MODELSPLAID").contains(modello);
            case "HONDA":
                return List.of("CIVIC", "CR-V", "ZR-V", "HR-V").contains(modello);
            default:
                return false;
        }
    }
}
