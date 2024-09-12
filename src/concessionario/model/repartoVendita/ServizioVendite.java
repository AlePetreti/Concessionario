package concessionario.model.repartoVendita;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.Cliente;
import concessionario.model.listino.Listino;

import java.util.Optional;

public class ServizioVendite {
    private final Listino listino;
    private final Listino listinoUsato;
    private final AnagraficaClienti listaClienti;
    private final RegistroVendite registroVendite;

    public ServizioVendite(Listino listino, Listino listinoUsato, AnagraficaClienti listaClienti, RegistroVendite registroVendite) {
        this.listino = listino;
        this.listinoUsato = listinoUsato;
        this.listaClienti = listaClienti;
        this.registroVendite = registroVendite;
    }

    // Metodo per calcolare il prezzo in base allo stato della macchina
    public double calcolaPrezzoAuto(Automobile auto) {
        return auto.getStatoMacchina().equals(StatoMacchina.USATO)
                ? listinoUsato.getPrezzoAuto(auto)
                : listino.getPrezzoAuto(auto);
    }

    // Metodo per generare il preventivo solo se il cliente è presente
    public Optional<Preventivo> generaPreventivo(Automobile auto, Cliente cliente) {
        if (listaClienti.ePresente(cliente)) {
            double prezzoAuto = calcolaPrezzoAuto(auto);
            return Optional.of(new Preventivo(auto, prezzoAuto, cliente));
        }
        return Optional.empty();
    }

    public boolean vendiAuto(Preventivo preventivo) {
        Automobile auto = preventivo.getAuto();

        if (auto.getStatoMacchina().equals(StatoMacchina.USATO)) {
            if (!listinoUsato.rimuoviAuto(auto)) {
                throw new IllegalStateException("Errore nella rimozione dell'auto usata dal listino.");
            }
        }
        registroVendite.addPreventivo(preventivo);
        return true;
    }
}
