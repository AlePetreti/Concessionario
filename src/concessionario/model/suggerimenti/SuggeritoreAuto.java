package concessionario.model.suggerimenti;

import concessionario.model.automobile.Automobile;
import concessionario.model.cliente.Cliente;
import concessionario.model.listino.ElementoListino;
import concessionario.model.listino.Listino;

import java.util.ArrayList;
import java.util.List;

public class SuggeritoreAuto {

    public List<ElementoListino> suggerisciAuto(Cliente cliente, Listino listinoAuto, Listino listinoUsato, Integer numeroPorte, String tipoAlimentazione) {
        List<ElementoListino> autoSuggerite = new ArrayList<>();
        List<ElementoListino> autoCompatibili = new ArrayList<>(); // Lista per le auto compatibili senza marca

        String preferenzaMarcaCliente = cliente.getPreferenzeAuto();
        double budgetCliente = cliente.getRedditoAnnuale() * 0.35;

        // Filtri sulle auto nuove
        for (ElementoListino elemento : listinoAuto.getListino()) {
            Automobile auto = elemento.getAutomobile();
            if (filtraAuto(auto, elemento.getPrezzo(), budgetCliente, preferenzaMarcaCliente, numeroPorte, tipoAlimentazione)) {
                autoSuggerite.add(elemento);
            } else if (filtraAuto(auto, elemento.getPrezzo(), budgetCliente, null, numeroPorte, tipoAlimentazione)) {
                // Aggiungi a autoCompatibili se non rispetta la marca ma rispetta gli altri parametri
                autoCompatibili.add(elemento);
            }
        }

        // Se non ci sono auto nuove, filtra anche sulle auto usate
        if (autoSuggerite.isEmpty()) {
            for (ElementoListino elemento : listinoUsato.getListino()) {
                Automobile auto = elemento.getAutomobile();
                if (filtraAuto(auto, elemento.getPrezzo(), budgetCliente, preferenzaMarcaCliente, numeroPorte, tipoAlimentazione)) {
                    autoSuggerite.add(elemento);
                } else if (filtraAuto(auto, elemento.getPrezzo(), budgetCliente, null, numeroPorte, tipoAlimentazione)) {
                    // Aggiungi a autoCompatibili se non rispetta la marca ma rispetta gli altri parametri
                    autoCompatibili.add(elemento);
                }
            }
        }

        // Se non ci sono auto che soddisfano la preferenza di marca, suggerisci una auto compatibile
        if (autoSuggerite.isEmpty() && !autoCompatibili.isEmpty()) {
            autoSuggerite.add(autoCompatibili.get(0)); // Suggerisci la prima auto compatibile
        }

        // Dopo i filtri, esegui il ranking
        autoSuggerite.sort((auto1, auto2) -> calcolaRanking(auto1, auto2, cliente));

        return autoSuggerite;
    }

    // Metodo che verifica se l'auto rispetta i criteri del cliente
    private boolean filtraAuto(Automobile auto, double prezzo, double budget, String preferenzaMarca, Integer numeroPorte, String tipoAlimentazione) {
        boolean rispettaPrezzo = prezzo <= budget;
        boolean rispettaMarca = preferenzaMarca == null || auto.getMarca().equalsIgnoreCase(preferenzaMarca);
        boolean rispettaPorte = (numeroPorte == null || numeroPorte == 0) || auto.getNumeroPorte() == numeroPorte;
        boolean rispettaAlimentazione = tipoAlimentazione == null || auto.getTipoAlimentazione().equalsIgnoreCase(tipoAlimentazione);
        
        return rispettaPrezzo && rispettaMarca && rispettaPorte && rispettaAlimentazione;
    }

    private int calcolaRanking(ElementoListino auto1, ElementoListino auto2, Cliente cliente) {
        int punteggio1 = 0;
        int punteggio2 = 0;

        double budgetCliente = cliente.getRedditoAnnuale() * 0.35;
        double differenzaPrezzo1 = Math.abs(auto1.getPrezzo() - budgetCliente);
        double differenzaPrezzo2 = Math.abs(auto2.getPrezzo() - budgetCliente);

        // Aggiunta punteggio in base alla differenza di prezzo
        if (differenzaPrezzo1 < differenzaPrezzo2) {
            punteggio1 += 10; 
        } else {
            punteggio2 += 10;
        }

        // Preferenza per la marca del cliente
        if (auto1.getAutomobile().getMarca().equalsIgnoreCase(cliente.getPreferenzeAuto())) {
            punteggio1 += 5;
        }
        if (auto2.getAutomobile().getMarca().equalsIgnoreCase(cliente.getPreferenzeAuto())) {
            punteggio2 += 5;
        }

        // Minor chilometraggio (per auto usate)
        if (auto1.getAutomobile().getKm() < auto2.getAutomobile().getKm()) {
            punteggio1 += 3;
        } else {
            punteggio2 += 3;
        }

        return Integer.compare(punteggio2, punteggio1); // auto con punteggio più alto vince
    }
}
