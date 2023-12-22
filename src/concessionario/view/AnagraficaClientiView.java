package concessionario.view;

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

    void addObserver(ConcessionarioViewObserver observer);

    void removeObserver(ConcessionarioViewObserver observer);
}
