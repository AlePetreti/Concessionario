package concessionario;

import concessionario.automobile.Automobile;
import concessionario.cliente.Cliente;

public class VenditorePrivato {

    private Cliente cliente;
    private Automobile auto;

    public VenditorePrivato(Cliente cliente, Automobile auto) {
        this.cliente = cliente;
        this.auto = auto;
    }

    public Automobile getAutomobile() {
        return this.auto;
    }

    public Cliente getCliente() {
        return this.cliente;
    }
}
