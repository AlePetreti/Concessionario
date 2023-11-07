package concessionario;
import java.util.List;

import concessionario.cliente.AnagraficaClienti;
import concessionario.cliente.Cliente;
import concessionario.cliente.FactoryCliente;

public class Main {
    public static void main(String[] args) throws Exception {

        FactoryCliente clientefactory = new FactoryCliente();
        AnagraficaClienti anagrafica = new AnagraficaClienti();

        for(int i = 0; i < 2; i++) {
            anagrafica.registraCliente(clientefactory.creaClienteRandom());
        }

        Cliente clienteTrovato = anagrafica.cercaCliente("");
        System.out.println(clienteTrovato);

        List<Cliente> clienti = anagrafica.cercaClienti("Alessandro");
        System.out.println(clienti);

        clienti = anagrafica.getClienti();
        System.out.println(clienti);
    }
}
