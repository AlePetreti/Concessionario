
package concessionario.model.suggerimenti;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.Cliente;
import concessionario.model.listino.ElementoListino;
import concessionario.model.listino.Listino;

import java.util.List;

public class SuggeritoreAutoTest {

    private SuggeritoreAuto suggeritoreAuto;
    private Listino listinoAuto;
    private Listino listinoUsato;
    private Cliente cliente;

    @BeforeEach
    public void init() {
        suggeritoreAuto = new SuggeritoreAuto();
        listinoAuto = new Listino();
        listinoUsato = new Listino();

        // Auto nuove
        listinoAuto.aggiungiAuto(new Automobile("Model X", "Tesla", 0, 4, 2000, 300, "XYZ123", StatoMacchina.NUOVO), 30000);
        listinoAuto.aggiungiAuto(new Automobile("Model Y", "Tesla", 0, 4, 2000, 300, "ABC456", StatoMacchina.NUOVO), 60000);
        listinoAuto.aggiungiAuto(new Automobile("Golf", "Volkswagen", 0, 4, 1600, 110, "DEF789", StatoMacchina.NUOVO), 20000);

        // Auto usate
        listinoUsato.aggiungiAuto(new Automobile("Polo", "Volkswagen", 50000, 4, 1400, 90, "JKL012", StatoMacchina.USATO), 8000);
        listinoUsato.aggiungiAuto(new Automobile("Civic", "Honda", 80000, 4, 1600, 120, "MNO345", StatoMacchina.USATO), 6000);
    }

    @Test
    public void testSuggerisciAuto_PreferenzaEPrezzoCompatibile() {
        cliente = new Cliente.Builder()
                .nome("Mario")
                .cognome("Rossi")
                .redditoAnnuale(100000)
                .preferenzeAuto("Tesla") // preferenza per la marca
                .build();

        List<ElementoListino> autoSuggerite = suggeritoreAuto.suggerisciAuto(cliente, listinoAuto, listinoUsato);

        // Dovrebbe restituire 1 auto
        assertEquals(1, autoSuggerite.size(), "Dovrebbe suggerire una sola auto compatibile con il reddito e la preferenza.");
        assertEquals("Model X", autoSuggerite.get(0).getAutomobile().getModello(), "Dovrebbe suggerire la Model X.");
    }

    @Test
    public void testSuggerisciAuto_SenzaPreferenza() {
        cliente = new Cliente.Builder()
                .nome("Luigi")
                .cognome("Verdi")
                .redditoAnnuale(60000)
                .build();

        List<ElementoListino> autoSuggerite = suggeritoreAuto.suggerisciAuto(cliente, listinoAuto, listinoUsato);

        // Dovrebbe suggerire "Golf"
        assertEquals(1, autoSuggerite.size(), "Dovrebbe suggerire una sola auto compatibile senza preferenze.");
        assertEquals("Golf", autoSuggerite.get(0).getAutomobile().getModello(), "Dovrebbe suggerire la Golf.");
    }

    @Test
    public void testSuggerisciAuto_PreferenzaNonDisponibile() {
        cliente = new Cliente.Builder()
                .nome("Giuseppe")
                .cognome("Bianchi")
                .redditoAnnuale(50000)
                .preferenzeAuto("Fiat")
                .build();

        List<ElementoListino> autoSuggerite = suggeritoreAuto.suggerisciAuto(cliente, listinoAuto, listinoUsato);
        assertTrue(autoSuggerite.size() == 2, "Non dovrebbe suggerire auto perché la preferenza non è disponibile.");
        
    }

    @Test
    public void testSuggerisciAuto_MultipliModelliCompatibili() {
        cliente = new Cliente.Builder()
                .nome("Sergio")
                .cognome("Rossi")
                .redditoAnnuale(120000)
                .build();

        List<ElementoListino> autoSuggerite = suggeritoreAuto.suggerisciAuto(cliente, listinoAuto, listinoUsato);

        assertTrue(autoSuggerite.size() >= 1, "Dovrebbe suggerire almeno una auto compatibile.");
        boolean contieneModelX = autoSuggerite.stream().anyMatch(el -> el.getAutomobile().getModello().equals("Model X"));
        boolean contieneGolf = autoSuggerite.stream().anyMatch(el -> el.getAutomobile().getModello().equals("Golf"));

        assertTrue(contieneModelX || contieneGolf, "La lista dovrebbe contenere Model X o Golf.");
    }

    @Test
    public void testSuggerisciAuto_PreferenzaCompatibileUsata() {
        cliente = new Cliente.Builder()
                .nome("Alberto")
                .cognome("Ferrari")
                .redditoAnnuale(20000)
                .preferenzeAuto("Honda")
                .build();

        List<ElementoListino> autoSuggerite = suggeritoreAuto.suggerisciAuto(cliente, listinoAuto, listinoUsato);

        assertEquals(1, autoSuggerite.size(), "Dovrebbe suggerire una sola auto usata della marca preferita.");
        assertEquals("Civic", autoSuggerite.get(0).getAutomobile().getModello(), "Dovrebbe suggerire la Polo.");
    }
}
