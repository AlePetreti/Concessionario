package concessionario.model.cliente;

import java.util.List;

public interface StrategiaDiRicerca {
    
    Cliente cerca(List<Cliente> clienti, String criterio);
}
