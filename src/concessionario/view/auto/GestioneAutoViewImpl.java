package concessionario.view.auto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.listino.ElementoListino;

public class GestioneAutoViewImpl implements GestioneAutoView {

    private final JFrame frameAuto;
    private final JTable listinoAuto;
    private final DefaultTableModel modelloTabella;
    private final JTextField modelloAuto;
    private final JTextField marcaAuto;
    private final JTextField kmAuto;
    private final JTextField numeroPorte;
    private final JTextField cilindrata;
    private final JTextField prezzoMax;
    private List<GestioneAutoViewObserver> osservatori;
    private List<ElementoListino> listinoCorrente;

    public GestioneAutoViewImpl() {
        this.osservatori = new LinkedList<>();
        frameAuto = new JFrame("GestioneAuto");
        this.frameAuto.setSize(1280, 720);
        this.frameAuto.setLayout(new BorderLayout());

        // Creazione del modello della tabella
        String[] nomiColonne = {"Modello", "Marca", "Km", "Numero Porte", "Cilindrata", "Stato", "Prezzo"};
        modelloTabella = new DefaultTableModel(nomiColonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Rende tutte le celle non modificabili
            }
        };

        // Creazione della tabella
        listinoAuto = new JTable(modelloTabella);
        listinoAuto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Aggiunta della tabella al frame
        frameAuto.add(new JLabel("Listino Auto"), BorderLayout.NORTH);
        frameAuto.add(new JScrollPane(listinoAuto), BorderLayout.CENTER);

