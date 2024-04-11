package concessionario.model;
import java.util.List;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;

import java.util.LinkedList;


public class CercatoreAuto {

    
    // da aggiungere il prezzo 
    public List<Automobile> cercaAuto(Filtro filtro, Listino listinoAuto) {
        List<Automobile> autoFiltrate = new LinkedList<Automobile>();
        for(ElementoListino e: listinoAuto.getListino() ) {
            Automobile auto = e.getAutomobile();
            if((filtro.getModello() == (null) || filtro.getModello().equalsIgnoreCase(auto.getModello()))
                && (filtro.getMarca() == (null) || filtro.getMarca().equalsIgnoreCase(auto.getMarca())) 
                && (filtro.getKm() == -1 || filtro.getKm() == auto.getKm())
                && (filtro.getNumeroPorte() == 0 || filtro.getNumeroPorte() == auto.getNumeroPorte())
                && (filtro.getCilindrata() == 0 || filtro.getCilindrata() == auto.getCilindrata())
                && (filtro.getStatoMacchina().equals(StatoMacchina.NUOVO) || filtro.getStatoMacchina().equals(auto.geStatoMacchina()))) {
                    autoFiltrate.add(auto);
           }
        }
        return autoFiltrate;
        
    }
}
