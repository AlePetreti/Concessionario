package concessionario.view;

import concessionario.controller.AutonoleggioController;
import concessionario.model.autonoleggio.auto_noleggio.AutomobileNoleggio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AutonoleggioView extends JFrame {
    private AutonoleggioController controller;

    private JList<AutomobileNoleggio> listaAutoNoleggio;
    private JTextArea dettagliAuto;
    private JButton bottoneNoleggia;

    public AutonoleggioView(AutonoleggioController controller) {
        this.controller = controller;

        // Inizializza e configura la GUI
        initializeUI();

        // Popola la lista delle auto
        aggiornaListaAuto();
    }

    private void initializeUI() {
        setTitle("Autonoleggio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Dimension preferredSize = new Dimension(1440, 720);
        setPreferredSize(preferredSize);

        listaAutoNoleggio = new JList<>();
        dettagliAuto = new JTextArea();
        bottoneNoleggia = new JButton("Noleggia");

        listaAutoNoleggio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaAutoNoleggio);

        add(scrollPane, BorderLayout.WEST);
        add(dettagliAuto, BorderLayout.CENTER);
        add(bottoneNoleggia, BorderLayout.SOUTH);

        bottoneNoleggia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noleggiaAuto();
            }
        });

        // Aggiungi listener alla selezione dell'auto nella lista
        listaAutoNoleggio.addListSelectionListener(e -> aggiornaDettagliAuto());
        pack();
        setLocationRelativeTo(null);
    }

    private void aggiornaListaAuto() {
        List<AutomobileNoleggio> automobili = controller.getAutomobili();
        listaAutoNoleggio.setListData(automobili.toArray(new AutomobileNoleggio[0]));
    }

    private void aggiornaDettagliAuto() {
        AutomobileNoleggio selectedAuto = listaAutoNoleggio.getSelectedValue();
        if (selectedAuto != null) {
            dettagliAuto.setText(selectedAuto.toString() + "\nStato: " + selectedAuto.getStato());
        }
    }

    private void noleggiaAuto() {
        AutomobileNoleggio selectedAuto = listaAutoNoleggio.getSelectedValue();
        if (selectedAuto != null) {
            int giorni = getNumeroGiorni();
            controller.noleggiaAuto(selectedAuto, giorni);
            aggiornaListaAuto();
            aggiornaDettagliAuto();
            double costoNoleggio = selectedAuto.getCostoNoleggio();
            JOptionPane.showMessageDialog(this, "Auto noleggiata con successo!\nCosto totale: " + costoNoleggio + " euro");
        } else {
            JOptionPane.showMessageDialog(this, "Seleziona un'auto prima di noleggiare.");
        }
    }

    private int getNumeroGiorni() {
        String input = JOptionPane.showInputDialog("Inserisci il numero di giorni di noleggio:");
        return Integer.parseInt(input);
    }
}