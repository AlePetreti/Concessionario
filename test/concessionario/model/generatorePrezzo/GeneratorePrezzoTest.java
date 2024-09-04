package concessionario.model.generatorePrezzo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class GeneratorePrezzoTest {

    private static final double PREZZO_MIN = 10000.0;
    private static final double PREZZO_MAX = 50000.0;

    @Test
    public void testGeneraPrezzoTraUnRange() {
        double prezzo = GeneratorePrezzo.generaPrezzo();
        assertTrue(prezzo >= PREZZO_MIN && prezzo <= PREZZO_MAX, 
            "Il prezzo generato dovrebbe essere compreso tra " + PREZZO_MIN + " e " + PREZZO_MAX);
    }
}
