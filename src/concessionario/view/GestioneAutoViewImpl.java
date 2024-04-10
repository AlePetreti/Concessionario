package concessionario.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import concessionario.model.ElementoListino;
import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.Cliente;

public class GestioneAutoViewImpl implements GestioneAutoView{

    private final JFrame frameAuto;
    private final JFrame framePreventivo;
    //private final JTextArea listinoAuto;
    private final JList<ElementoListino> listinoAuto;
    private final DefaultListModel<ElementoListino> modelloLista;
    private final JTextField modelloAuto;
    private final JTextField marcaAuto;
    private final JTextField kmAuto;
    private final JTextField numeroPorte;
    private final JTextField cilindrata;
    private final JTextField prezzoMax;
    private final JTextArea specificheAuto;
    private final JComboBox<String> boxClienti;
    private List<ConcessionarioViewObserver> osservatori;
    

    public GestioneAutoViewImpl() {
        this.osservatori = new LinkedList<>();
        frameAuto = new JFrame("GestioneAuto");
        this.frameAuto.setSize(1280, 720);
        this.frameAuto.setLayout(new BorderLayout());

        // lista delle auto
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Listino Auto"));
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        modelloLista = new DefaultListModel<>();
        listinoAuto = new JList<>(modelloLista);
        listinoAuto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listinoAuto.setLayoutOrientation(JList.VERTICAL);
        panel.add(listinoAuto);
        listinoAuto.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) {
                    ElementoListino elementoSelezionato = listinoAuto.getSelectedValue();
                    if(elementoSelezionato != null) {
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
                if(checkBoxUsato.isSelected()) {
                    notifyEvent(TipoEvento.CERCA_AUTO_USATE);
                } else {
                    notifyEvent(TipoEvento.CERCA_AUTO);
                }
            }
        });

        this.frameAuto.add(panel, BorderLayout.CENTER);
        this.frameAuto.add(panel2, BorderLayout.LINE_START);

        // finestra per i preventivi
        framePreventivo = new JFrame("Preventivo");
        framePreventivo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePreventivo.setSize(1280,720);
        framePreventivo.setLayout(new BorderLayout());
        // panel per mostrare specifiche auto
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        JButton b1 = new JButton("Concludi Vendita");
        panel3.add(b1, BorderLayout.EAST);
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(TipoEvento.CONCLUDI_VENDITA);
                framePreventivo.dispose();
            }
            
        });
        panel3.add(new JLabel("Prezzo totale: "), BorderLayout.WEST);
        /*
         * TODO: finisci il frame del preventivo
         */
        // panel per specifiche auto del preventivo
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        panel4.add(new JLabel("Specifiche auto"));
        specificheAuto = new JTextArea();
        panel4.add(specificheAuto); 
        
        // panel per selezioanre i clienti
        JPanel panel5 = new JPanel();
        panel5.setLayout(new BorderLayout());
        boxClienti = new JComboBox<>();
        boxClienti.setPreferredSize(new Dimension(300, 30));
        panel5.add(boxClienti, BorderLayout.CENTER);


        this.framePreventivo.add(panel3, BorderLayout.PAGE_END);
        this.framePreventivo.add(panel4, BorderLayout.CENTER);
        this.framePreventivo.add(panel5, BorderLayout.EAST);
    }

    private void notifyEvent(TipoEvento tipoEvento) {
        for (ConcessionarioViewObserver concessionarioViewObserver : osservatori) {
            concessionarioViewObserver.eventNotified(new Event(tipoEvento));
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
        if(elemento.getAutomobile().geStatoMacchina().equals(StatoMacchina.USATO)) {
            panel1.add(new JLabel("KM: " + elemento.getAutomobile().getKm()));
        }
        panel1.add(new JLabel("Stato Auto: " + elemento.getAutomobile().geStatoMacchina()));
        panel1.add(new JLabel("Prezzo: " + elemento.getPrezzo()));

        JPanel panelB = new JPanel();
        panelB.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton preButton = new JButton("Crea Preventivo");
        panelB.add(preButton);

        preButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(TipoEvento.MOSTRA_PREVENTIVO);
                dialog.dispose();
            }
            
        });

        dialog.add(panel1, BorderLayout.CENTER);
        dialog.add(panelB, BorderLayout.SOUTH);
        dialog.setSize(400, 300);
        //dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
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
        modelloLista.clear();

        for (ElementoListino elementoListino : listino) {
            modelloLista.addElement(elementoListino);
        }
    }

    @Override
    public ElementoListino getElementoListino() {
        return listinoAuto.getSelectedValue();
    }

    /*
    private String formattaElementoListino(ElementoListino elemento) {
        String formatoStringa = new String();
        formatoStringa  = elemento.getAutomobile().getMarca() + " " + elemento.getAutomobile().getModello() + " " +  elemento.getPrezzo();
        return formatoStringa;
    }
    */

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
 
    @Override
    public void mostraCreaPreventivo() {
        framePreventivo.setVisible(true);
        
    }

    @Override
    public void mostraSpecificheAutoPreventivo(Automobile auto) {
        ElementoListino autoSelezionata  = listinoAuto.getSelectedValue();
        if(autoSelezionata != null) {

        }
    }

    @Override
    public void mostraListaClienti(List<Cliente> cliente) {
        for(Cliente c : cliente) {
            String nomeCognome = c.getNome() + " " + c.getCognome();
            boxClienti.addItem(nomeCognome);
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
}