        // Aggiunta del listener per la selezione delle righe
        listinoAuto.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = listinoAuto.getSelectedRow();
                    if (selectedRow != -1) {
                        ElementoListino elementoSelezionato = listinoCorrente.get(selectedRow);
                        mostraSpecificheAuto(elementoSelezionato);
                    }
                }
            }
        });

        // filtri per cercare un auto
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        // vincoli per posizionare un elemento nella GridBag
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        panel2.setLayout(new GridBagLayout());
        constraints.gridy = 0;
        // campo per marca auto
        panel2.add(new JLabel("Marca Auto"), constraints);
        constraints.gridy = 1;
        panel2.add(marcaAuto = new JTextField(), constraints);
        constraints.gridy = 2;
        // campo per modello auto
        panel2.add(new JLabel("Modello Auto"), constraints);
        constraints.gridy = 3;
        panel2.add(modelloAuto = new JTextField(), constraints);
        constraints.gridy = 4;
        // campo per KM auto
        panel2.add(new JLabel("KM auto"), constraints);
        constraints.gridy = 5;
        panel2.add(kmAuto = new JTextField(), constraints);
        constraints.gridy = 6;
        // campo per numero porte auto
        panel2.add(new JLabel("Numero porte"), constraints);
        constraints.gridy = 7;
        panel2.add(numeroPorte = new JTextField(), constraints);
        constraints.gridy = 8;
        // campo per cilindrata auto
        panel2.add(new JLabel("Cilindrata Auto"), constraints);
        constraints.gridy = 9;
        panel2.add(cilindrata = new JTextField(), constraints);
        constraints.gridy = 10;
        // campo per prezzo max macchina
        panel2.add(new JLabel("Prezzo Max"), constraints);
        constraints.gridy = 11;
        panel2.add(prezzoMax = new JTextField(), constraints);
        constraints.gridy = 12;
        // bottone per impostare le auto ad usate
        JCheckBox checkBoxUsato = new JCheckBox("Usato");
        panel2.add(checkBoxUsato, constraints);
        constraints.gridy = 13;
        // bottone per ricerca delle auto
        JButton bRicerca = new JButton("Cerca");
        panel2.add(bRicerca, constraints);
        constraints.gridy = 14;
        bRicerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxUsato.isSelected()) {
                    notifyEvent(EventoGestioneAuto.CERCA_AUTO_USATE);
                } else {
                    notifyEvent(EventoGestioneAuto.CERCA_AUTO);
                }
            }
        });

        this.frameAuto.add(panel2, BorderLayout.LINE_START);
    }

    private void notifyEvent(EventoGestioneAuto tipoEvento) {
        for (GestioneAutoViewObserver gestioneAutoViewObserver : osservatori) {
            gestioneAutoViewObserver.eventNotified(tipoEvento);
        }
    }

    // dialog per mostrare le specifiche del auto
    private void mostraSpecificheAuto(ElementoListino elemento) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Specifiche Auto");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        // panel per mostrare specifiche auto
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(0, 1));
        panel1.add(new JLabel("Marca: " + elemento.getAutomobile().getMarca()));
        panel1.add(new JLabel("Modello: " + elemento.getAutomobile().getModello()));
        panel1.add(new JLabel("Numero porte: " + elemento.getAutomobile().getNumeroPorte()));
        panel1.add(new JLabel("Cilindrata: " + elemento.getAutomobile().getCilindrata()));
        panel1.add(new JLabel("Cavalli: " + elemento.getAutomobile().getCavalli()));
        if (elemento.getAutomobile().getStatoMacchina().equals(StatoMacchina.USATO)) {
            panel1.add(new JLabel("KM: " + elemento.getAutomobile().getKm()));
        }
        panel1.add(new JLabel("Stato Auto: " + elemento.getAutomobile().getStatoMacchina()));
        panel1.add(new JLabel("Prezzo: " + elemento.getPrezzo()));

        JPanel panelB = new JPanel();
        panelB.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton preButton = new JButton("Crea Preventivo");
        panelB.add(preButton);

        preButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(EventoGestioneAuto.MOSTRA_PREVENTIVO);
                dialog.dispose();
            }
        });

        dialog.add(panel1, BorderLayout.CENTER);
        dialog.add(panelB, BorderLayout.SOUTH);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    @Override
    public void mostraGestioneAuto() {
        frameAuto.setVisible(true);
        notifyEvent(EventoGestioneAuto.GESTIONE_AUTO_APERTA);
    }

    @Override
    public void addObserver(GestioneAutoViewObserver observer) {
        osservatori.add(observer);
    }

    @Override
    public void removeObserver(GestioneAutoViewObserver observer) {
        osservatori.remove(observer);
    }

    @Override
    public void mostraListino(List<ElementoListino> listino) {
        modelloTabella.setRowCount(0); // Pulisce la tabella
        listinoCorrente = listino; // Aggiorna la lista corrente

        for (ElementoListino elementoListino : listino) {
            Automobile auto = elementoListino.getAutomobile();
            Object[] row = {
                auto.getModello(),
                auto.getMarca(),
                auto.getKm(),
                auto.getNumeroPorte(),
                auto.getCilindrata(),
                auto.getStatoMacchina(),
                elementoListino.getPrezzo()
            };
            modelloTabella.addRow(row);
        }
    }

    @Override
    public ElementoListino getElementoListino() {
        int selectedRow = listinoAuto.getSelectedRow();
        if (selectedRow != -1) {
            return listinoCorrente.get(selectedRow);
        }
        return null;
    }

    @Override
    public String getModelloAuto() {
        return modelloAuto.getText();
    }

    @Override
    public String getMarcaAuto() {
        return marcaAuto.getText();
    }

    @Override
    public Optional<Integer> getKmAuto() {
        if (kmAuto.getText().isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(Integer.parseInt(kmAuto.getText()));
        }
    }

    @Override
    public Optional<Integer> getNumeroPorte() {
        if (numeroPorte.getText().isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(Integer.parseInt(numeroPorte.getText()));
        }
    }

    @Override
    public Optional<Integer> getCilindrata() {
        if (cilindrata.getText().isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(Integer.parseInt(cilindrata.getText()));
        }
    }

    @Override
    public Optional<Double> getPrezzoMax() {
        if (prezzoMax.getText().isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(Double.parseDouble(prezzoMax.getText()));
        }
    }
}
