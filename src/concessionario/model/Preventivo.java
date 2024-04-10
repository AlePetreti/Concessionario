package concessionario.model;

import concessionario.model.automobile.Automobile;
import concessionario.model.cliente.Cliente;

public class Preventivo {
    
    private Automobile auto;
    private Cliente cliente;
    private double prezzoTotale;

    public Preventivo(Automobile auto, Cliente cliente, double prezzoTotale) {
        this.auto = auto;
        this.cliente = cliente;
        this.prezzoTotale = prezzoTotale;
    }

    public Automobile getAuto() {
        return auto;
    }

    public void setAuto(Automobile auto) {
        this.auto = auto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    @Override
    public String toString() {
        return "Preventivo [auto=" + auto + ", cliente=" + cliente + ", prezzoTotale=" + prezzoTotale + "]";
    }

    // qua si possono aggiungere vari tipi di logiche per variare il prezzo del preventivo per esempio: sconti, optional etc...
}
