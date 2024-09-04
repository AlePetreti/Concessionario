package concessionario.model.officina;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.FactoryAutomobile;
import concessionario.model.listino.Listino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfficinaModelTest {

    private OfficinaModel officinaModel;
    private FactoryAutomobile factoryAutomobile;
    private Listino listinoAutoUsate;

    @BeforeEach
    void setUp() {
        listinoAutoUsate = new Listino();
        officinaModel = new OfficinaModel(listinoAutoUsate);
        factoryAutomobile = new FactoryAutomobile();
    }

    @Test
    void testAggiungiAutoUsata() {
        Automobile auto = factoryAutomobile.creaAutoRandomUsata();
        listinoAutoUsate.aggiungiAuto(auto, 10000);

        assertEquals(1, officinaModel.getAutoUsate().getListino().size());
        assertEquals(auto, officinaModel.getAutoUsate().getListino().get(0).getAutomobile());
    }

    @Test
    void testRimuoviAutoUsata() {
        // Creiamo due automobili usate
        Automobile auto1 = factoryAutomobile.creaAutoRandomUsata();
        Automobile auto2 = factoryAutomobile.creaAutoRandomUsata();

        // Aggiungiamo le auto al listino
        listinoAutoUsate.aggiungiAuto(auto1, 8000);
        listinoAutoUsate.aggiungiAuto(auto2, 12000);

        // Verifichiamo che entrambe le auto siano state aggiunte
        assertEquals(2, officinaModel.getAutoUsate().getListino().size());

        // Rimuoviamo la prima auto usando il metodo rimuoviAuto
        boolean rimosso = officinaModel.getAutoUsate().rimuoviAuto(auto1);

        // Verifichiamo che l'auto sia stata rimossa
        assertTrue(rimosso, "L'auto dovrebbe essere stata rimossa dal listino.");
        assertEquals(1, officinaModel.getAutoUsate().getListino().size(), "Dovrebbe rimanere solo un'auto nel listino.");
        assertEquals(auto2, officinaModel.getAutoUsate().getListino().get(0).getAutomobile(), "L'auto rimanente dovrebbe essere auto2.");
    }
}