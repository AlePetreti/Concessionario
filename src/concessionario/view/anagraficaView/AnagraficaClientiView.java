package concessionario.view.anagraficaView;

import java.util.List;

import concessionario.model.cliente.Cliente;

public interface AnagraficaClientiView {
    
    void mostraAnagraficaClienti();

    void mostraListaClienti(List<Cliente> clienti);

    String getNomeInserito();

    String getCognomeInserito();

    String getEmailInserita();

    String getTelefonoInserito();

    String getCfInserito();

    String getParolaChiave();

    void addObserver(AnagraficaClientiViewObserver observer);

    void removeObserver(AnagraficaClientiViewObserver observer);
}
