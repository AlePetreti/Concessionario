package concessionario.controller.generatoretarga.test;

import concessionario.model.automobile.GeneratoreTarga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class GeneratoreTargaTest {

    @Test
    void testGenerateTargaLength() {
        // Verifica che la targa generata abbia una lunghezza di 7 caratteri
        String targa = GeneratoreTarga.generateTarga();
        assertEquals(7, targa.length(), "La targa dovrebbe essere lunga 7 caratteri");
    }

    @Test
    void testGenerateTargaFormat() {
        // Verifica che la targa generata sia nel formato corretto (LLNNNLL)
        String targa = GeneratoreTarga.generateTarga();
        
        // Controlla le prime due lettere
        assertTrue(Character.isLetter(targa.charAt(0)), "Il primo carattere dovrebbe essere una lettera");
        assertTrue(Character.isLetter(targa.charAt(1)), "Il secondo carattere dovrebbe essere una lettera");
        
        // Controlla i tre numeri
        assertTrue(Character.isDigit(targa.charAt(2)), "Il terzo carattere dovrebbe essere una cifra");
        assertTrue(Character.isDigit(targa.charAt(3)), "Il quarto carattere dovrebbe essere una cifra");
        assertTrue(Character.isDigit(targa.charAt(4)), "Il quinto carattere dovrebbe essere una cifra");

        // Controlla le ultime due lettere
        assertTrue(Character.isLetter(targa.charAt(5)), "Il sesto carattere dovrebbe essere una lettera");
        assertTrue(Character.isLetter(targa.charAt(6)), "Il settimo carattere dovrebbe essere una lettera");
    }

    @Test
    void testGenerateTargaUniqueness() {
        // Verifica che targhe generate successivamente non siano uguali (questo test potrebbe fallire raramente per coincidenza)
        String targa1 = GeneratoreTarga.generateTarga();
        String targa2 = GeneratoreTarga.generateTarga();
        assertNotEquals(targa1, targa2, "Le targhe generate successivamente non dovrebbero essere uguali");
    }

    @Test
    void testGenerateMultipleTargheUniqueness() {
        // Verifica che un numero più grande di targhe siano uniche
        int numberOfTarghe = 10000;
        Set<String> targheSet = new HashSet<>();
        for (int i = 0; i < numberOfTarghe; i++) {
            String targa = GeneratoreTarga.generateTarga();
            assertFalse(targheSet.contains(targa), "La targa non dovrebbe essere duplicata");
            targheSet.add(targa);
        }
        assertEquals(numberOfTarghe, targheSet.size(), "Dovrebbero essere state generate tutte targhe uniche");
    }
}
