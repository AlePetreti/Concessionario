package concessionario.model.cliente;

import java.util.LinkedList;
import java.util.List;

public class StrategiaRicercaPerNomeOCognome implements StrategiaDiRicerca{

    @Override
    public List<Cliente> cerca(List<Cliente> clienti, String parolaChiave) {
        List<Cliente> clientiTrovati = new LinkedList<>();
        for (Cliente cliente : clienti) {
            if (cliente.getNome().toLowerCase().contains(parolaChiave.toLowerCase()) || 
                cliente.getCognome().toLowerCase().contains(parolaChiave.toLowerCase())) {
                clientiTrovati.add(cliente);
            }
        }
        return clientiTrovati;
    }  
}
