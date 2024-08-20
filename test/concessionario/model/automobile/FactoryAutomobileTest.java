package concessionario.model.automobile;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FactoryAutomobileTest {
    
    private FactoryAutomobile factory;

    @BeforeEach
    public void init() {
        factory = new FactoryAutomobile();
    }

    @Test
    public void testCreaAutoRandomNonNull() {
        Automobile auto = factory.creaAutoRandom();
        assertNotNull(auto);
    }

    @Test
    public void testCreaAutoRandomMarcaValida() {
        // Verifica che l'auto generata abbia una marca valida
        Automobile auto = factory.creaAutoRandom();
        String[] marche = {"MERCEDES", "FIAT", "TOYOTA", "AUDI", "VOLKSWAGEN", "ALFAROMEO", "SUZUKI"};
        assertTrue(isValueInArray(auto.getMarca(), marche), "La marca dell'automobile generata dovrebbe essere valida");
    }

    @Test
    public void testCreaAutoRandomModelloCoerenteConMarca() {
        // Verifica che il modello sia coerente con la marca
        Automobile auto = factory.creaAutoRandom();
        assertTrue(isModelloCoerenteConMarca(auto.getModello(), auto.getMarca()), "Il modello non è coerente con la marca");
    }

    @Test
    public void testCreaAutoRandomNumeroPorteValido() {
        // Verifica che l'auto generata abbia un numero di porte valido
        Automobile auto = factory.creaAutoRandom();
        int[] numeroPorte = {3, 5};
        assertTrue(isValuePresent(auto.getNumeroPorte(), numeroPorte), "Il numero di porte dovrebbe essere valido");
    }

    @Test
    public void testCreaAutoRandomCilindrataValida() {
        // Verifica che l'auto generata abbia una cilindrata valida
        Automobile auto = factory.creaAutoRandom();
        int[] cilindrate = {1200, 1400, 1600, 1900, 2000, 2200, 3000, 4000};
        assertTrue(isValuePresent(auto.getCilindrata(), cilindrate), "La cilindrata dovrebbe essere valida");
    }

    @Test
    public void testCreaAutoRandomCavalliValidi() {
        // Verifica che l'auto generata abbia un numero di cavalli valido
        Automobile auto = factory.creaAutoRandom();
        int[] cavalli = {75, 90, 116, 120, 150, 190, 210, 350, 400};
        assertTrue(isValuePresent(auto.getCavalli(), cavalli), "Il numero di cavalli dovrebbe essere valido");
    }

    // Metodo di supporto per verificare se un valore è contenuto in un array
    private boolean isValuePresent(int value, int[] array) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    private boolean isValueInArray(String value, String[] array) {
        for (String s : array) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }

    // Metodo di supporto per verificare se un modello è coerente con la marca
    private boolean isModelloCoerenteConMarca(String modello, String marca) {
        switch (marca) {
            case "MERCEDES":
                return isValueInArray(modello, new String[]{"ClasseA", "ClasseB", "Classe S", "ClasseG", "classeC"});
            case "FIAT":
                return isValueInArray(modello, new String[]{"PANDA", "PUNTO", "500", "DOBLO"});
            case "TOYOTA":
                return isValueInArray(modello, new String[]{"SUPRA", "YARIS", "RAV4", "AYGO"});
            case "AUDI":
                return isValueInArray(modello, new String[]{"A8", "RSQ8", "S6", "A3"});
            case "VOLKSWAGEN":
                return isValueInArray(modello, new String[]{"GOLF", "POLO", "TIGUAN", "TUAREG"});
            case "ALFAROMEO":
                return isValueInArray(modello, new String[]{"STELVIO", "GIULIA", "TONALE", "159"});
            case "SUZUKI":
                return isValueInArray(modello, new String[]{"SWIFT", "VITARA", "JIMNY", "IGNIS"});
            default:
                return false;
        }
    }
}
