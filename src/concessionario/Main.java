package concessionario;
import java.util.List;

import concessionario.cliente.AnagraficaClienti;
import concessionario.cliente.Cliente;

public class Main {
    public static void main(String[] args) throws Exception {

        Cliente c1;
        Cliente c2;
        Cliente c3;
        AnagraficaClienti anagrafica;

        c1 = new Cliente("Alessandro", "Petreti", "petretiale@gmail.com", "3291390019", "PTRLSN02S25D488F");
        c2 = new Cliente("Andrea", "Petreti", "petretiandrea@gmail.com", "3567893245", "SONODOWN");
        c3 = new Cliente("Roberta", "Mentuccia", "mentucciaroberta@gmail.com", "3256764598", "HOSEMPRERAGIONE");

        anagrafica = new AnagraficaClienti();

        anagrafica.registraCliente(c1);
        anagrafica.registraCliente(c2);
        anagrafica.registraCliente(c3);

        Cliente clienteTrovato = anagrafica.cercaCliente("SONODOWN");
        System.out.println(clienteTrovato);

        List<Cliente> clienti = anagrafica.cercaClienti("Pe");
        System.out.println(clienti);
    }
}
