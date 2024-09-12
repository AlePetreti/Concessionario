package concessionario.model.cliente;

import java.util.List;

public interface StrategiaDiRicerca {
    
    List<Cliente> cerca(List<Cliente> clienti, String parolaChiave);
}
