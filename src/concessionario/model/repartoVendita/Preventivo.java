package concessionario.model.repartoVendita;

import concessionario.model.automobile.Automobile;
import concessionario.model.cliente.Cliente;

public class Preventivo {
    
    private Automobile auto;
    private Cliente cliente;
    private double prezzoTotale;

    public Preventivo(Automobile auto, double prezzoAuto, Cliente cliente) {
        this.auto = auto;
        this.cliente = cliente;
        this.prezzoTotale = prezzoAuto;
    }

    public Automobile getAuto() {
        return auto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    @Override
    public String toString() {
        return "Preventivo [auto=" + auto + ", cliente=" + cliente + ", prezzoTotale=" + prezzoTotale + "]";
    }

    // qua si possono aggiungere vari tipi di logiche per variare il prezzo del preventivo per esempio: sconti, optional etc...
}
