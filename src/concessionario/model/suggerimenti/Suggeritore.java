package concessionario.model.suggerimenti;
import java.util.List;

import concessionario.model.listino.ElementoListino;

public interface Suggeritore {
    List<ElementoListino> suggerisciAuto(PreferenzeCliente preferenzeCliente);
}
