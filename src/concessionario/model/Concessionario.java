package concessionario.model;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.Cliente;

public class Concessionario {

    private final Listino listino;
    private final AnagraficaClienti listaClienti;
    private final RegistroVendite registroVendite;
    
    public Concessionario(Listino listino, AnagraficaClienti listaClienti, RegistroVendite registroVendite) {
        this.listino = listino;
        this.listaClienti = listaClienti;
        this.registroVendite = registroVendite;
    }
    
    public Preventivo generaPreventivo(Automobile auto, Cliente cliente) {
        if(listaClienti.isPresent(cliente)) {
            double prezzoAuto = listino.getPrezzoAuto(auto);
            return new Preventivo(auto, prezzoAuto, cliente);
        }
        return null;
    }

    public boolean vendiAuto(Preventivo preventivo) {
        if(preventivo.getAuto().geStatoMacchina().equals(StatoMacchina.USATO)) {
            // rimuovi auto usata dalla lista di auto usate
        }
        registroVendite.addPreventivo(preventivo);
        return true;
    }
}
