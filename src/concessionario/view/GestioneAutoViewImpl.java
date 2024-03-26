package concessionario.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import concessionario.model.ElementoListino;

public class GestioneAutoViewImpl implements GestioneAutoView{

    private final JFrame frameAuto;
    private final JTextArea listinoAuto;
    private final JTextField modelloAuto;
    private final JTextField marcaAuto;
    private final JTextField kmAuto;
    private final JTextField numeroPorte;
    private final JTextField cilindrata;
    private final JTextField prezzoMax;

    private List<ConcessionarioViewObserver> osservatori;
    

    public GestioneAutoViewImpl() {
        this.osservatori = new LinkedList<>();
        frameAuto = new JFrame("GestioneAuto");
        this.frameAuto.setSize(1280, 720);
        this.frameAuto.setLayout(new BorderLayout());
        // bottone acquisto da privato 
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton bAcquistoPrivato = new JButton("Acquista auto da privato");
        panel.add(bAcquistoPrivato);

        // lista delle auto 
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(new JLabel("Listino Auto"));
        panel1.add(Box.createRigidArea(new Dimension(0, 8)));
        listinoAuto = new JTextArea();
        listinoAuto.setLineWrap(true);
        listinoAuto.setEditable(false);
        listinoAuto.setOpaque(false);
        panel1.add(listinoAuto);

        // filtri per cercare un auto
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        // vincoli per posizionare un elemento nella GridBag
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        panel2.setLayout(new GridBagLayout());
        constraints.gridy = 0;
        // campo per modello auto
        panel2.add(new JLabel("Modello Auto"), constraints);
        constraints.gridy = 1;
        panel2.add(modelloAuto = new JTextField(), constraints);
        constraints.gridy = 2;
        // campo per marca auto
        panel2.add(new JLabel("Marca Auto"), constraints);
        constraints.gridy = 3;
        panel2.add(marcaAuto = new JTextField(), constraints);
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
                if(checkBoxUsato.isSelected()) {
                    notifyEvent(TipoEvento.CERCA_AUTO_USATE);
                } else {
                    notifyEvent(TipoEvento.CERCA_AUTO);
                }
            }
        });

        this.frameAuto.add(panel, BorderLayout.PAGE_START);
        this.frameAuto.add(panel1, BorderLayout.CENTER);
        this.frameAuto.add(panel2, BorderLayout.LINE_START);
        
    }

    private void notifyEvent(TipoEvento tipoEvento) {
        for (ConcessionarioViewObserver concessionarioViewObserver : osservatori) {
            concessionarioViewObserver.eventNotified(new Event(tipoEvento));
        }
    }

    @Override
    public void mostraGestioneAuto() {
        frameAuto.setVisible(true);
        notifyEvent(TipoEvento.GESTIONE_AUTO_APERTA);
    }

    @Override
    public void addObserver(ConcessionarioViewObserver observer) {
        osservatori.add(observer);
    }

    @Override
    public void removeObserver(ConcessionarioViewObserver observer) {
        osservatori.remove(observer);
    }

    @Override
    public void mostraListino(List<ElementoListino> listino) {
        String listaAuto = new String();

        for (ElementoListino elementoListino : listino) {
            listaAuto = listaAuto + formattaElementoListino(elementoListino) + "\n";
        }
        listinoAuto.setText(listaAuto);
    }

    private String formattaElementoListino(ElementoListino elemento) {
        String formatoStringa = new String();
        formatoStringa  = elemento.getAutomobile().getMarca() + " " + elemento.getAutomobile().getModello() + " " +  elemento.getPrezzo();
        return formatoStringa;
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
        if(kmAuto.getText().isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(Integer.parseInt(kmAuto.getText()));
        }
    }

    @Override
    public Optional<Integer> getNumeroPorte() {
        if(numeroPorte.getText().isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(Integer.parseInt(numeroPorte.getText()));
        }
    }

    @Override
    public Optional<Integer> getCilindrata() {
        if(cilindrata.getText().isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(Integer.parseInt(cilindrata.getText()));
        }
    }

    @Override
    public Optional<Double> getPrezzoMax() {
        if(prezzoMax.getText().isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(Double.parseDouble(prezzoMax.getText()));
        }
    }

}
