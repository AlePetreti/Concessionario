package concessionario.model.repartoVendita;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.Cliente;
import concessionario.model.listino.Listino;

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
    
    public Preventivo generaPreventivo(Automobile auto, Cliente cliente) {
        if(listaClienti.ePresente(cliente)) {
            double prezzoAuto;
            if (auto.getStatoMacchina().equals(StatoMacchina.USATO)) {
                prezzoAuto = listinoUsato.getPrezzoAuto(auto);
            } else {
                prezzoAuto = listino.getPrezzoAuto(auto);
            }
            return new Preventivo(auto, prezzoAuto, cliente);
        }
        return null;
    }
    
    public boolean vendiAuto(Preventivo preventivo) {
        Automobile auto = preventivo.getAuto();
        
        if(auto.getStatoMacchina().equals(StatoMacchina.USATO)) {
            listinoUsato.rimuoviAuto(auto);
        }
        registroVendite.addPreventivo(preventivo);
        return true;
    }
}
