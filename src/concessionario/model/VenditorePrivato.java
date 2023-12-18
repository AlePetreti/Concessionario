package concessionario.model;

import concessionario.model.automobile.Automobile;
import concessionario.model.cliente.Cliente;

public class VenditorePrivato {

    private Cliente cliente;
    private Automobile auto;
    private int prezzoVendita;

    public VenditorePrivato(Cliente cliente, Automobile auto, int prezzoVendita) {
        this.cliente = cliente;
        this.auto = auto;
        this.prezzoVendita = prezzoVendita;
    }

    public Automobile getAutomobile() {
        return this.auto;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public int getprezzoVendita() {
        return this.prezzoVendita;
    }
}
