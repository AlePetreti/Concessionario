package concessionario.model.repartoVendita;

import java.util.LinkedList;
import java.util.List;

public class RegistroVendite {

    private final List<Preventivo> preventiviCompletati;

    public RegistroVendite() {
        this.preventiviCompletati = new LinkedList<>();
    }

    public void addPreventivo(Preventivo preventivo) {
        preventiviCompletati.add(preventivo);
    }

    public List<Preventivo> getListaPreventivi() {
        return new LinkedList<Preventivo>(preventiviCompletati);
    }    
}
