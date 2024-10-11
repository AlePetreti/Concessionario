package concessionario.view.anagrafica;

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

    String getRedditoAnnualeInserito();

    String getPreferenzeAutoInserita();

    String getNumeroPorteInserito();

    String getAlimentazioneInserita();

    void addObserver(AnagraficaClientiViewObserver observer);

    void removeObserver(AnagraficaClientiViewObserver observer);
}
