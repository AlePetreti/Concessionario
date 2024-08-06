package concessionario.model.repartoVendita;

import concessionario.model.Listino;
import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.Cliente;

public class ServizioVendite {
    private final Listino listino;
    private final AnagraficaClienti listaClienti;
    private final RegistroVendite registroVendite;

    public ServizioVendite(Listino listino, AnagraficaClienti listaClienti, RegistroVendite registroVendite) {
        this.listino = listino;
        this.listaClienti = listaClienti;
        this.registroVendite = registroVendite;
    }
        
    /**
    * Genera un preventivo per una determinata automobile e cliente.
    *
    * @param auto l'automobile per cui generare il preventivo
    * @param cliente il cliente a cui il preventivo è destinato
    * @return il preventivo generato o null se il cliente non è presente
    */
    public Preventivo generaPreventivo(Automobile auto, Cliente cliente) {
        if(listaClienti.isPresent(cliente)) {
            double prezzoAuto = listino.getPrezzoAuto(auto);
            return new Preventivo(auto, prezzoAuto, cliente);
        }
        return null;
    }
    
    /**
    * Vende un'automobile basata su un preventivo.
    *
    * @param preventivo il preventivo su cui si basa la vendita
    * @return true se la vendita è stata registrata correttamente
    */
    public boolean vendiAuto(Preventivo preventivo) {
        if(preventivo.getAuto().getStatoMacchina().equals(StatoMacchina.USATO)) {
            // rimuovi auto usata dalla lista di auto usate
        }
        registroVendite.addPreventivo(preventivo);
        return true;
    }
}
