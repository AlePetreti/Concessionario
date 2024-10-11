package concessionario.model.repartoVendita;

import java.util.LinkedList;
import java.util.List;


import concessionario.model.cliente.Cliente;

public class RegistroVendite {

    private final List<Preventivo> preventiviCompletati;

    public RegistroVendite() {
        this.preventiviCompletati = new LinkedList<>();
    }

    // Aggiungi preventivo al registro vendite, specificando il cliente
    public void addPreventivo(Preventivo preventivo, Cliente cliente) {
        preventiviCompletati.add(preventivo);
        
    }

    public List<Preventivo> getListaPreventivi() {
        return new LinkedList<>(preventiviCompletati);
    }
      
}
