package concessionario.view.anagrafica;

import java.util.LinkedList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import concessionario.model.cliente.Cliente;

public class AnagraficaClientiViewImpl implements AnagraficaClientiView {

    private final JFrame frameClienti;
    private final JTable tabella;
    private final DefaultTableModel model;
    private final JTextField nomeCliente;
    private final JTextField cognomeCliente;
    private final JTextField emailCliente;
    private final JTextField telCliente;
    private final JTextField cfCliente;
    private final JTextField parolaChiave;
    private final JTextField redditoAnnualeCliente;
    private final JTextField numeroMembriNucleoFamiliare;

    private List<AnagraficaClientiViewObserver> osservatori;

    public AnagraficaClientiViewImpl() {
        this.osservatori = new LinkedList<>();
        frameClienti = new JFrame("Anagrafica Clienti");
        this.frameClienti.setSize(1280, 720);
        this.frameClienti.setLayout(new BorderLayout());

        // tabella clienti
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Lista dei clienti"));
        panel.add(Box.createRigidArea(new Dimension(0, 8)));

        // Aggiornamento delle colonne per includere reddito e membri della famiglia
        String[] columnNames = {"CF", "Nome", "Cognome", "Email", "Telefono", "Reddito Annuale", "Numero Membri Nucleo Familiare"};
        model = new DefaultTableModel(columnNames, 0);
        tabella = new JTable(model);
        
        // Aggiunge la tabella ad un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabella);
        panel.add(scrollPane);

        // registrazione clienti
        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createLineBorder(Color.black));
        // vincoli per posizionare un elemento nella GridBag
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        panel1.setLayout(new GridBagLayout());
        constraints.gridy = 0;

        // campo per nome cliente
        panel1.add(new JLabel("nome cliente:"), constraints);
        constraints.gridy = 1;
        panel1.add(nomeCliente = new JTextField(), constraints);
        constraints.gridy = 2;

        // campo per cognome cliente
        panel1.add(new JLabel("cognome cliente:"), constraints);
        constraints.gridy = 3;
        cognomeCliente = new JTextField();
        panel1.add(cognomeCliente, constraints);
        constraints.gridy = 4;

        // campo per email cliente
        panel1.add(new JLabel("email cliente:"), constraints);
        constraints.gridy = 5;
        emailCliente = new JTextField();
        panel1.add(emailCliente, constraints);
        constraints.gridy = 6;

        // campo per telefono cliente
        panel1.add(new JLabel("telefono cliente:"), constraints);
        constraints.gridy = 7;
        telCliente = new JTextField();
        panel1.add(telCliente, constraints);
        constraints.gridy = 8;

        // campo per cf cliente
        panel1.add(new JLabel("codice fiscale cliente:"), constraints);
        constraints.gridy = 9;
        cfCliente = new JTextField();
        panel1.add(cfCliente, constraints);
        constraints.gridy = 10;

        // campo per reddito annuale cliente
        constraints.gridy = 11;
        panel1.add(new JLabel("Reddito Annuale Cliente:"), constraints);
        constraints.gridy = 12;
        redditoAnnualeCliente = new JTextField();
        panel1.add(redditoAnnualeCliente, constraints);

        // campo per numero membri della famiglia
        constraints.gridy = 13;
        panel1.add(new JLabel("Numero membri famiglia:"), constraints);
        constraints.gridy = 14;
        numeroMembriNucleoFamiliare = new JTextField();
        panel1.add(numeroMembriNucleoFamiliare, constraints);

