package concessionario.model;
import java.util.List;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;

import java.util.LinkedList;


public class CercatoreAuto {

    
    // da aggiungere il prezzo 
    // TODO: aggiungere il prezzo
    public Listino cercaAuto(Filtro filtro, Listino listinoAuto) {
        Listino autoFiltrate = new Listino();
        for(ElementoListino e: listinoAuto.getListino() ) {
            Automobile auto = e.getAutomobile();
            if((filtro.getModello().isEmpty() || filtro.getModello().equalsIgnoreCase(auto.getModello()))
                && (filtro.getMarca().isEmpty() || filtro.getMarca().equalsIgnoreCase(auto.getMarca())) 
                && (filtro.getKm() == -1 || filtro.getKm() == auto.getKm())
                && (filtro.getNumeroPorte() == 0 || filtro.getNumeroPorte() == auto.getNumeroPorte())
                && (filtro.getCilindrata() == 0 || filtro.getCilindrata() == auto.getCilindrata())
                && (filtro.getStatoMacchina().equals(StatoMacchina.NUOVO) || filtro.getStatoMacchina().equals(auto.geStatoMacchina()))
                && (filtro.getPrezzoMax() == 0.0 || filtro.getPrezzoMax() >= e.getPrezzo())) {
                    autoFiltrate.aggiungiAuto(auto, e.getPrezzo());
           }
        }
        return autoFiltrate;
        
    }
}
