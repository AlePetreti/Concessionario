package concessionario.model.cliente;

import java.util.List;

public class StrategiaRicercaPerCf implements StrategiaDiRicerca{

    @Override
    public Cliente cerca(List<Cliente> clienti, String codiceFiscale) {
        for (Cliente cliente : clienti) {
            if (cliente.getCf().equalsIgnoreCase(codiceFiscale)) {
                return cliente;
            }
        }
        return null;
    }
}
