package concessionario.model.autonoleggio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AssicurazioneTest {

    @Test
    public void testCalcolaCostoAssicurazioneCoperturaBassa() {
        int giorni = 3;
        String copertura = "Copertura Bassa";

        double costo = Assicurazione.calcolaCostoAssicurazione(giorni, copertura);

        // Verifica che il costo sia nel range atteso
        double costoMinimo = giorni * (5 * 40); // minimo possibile
        double costoMassimo = giorni * (5 * 60); // massimo possibile

        assertTrue(costo >= costoMinimo && costo <= costoMassimo,
                "Il costo per la Copertura Bassa dovrebbe essere tra " + costoMinimo + " e " + costoMassimo);
    }

    @Test
    public void testCalcolaCostoAssicurazioneCoperturaMedia() {
        int giorni = 5;
        String copertura = "Copertura Media";

        double costo = Assicurazione.calcolaCostoAssicurazione(giorni, copertura);

        // Verifica che il costo sia nel range atteso
        double costoMinimo = giorni * (5 * 80); // minimo possibile
        double costoMassimo = giorni * (5 * 120); // massimo possibile

        assertTrue(costo >= costoMinimo && costo <= costoMassimo,
                "Il costo per la Copertura Media dovrebbe essere tra " + costoMinimo + " e " + costoMassimo);
    }

    @Test
    public void testCalcolaCostoAssicurazioneCoperturaAlta() {
        int giorni = 7;
        String copertura = "Copertura Alta";

        double costo = Assicurazione.calcolaCostoAssicurazione(giorni, copertura);

        // Verifica che il costo sia nel range atteso
        double costoMinimo = giorni * (5 * 123); // minimo possibile
        double costoMassimo = giorni * (5 * 200); // massimo possibile

        assertTrue(costo >= costoMinimo && costo <= costoMassimo,
                "Il costo per la Copertura Alta dovrebbe essere tra " + costoMinimo + " e " + costoMassimo);
    }

    @Test
    public void testCalcolaCostoAssicurazioneCoperturaInesistente() {
        int giorni = 4;
        String copertura = "Copertura Inesistente";

        double costo = Assicurazione.calcolaCostoAssicurazione(giorni, copertura);

        // Verifica che il costo sia 0 per una copertura non valida
        assertEquals(0.0, costo, "Il costo per una copertura inesistente dovrebbe essere 0.0");
    }
}