        // bottone per la registrazione dei clienti 
        constraints.gridy = 15;
        JButton bRegistraCliente = new JButton("Registra Cliente");
        panel1.add(bRegistraCliente, constraints);
        bRegistraCliente.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(isValidInput()) {
                    notifyEvent(EventoAnagrafica.REGISTRA_CLIENTI);
                }
            }
        });
        
        // ricerca cliente
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        // barra di ricerca
        parolaChiave = new JTextField(30);
        panel2.add(parolaChiave);
        // checkbox del Cf
        JCheckBox checkBoxCf = new JCheckBox("cf");
        panel2.add(checkBoxCf);

        // bottone per la ricerca
        JButton bCerca = new JButton("Cerca");
        panel2.add(bCerca);
        bCerca.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCf.isSelected()) { 
                    notifyEvent(EventoAnagrafica.CERCA_CLIENTI_CF);
                } else {
                    notifyEvent(EventoAnagrafica.CERCA_CLIENTI);
                }
            }    
        });

        this.frameClienti.add(panel, BorderLayout.CENTER);
        this.frameClienti.add(panel1, BorderLayout.LINE_END);
        this.frameClienti.add(panel2, BorderLayout.PAGE_START);
    }

    private void notifyEvent(EventoAnagrafica tipoEvento) {
        for (AnagraficaClientiViewObserver anagraficaClientiViewObserver : osservatori) {
            anagraficaClientiViewObserver.eventNotified(tipoEvento);
        }
    }

    private boolean isValidInput() {
        String nome = nomeCliente.getText().trim();
        String cognome = cognomeCliente.getText().trim();
        String email = emailCliente.getText().trim();
        String telefono = telCliente.getText().trim();
        String cf = cfCliente.getText().trim();
        String reddito = redditoAnnualeCliente.getText().trim();
        String numeroMembri = numeroMembriNucleoFamiliare.getText().trim();


        if (!nome.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(frameClienti, "Il nome deve contenere solo lettere.", "Errore di validazione", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!cognome.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(frameClienti, "Il cognome deve contenere solo lettere.", "Errore di validazione", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(frameClienti, "L'email non è valida.", "Errore di validazione", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!telefono.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(frameClienti, "Il telefono deve contenere solo numeri e deve essere lungo 10 cifre.", "Errore di validazione", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!cf.matches("[A-Za-z0-9]{16}")) {
            JOptionPane.showMessageDialog(frameClienti, "Il codice fiscale deve contenere 16 caratteri alfanumerici.", "Errore di validazione", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Double.parseDouble(reddito);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frameClienti, "Il reddito annuale deve essere un numero.", "Errore di validazione", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!numeroMembri.matches("\\d+")) {
            JOptionPane.showMessageDialog(frameClienti, "Il numero di membri della famiglia deve essere un numero intero positivo.", "Errore di validazione", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    @Override
    public void mostraAnagraficaClienti() {
        frameClienti.setVisible(true);
        notifyEvent(EventoAnagrafica.ANAGRAFICA_CLIENTI_APERTA);
    }

    @Override
    public void mostraListaClienti(List<Cliente> clienti) {
        // Pulisce la tabella
        model.setRowCount(0);
        
        // Popola la tabella con i dati dei clienti
        for (Cliente cliente : clienti) {
            Object[] rowData = {
                cliente.getCf(),
                cliente.getNome(),
                cliente.getCognome(),
                cliente.getEmail(),
                cliente.getTelefono(),
                cliente.getRedditoAnnuale(),
                cliente.getNumeroMembriNucleoFamiliare() 
            };
            model.addRow(rowData);
        }
    }
       
    @Override
    public String getNomeInserito() {
        return nomeCliente.getText();
    }

    @Override
    public String getCognomeInserito() {
        return cognomeCliente.getText();
    }

    @Override
    public String getEmailInserita() {
        return emailCliente.getText();
    }

    @Override
    public String getTelefonoInserito() {
        return telCliente.getText();
    }

    @Override
    public String getCfInserito() {
        return cfCliente.getText();
    }

    @Override
    public String getRedditoAnnualeInserito() {
        return redditoAnnualeCliente.getText();
    }
    
    @Override
    public String getNumberoMembriFamiliaInserito() {
        return numeroMembriNucleoFamiliare.getText();
    }

    @Override
    public String getParolaChiave() {
        return parolaChiave.getText();
    }

    @Override
    public void addObserver(AnagraficaClientiViewObserver observer) {
        osservatori.add(observer);
    }

    @Override
    public void removeObserver(AnagraficaClientiViewObserver observer) {
        osservatori.remove(observer);
    }
}
