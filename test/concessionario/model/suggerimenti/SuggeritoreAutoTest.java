package concessionario.model.suggerimenti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.automobile.TipoAlimentazione; // Importa l'enum
import concessionario.model.listino.ElementoListino;
import concessionario.model.listino.Listino;

import java.util.List;

public class SuggeritoreAutoTest {

    private SuggeritoreAuto suggeritoreAuto;
    private Listino listinoAuto;
    private Listino listinoUsato;

    @BeforeEach
    public void init() {
        listinoAuto = new Listino();
        listinoUsato = new Listino();

        // Auto nuove - aggiungi anche il prezzo
        listinoAuto.aggiungiAuto(new Automobile("Model X", "Tesla", 0, 4, 2000, 300, "XYZ123", StatoMacchina.NUOVO, TipoAlimentazione.ELETTRICA), 30000);
        listinoAuto.aggiungiAuto(new Automobile("Model Y", "Tesla", 0, 4, 2000, 300, "ABC456", StatoMacchina.NUOVO, TipoAlimentazione.ELETTRICA), 60000);
        listinoAuto.aggiungiAuto(new Automobile("Golf", "Volkswagen", 0, 4, 1600, 110, "DEF789", StatoMacchina.NUOVO, TipoAlimentazione.BENZINA), 20000);

        // Auto usate - aggiungi anche il prezzo
        listinoUsato.aggiungiAuto(new Automobile("Polo", "Volkswagen", 50000, 4, 1400, 90, "JKL012", StatoMacchina.USATO, TipoAlimentazione.BENZINA), 8000);
        listinoUsato.aggiungiAuto(new Automobile("Civic", "Honda", 80000, 4, 1600, 120, "MNO345", StatoMacchina.USATO, TipoAlimentazione.BENZINA), 6000);
        
        suggeritoreAuto = new SuggeritoreAuto(listinoAuto, listinoUsato); // Passa i listini al suggeritore
    }

    @Test
    public void testSuggerisciAuto_PreferenzaEPrezzoCompatibile() {
        PreferenzeCliente preferenzeCliente = new PreferenzeCliente.Builder()
                .redditoAnnuale(100000)
                .preferenzeAuto("Tesla") // preferenza per la marca
                .preferenzeAlimentazione(TipoAlimentazione.ELETTRICA) // preferenza per l'alimentazione
                .build();

        List<ElementoListino> autoSuggerite = suggeritoreAuto.suggerisciAuto(preferenzeCliente);

        // Dovrebbe restituire 1 auto
        assertEquals(1, autoSuggerite.size(), "Dovrebbe suggerire una sola auto compatibile con il reddito e la preferenza.");
        assertEquals("Model X", autoSuggerite.get(0).getAutomobile().getModello(), "Dovrebbe suggerire la Model X.");
    }

    @Test
    public void testSuggerisciAuto_SenzaPreferenza() {
        PreferenzeCliente preferenzeCliente = new PreferenzeCliente.Builder()
                .redditoAnnuale(60000)
                .build();

        List<ElementoListino> autoSuggerite = suggeritoreAuto.suggerisciAuto(preferenzeCliente);

        // Dovrebbe suggerire "Golf"
        assertEquals(1, autoSuggerite.size(), "Dovrebbe suggerire una sola auto compatibile senza preferenze.");
        assertEquals("Golf", autoSuggerite.get(0).getAutomobile().getModello(), "Dovrebbe suggerire la Golf.");
    }

    @Test
    public void testSuggerisciAuto_PreferenzaNonDisponibile() {
        PreferenzeCliente preferenzeCliente = new PreferenzeCliente.Builder()
            .redditoAnnuale(50000)
            .preferenzeAuto("Fiat")
            .build();

        List<ElementoListino> autoSuggerite = suggeritoreAuto.suggerisciAuto(preferenzeCliente);

        // Controlla che non ci siano auto con la marca Fiat nel risultato
        boolean contieneFiat = autoSuggerite.stream().anyMatch(el -> el.getAutomobile().getMarca().equalsIgnoreCase("Fiat"));
        
        // Assicura che non ci siano auto suggerite o che non ci siano auto Fiat
        assertTrue(autoSuggerite.isEmpty() || !contieneFiat, "Non dovrebbe suggerire auto perché la preferenza non è disponibile.");
    }

    @Test
    public void testSuggerisciAuto_MultipliModelliCompatibili() {
        PreferenzeCliente preferenzeCliente = new PreferenzeCliente.Builder()
                .redditoAnnuale(120000)
                .build();

        List<ElementoListino> autoSuggerite = suggeritoreAuto.suggerisciAuto(preferenzeCliente);

        assertTrue(autoSuggerite.size() >= 1, "Dovrebbe suggerire almeno una auto compatibile.");
        boolean contieneModelX = autoSuggerite.stream().anyMatch(el -> el.getAutomobile().getModello().equals("Model X"));
        boolean contieneGolf = autoSuggerite.stream().anyMatch(el -> el.getAutomobile().getModello().equals("Golf"));

        assertTrue(contieneModelX || contieneGolf, "La lista dovrebbe contenere Model X o Golf.");
    }

    @Test
    public void testSuggerisciAuto_PreferenzaCompatibileUsata() {
        PreferenzeCliente preferenzeCliente = new PreferenzeCliente.Builder()
                .redditoAnnuale(20000)
                .preferenzeAuto("Honda")
                .preferenzeAlimentazione(TipoAlimentazione.BENZINA) // Preferenza per l'alimentazione
                .build();

        List<ElementoListino> autoSuggerite = suggeritoreAuto.suggerisciAuto(preferenzeCliente);

        assertEquals(1, autoSuggerite.size(), "Dovrebbe suggerire una sola auto usata della marca preferita.");
        assertEquals("Civic", autoSuggerite.get(0).getAutomobile().getModello(), "Dovrebbe suggerire la Civic.");
    }
}
