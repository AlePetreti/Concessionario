package concessionario.view.officina;

import concessionario.model.automobile.Automobile;
import concessionario.view.concessionario.ConcessionarioViewObserver;
import concessionario.view.concessionario.EventoConcessionario;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OfficinaView extends JFrame {

    private JList<String> listaAuto;
    private DefaultListModel<String> listaAutoModel;
    private JLabel dettagliAuto;

    private List<ConcessionarioViewObserver> osservatori = new ArrayList<>(); // Lista di osservatori
    private List<Automobile> automobili; // Lista delle automobili per gestire i dati

    public OfficinaView() {
        // Configura la finestra principale
        setTitle("Officina Auto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Pannello superiore con la lista di automobili
        listaAutoModel = new DefaultListModel<>();
        listaAuto = new JList<>(listaAutoModel);
        listaAuto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permettiamo una singola selezione

        // Aggiungi un listener per la selezione della lista
        listaAuto.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = listaAuto.getSelectedIndex();
                    if (selectedIndex != -1) {
                        mostraDettagliAuto(automobili.get(selectedIndex));
                        notificaOsservatori(EventoConcessionario.RIPARA_AUTO);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(listaAuto);
        add(scrollPane, BorderLayout.CENTER);

        // Pannello inferiore con i dettagli auto
        JPanel pannelloDettagli = new JPanel();
        pannelloDettagli.setLayout(new BorderLayout());
        
        dettagliAuto = new JLabel("Dettagli auto:");
        pannelloDettagli.add(dettagliAuto, BorderLayout.CENTER);

        add(pannelloDettagli, BorderLayout.SOUTH);
    }

    // Metodo per aggiornare la lista delle auto disponibili (auto usate)
    public void mostraAutoDisponibili(List<Automobile> automobili) {
        this.automobili = automobili; // Salva la lista delle automobili
        listaAutoModel.clear();
        for (Automobile auto : automobili) {
            listaAutoModel.addElement(auto.getMarca() + " - " + auto.getModello());
        }
    }

    // Metodo per ottenere l'indice dell'auto selezionata
    public int getSelectedAutoIndex() {
        return listaAuto.getSelectedIndex();
    }

    // Metodo per aggiornare i dettagli dell'auto selezionata
    public void mostraDettagliAuto(Automobile auto) {
        if (auto != null) {
            dettagliAuto.setText("Dettagli auto: " + auto.getMarca() + " " + auto.getModello());
        } else {
            dettagliAuto.setText("Automobile non trovata.");
        }
    }

    // Metodo per rimuovere l'auto selezionata dalla lista
    public void rimuoviAutoSelezionata(int index) {
        listaAutoModel.remove(index);
        automobili.remove(index);
        dettagliAuto.setText("Dettagli auto:"); // Resetta i dettagli dopo la rimozione
    }

    // Registra un osservatore per gli eventi della vista
    public void registraOsservatore(ConcessionarioViewObserver osservatore) {
        osservatori.add(osservatore);
    }

    // Notifica tutti gli osservatori quando un evento si verifica
    private void notificaOsservatori(EventoConcessionario evento) {
        for (ConcessionarioViewObserver osservatore : osservatori) {
            osservatore.eventNotified(evento);
        }
    }

    public void mostraMessaggioRiparazioneAvviata(Automobile auto) {
        JOptionPane.showMessageDialog(this, 
            "Riparazione avviata per: " + auto.getMarca() + " " + auto.getModello(),
            "Riparazione Avviata",
            JOptionPane.INFORMATION_MESSAGE);
    }





}

