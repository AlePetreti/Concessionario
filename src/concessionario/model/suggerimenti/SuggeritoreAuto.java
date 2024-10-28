package concessionario.model.suggerimenti;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.TipoAlimentazione;
import concessionario.model.cliente.Cliente;
import concessionario.model.listino.ElementoListino;
import concessionario.model.listino.Listino;

import java.util.ArrayList;
import java.util.List;

public class SuggeritoreAuto implements Suggeritore {

    private Listino listinoAuto;
    private Listino listinoUsato;

    public SuggeritoreAuto(Listino listinoAuto, Listino listinoUsato) {
        this.listinoAuto = listinoAuto;
        this.listinoUsato = listinoUsato;
    }

    @Override
    public List<ElementoListino> suggerisciAuto(Cliente cliente) {
        List<ElementoListino> autoFiltrate = new ArrayList<>();
        List<ElementoListino> autoCompatibili = new ArrayList<>();

        double budgetCliente = cliente.getRedditoAnnuale() * 0.35;
        String preferenzaMarca = cliente.getPreferenzeAuto();
        int preferenzaNumeroPorte = cliente.getPreferenzeNumeroPorte();
        TipoAlimentazione preferenzaAlimentazione = cliente.getPreferenzeAlimentazione();

        // Filtraggio auto in base al budget
        for (ElementoListino elemento : listinoAuto.getListino()) {
            Automobile auto = elemento.getAutomobile();
            if (elemento.getPrezzo() <= budgetCliente) {
                if (rispettaPreferenze(auto, preferenzaMarca, preferenzaNumeroPorte, preferenzaAlimentazione)) {
                    autoFiltrate.add(elemento);
                } else {
                    autoCompatibili.add(elemento);
                }
            }
        }

        // Se nessuna auto nuova è stata filtrata, considera anche l'usato
        if (autoFiltrate.isEmpty()) {
            for (ElementoListino elemento : listinoUsato.getListino()) {
                Automobile auto = elemento.getAutomobile();
                if (elemento.getPrezzo() <= budgetCliente) {
                    if (rispettaPreferenze(auto, preferenzaMarca, preferenzaNumeroPorte, preferenzaAlimentazione)) {
                        autoFiltrate.add(elemento);
                    } else {
                        autoCompatibili.add(elemento);
                    }
                }
            }
        }

        // Se nessuna auto rispetta tutte le preferenze, considera quelle compatibili
        if (autoFiltrate.isEmpty() && !autoCompatibili.isEmpty()) {
            autoCompatibili.sort((auto1, auto2) -> calcolaRanking(auto1, auto2, cliente));
            autoFiltrate.add(autoCompatibili.get(0));
        }

        // Ordina le auto filtrate in base al ranking
        autoFiltrate.sort((auto1, auto2) -> calcolaRanking(auto1, auto2, cliente));

        return autoFiltrate;
    }

    // Metodo che verifica se l'auto rispetta le preferenze del cliente
    private boolean rispettaPreferenze(Automobile auto, String preferenzaMarca, int preferenzaNumeroPorte, TipoAlimentazione preferenzaAlimentazione) {
        boolean rispettaMarca = preferenzaMarca == null || auto.getMarca().equalsIgnoreCase(preferenzaMarca);
        boolean rispettaNumeroPorte = preferenzaNumeroPorte == 0 || auto.getNumeroPorte() == preferenzaNumeroPorte;
        boolean rispettaAlimentazione = preferenzaAlimentazione == null || auto.getTipoAlimentazione().equals(preferenzaAlimentazione);

        return rispettaMarca && rispettaNumeroPorte && rispettaAlimentazione;
    }

    // Metodo che calcola il ranking delle auto in base alle preferenze del cliente
    private int calcolaRanking(ElementoListino auto1, ElementoListino auto2, Cliente cliente) {
        int punteggio1 = 0;
        int punteggio2 = 0;

        String preferenzaMarca = cliente.getPreferenzeAuto();
        int preferenzaNumeroPorte = cliente.getPreferenzeNumeroPorte();
        TipoAlimentazione preferenzaAlimentazione = cliente.getPreferenzeAlimentazione();

        // Ranking basato su quanto l'auto rispetta le preferenze del cliente

        // Preferenza di marca
        if (auto1.getAutomobile().getMarca().equalsIgnoreCase(preferenzaMarca)) {
            punteggio1 += 5;
        }
        if (auto2.getAutomobile().getMarca().equalsIgnoreCase(preferenzaMarca)) {
            punteggio2 += 5;
        }

        // Preferenza di numero porte
        if (auto1.getAutomobile().getNumeroPorte() == preferenzaNumeroPorte) {
            punteggio1 += 3;
        }
        if (auto2.getAutomobile().getNumeroPorte() == preferenzaNumeroPorte) {
            punteggio2 += 3;
        }

        // Preferenza di alimentazione
        if (auto1.getAutomobile().getTipoAlimentazione().equals(preferenzaAlimentazione)) {
            punteggio1 += 2;
        }
        if (auto2.getAutomobile().getTipoAlimentazione().equals(preferenzaAlimentazione)) {
            punteggio2 += 2;
        }

        // Differenza prezzo rispetto al budget
        double budgetCliente = cliente.getRedditoAnnuale() * 0.35;
        double differenzaPrezzo1 = Math.abs(auto1.getPrezzo() - budgetCliente);
        double differenzaPrezzo2 = Math.abs(auto2.getPrezzo() - budgetCliente);

        if (differenzaPrezzo1 < differenzaPrezzo2) {
            punteggio1 += 10;
        } else {
            punteggio2 += 10;
        }

        return Integer.compare(punteggio2, punteggio1);
    }
}
