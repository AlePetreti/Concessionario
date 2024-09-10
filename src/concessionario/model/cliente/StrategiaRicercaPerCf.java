package concessionario.model.cliente;

import java.util.Collections;
import java.util.List;

public class StrategiaRicercaPerCf implements StrategiaDiRicerca{

     public List<Cliente> cerca(List<Cliente> clienti, String parolachiave) {
        for (Cliente cliente : clienti) {
            if (cliente.getCf().equalsIgnoreCase(parolachiave)) {
                return Collections.singletonList(cliente);
            }
        }
        return Collections.emptyList();
    }
}
