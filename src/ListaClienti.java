import java.util.LinkedList;
import java.util.List;

public class ListaClienti {


    private LinkedList<Cliente> listaClienti;

    public ListaClienti() {
        listaClienti = new LinkedList<Cliente>();

    }
    // aggiungi cliente alla lista 
    public boolean registraCliente(Cliente cliente) {
        listaClienti.add(cliente);
        return true;
    }
    // elimina cliente dalla listas
    public boolean eliminaCliente(Cliente cliente) {
        listaClienti.remove(cliente);
        return true;
    }

    public List<Cliente> getlistaClienti() {
        return new LinkedList<Cliente>(listaClienti);
    }
}
