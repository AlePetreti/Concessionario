package concessionario.view.auto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.automobile.TipoAlimentazione;
import concessionario.model.cliente.Cliente;
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
    private final JComboBox<String> boxClienti;
    private final JComboBox<TipoAlimentazione> boxTipoAlimentazione;
    private List<GestioneAutoViewObserver> osservatori;
    private List<ElementoListino> listinoCorrente;
    private final JTextField marcaAutoSugg;

    public GestioneAutoViewImpl() {
        this.osservatori = new LinkedList<>();
        frameAuto = new JFrame("GestioneAuto");
        this.frameAuto.setSize(1280, 720);
        this.frameAuto.setLayout(new BorderLayout());

        // Creazione del modello della tabella
        String[] nomiColonne = {"Modello", "Marca", "Km", "Numero Porte", "Cilindrata", "Stato", "Alimentazione", "Prezzo"};
        modelloTabella = new DefaultTableModel(nomiColonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
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

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        
        // Aggiungi boxClienti
        gbc.gridy = 0;
        boxClienti = new JComboBox<>();
        boxClienti.setPreferredSize(new Dimension(300, 30));
        panel.add(boxClienti, gbc);
        
        // Aggiungi boxTipoAlimentazione
        gbc.gridy = 1;
        boxTipoAlimentazione = new JComboBox<>();
        boxTipoAlimentazione.setPreferredSize(new Dimension(300, 30));
        panel.add(boxTipoAlimentazione, gbc);
        
        gbc.gridy = 2;
        panel.add(new JLabel("Marca Auto"), gbc);
        gbc.gridy = 3;
        marcaAutoSugg = new JTextField();
        marcaAutoSugg.setPreferredSize(new Dimension(300, 30));
        panel.add(marcaAutoSugg, gbc);
        
        
        // Aggiungi bottone per ricerca suggerimenti
        gbc.gridy = 4;
        JButton bRicercaSugg = new JButton("Ricerca tramite suggerimenti");
        panel.add(bRicercaSugg, gbc);

        bRicercaSugg.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String testoInserito = marcaAutoSugg.getText().trim();
        
                // Controlla se il testo contiene solo lettere
                if (!testoInserito.isEmpty() && !testoInserito.matches("[a-zA-Z\\s]+")) {
                    JOptionPane.showMessageDialog(frameAuto, "Inserire una marca valida", "Errore di validazione", JOptionPane.ERROR_MESSAGE);
                } else {
                    notifyEvent(EventoGestioneAuto.CERCA_AUTO_SUGG);
                }
            }
        });
        
        this.frameAuto.add(panel, BorderLayout.EAST);

        
        // Filtri per cercare un auto
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        // Vincoli per posizionare un elemento nella GridBag
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        panel2.setLayout(new GridBagLayout());
        constraints.gridy = 0;
        // Campo per marca auto
        panel2.add(new JLabel("Marca Auto"), constraints);
        constraints.gridy = 1;
        panel2.add(marcaAuto = new JTextField(), constraints);
        constraints.gridy = 2;
        // Campo per modello auto
        panel2.add(new JLabel("Modello Auto"), constraints);
        constraints.gridy = 3;
        panel2.add(modelloAuto = new JTextField(), constraints);
        constraints.gridy = 4;
        // Campo per KM auto
        panel2.add(new JLabel("KM auto"), constraints);
        constraints.gridy = 5;
        panel2.add(kmAuto = new JTextField(), constraints);
        constraints.gridy = 6;
        // Campo per numero porte auto
        panel2.add(new JLabel("Numero porte"), constraints);
        constraints.gridy = 7;
        panel2.add(numeroPorte = new JTextField(), constraints);
        constraints.gridy = 8;
        // Campo per cilindrata auto
        panel2.add(new JLabel("Cilindrata Auto"), constraints);
        constraints.gridy = 9;
        panel2.add(cilindrata = new JTextField(), constraints);
        constraints.gridy = 10;
        // Campo per prezzo max macchina
        panel2.add(new JLabel("Prezzo Max"), constraints);
        constraints.gridy = 11;
        panel2.add(prezzoMax = new JTextField(), constraints);
        constraints.gridy = 12;
        // Bottone per impostare le auto ad usate
        JCheckBox checkBoxUsato = new JCheckBox("Usato");
        panel2.add(checkBoxUsato, constraints);
        constraints.gridy = 13;
        // Bottone per ricerca delle auto
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

    // Dialog per mostrare le specifiche del auto
    private void mostraSpecificheAuto(ElementoListino elemento) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Specifiche Auto");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        // Panel per mostrare specifiche auto
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
        panel1.add(new JLabel("Targa: " + elemento.getAutomobile().getTarga()));
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
        modelloTabella.setRowCount(0);
        listinoCorrente = listino;

        for (ElementoListino elementoListino : listino) {
            Automobile auto = elementoListino.getAutomobile();
            Object[] row = {
                auto.getModello(),
                auto.getMarca(),
                auto.getKm(),
                auto.getNumeroPorte(),
                auto.getCilindrata(),
                auto.getStatoMacchina(),
                auto.getTipoAlimentazione(),
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

    @Override
    public void mostraListaClienti(List<Cliente> cliente) {
        boxClienti.removeAllItems();
        for (Cliente c : cliente) {
            boxClienti.addItem(c.getNome() + " " + c.getCognome()); 
        }
    }

    @Override
    public Cliente getClienteSelezionato(List<Cliente> listaClienti) {
        String nomeCognome = (String) boxClienti.getSelectedItem();
        for (Cliente cliente : listaClienti) {
            if ((cliente.getNome() + " " + cliente.getCognome()).equals(nomeCognome)) {
                return cliente;
            }
        }
        return null;
    }
    @Override
    public void mostraTipiAlimentazione() {
            boxTipoAlimentazione.removeAllItems();
        for (TipoAlimentazione tipo : TipoAlimentazione.values()) {
            boxTipoAlimentazione.addItem(tipo);
        }
    }
    @Override
    public TipoAlimentazione getTipoAlimentazioneSelezionato() {
        return (TipoAlimentazione) boxTipoAlimentazione.getSelectedItem();
    }
    
    @Override
    public String getMarcaAutoSugg() {
        return marcaAutoSugg.getText();
    }

}
