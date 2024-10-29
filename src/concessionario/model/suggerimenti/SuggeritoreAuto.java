package concessionario.model.suggerimenti;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.TipoAlimentazione;
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
    public List<ElementoListino> suggerisciAuto(PreferenzeCliente preferenzeCliente) {
        List<ElementoListino> autoFiltrate = new ArrayList<>(); // contiene le auto che rientrano nel budget e rispettano tutte le preferenze
        List<ElementoListino> autoCompatibili = new ArrayList<>(); // contiene le auto che rientrano nel budget ma non rispettano tutte le preferenze.
    
        double budgetCliente = preferenzeCliente.getRedditoAnnuale() * 0.35;
        String preferenzaMarca = preferenzeCliente.getPreferenzeAuto();
        int preferenzaNumeroPorte = preferenzeCliente.getPreferenzeNumeroPorte();
        TipoAlimentazione preferenzaAlimentazione = preferenzeCliente.getPreferenzeAlimentazione();
    
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
    
        // Se nessuna auto rispetta tutte le preferenze, considera tutte le compatibili
        if (autoFiltrate.isEmpty() && !autoCompatibili.isEmpty()) {
            autoCompatibili.sort((auto1, auto2) -> calcolaRanking(auto1, auto2, preferenzeCliente));
            autoFiltrate.addAll(autoCompatibili);
        } else {
            // Ordina le auto filtrate in base al ranking
            autoFiltrate.sort((auto1, auto2) -> calcolaRanking(auto1, auto2, preferenzeCliente));
        }
    
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
    private int calcolaRanking(ElementoListino auto1, ElementoListino auto2, PreferenzeCliente preferenzeCliente) {
        int punteggio1 = 0;
        int punteggio2 = 0;

        String preferenzaMarca = preferenzeCliente.getPreferenzeAuto();
        int preferenzaNumeroPorte = preferenzeCliente.getPreferenzeNumeroPorte();
        TipoAlimentazione preferenzaAlimentazione = preferenzeCliente.getPreferenzeAlimentazione();

        // Preferenza di marca con un peso maggiore
        if (auto1.getAutomobile().getMarca().equalsIgnoreCase(preferenzaMarca)) {
            punteggio1 += 10;
        }
        if (auto2.getAutomobile().getMarca().equalsIgnoreCase(preferenzaMarca)) {
            punteggio2 += 10;
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
            punteggio1 += 5;
        }
        if (auto2.getAutomobile().getTipoAlimentazione().equals(preferenzaAlimentazione)) {
            punteggio2 += 5;
        }

        // Differenza prezzo rispetto al budget con peso ridotto
        double budgetCliente = preferenzeCliente.getRedditoAnnuale() * 0.35;
        double differenzaPrezzo1 = Math.abs(auto1.getPrezzo() - budgetCliente);
        double differenzaPrezzo2 = Math.abs(auto2.getPrezzo() - budgetCliente);

        // Peso minore per la vicinanza al budget
        if (differenzaPrezzo1 < differenzaPrezzo2) {
            punteggio1 += 4;
        } else if (differenzaPrezzo2 < differenzaPrezzo1) {
            punteggio2 += 4;
        }

        //in caso di punteggio uguale, preferisce l'auto con il prezzo più basso
        if (punteggio1 == punteggio2) {
            return Double.compare(auto1.getPrezzo(), auto2.getPrezzo());
        }

        // Ordinamento decrescente per punteggio
        return Integer.compare(punteggio2, punteggio1);
    }

}
