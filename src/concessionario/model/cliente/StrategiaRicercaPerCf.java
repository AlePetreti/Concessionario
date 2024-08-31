package concessionario.model.cliente;

import java.util.Collections;
import java.util.List;

public class StrategiaRicercaPerCf implements StrategiaDiRicerca{

     public List<Cliente> cerca(List<Cliente> clienti, String codiceFiscale) {
        for (Cliente cliente : clienti) {
            if (cliente.getCf().equalsIgnoreCase(codiceFiscale)) {
                return Collections.singletonList(cliente);
            }
        }
        return Collections.emptyList();
    }
}
