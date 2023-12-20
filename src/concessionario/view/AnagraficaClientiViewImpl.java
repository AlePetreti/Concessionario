package concessionario.view;

import java.util.LinkedList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import concessionario.model.cliente.Cliente;

public class AnagraficaClientiViewImpl implements AnagraficaClientiView{

    private JFrame frameClienti;
    private JTextArea listaClienti;
    private JTextField nomeCliente;
    private JTextField cognomeCliente;
    private JTextField emailCliente;
    private JTextField telCliente;
    private JTextField cfCliente;
    private JTextField parolaChiave;

    private List<ConcessionarioViewObserver> osservatori;


    public AnagraficaClientiViewImpl() {
        this.osservatori = new LinkedList<>();
        frameClienti = new JFrame("Anagrafica Clienti");
        this.frameClienti.setSize(1280, 720);
        this.frameClienti.setLayout(new BorderLayout());

        // lista clienti
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Lista dei clienti"));
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        listaClienti = new JTextArea();
        listaClienti.setLineWrap(true);
        listaClienti.setEditable(false);
        listaClienti.setOpaque(false);
        panel.add(listaClienti);
        
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
        nomeCliente = new JTextField();
        panel1.add(nomeCliente, constraints);
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
        // bottone per la registrazione dei clienti 
        JButton bRegistraCliente = new JButton("Registra Cliente");
        panel1.add(bRegistraCliente, constraints);
        bRegistraCliente.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(TipoEvento.REGISTRA_CLIENTI);
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
                    notifyEvent(TipoEvento.CERCA_CLIENTI_CF);
                } else {
                    notifyEvent(TipoEvento.CERCA_CLIENTI);
                }
            }    
        });


        this.frameClienti.add(panel, BorderLayout.CENTER);
        this.frameClienti.add(panel1, BorderLayout.LINE_END);
        this.frameClienti.add(panel2, BorderLayout.PAGE_START);
    }

    private void notifyEvent(TipoEvento tipoEvento) {
        for (ConcessionarioViewObserver concessionarioViewObserver : osservatori) {
            concessionarioViewObserver.eventNotified(new Event(tipoEvento));
        }
    }

    @Override
    public void mostraAnagraficaClienti() {
        frameClienti.setVisible(true);
        notifyEvent(TipoEvento.ANAGRAFICA_CLIENTI_APERTA);
    }

    @Override
    public void mostraListaClienti(List<Cliente> clienti) {
        String datiCliente = new String();
        datiCliente = ("Cf\t\tNome\tCognome\tEmail\t\t\tTelefono\n");
        for (Cliente cliente : clienti) { 
            datiCliente = datiCliente + (cliente.getCf() + "\t" + cliente.getNome() + "\t" +  cliente.getCognome()+ "\t" + cliente.getEmail() + "\t\t" + cliente.getTelefono() + "\n");
        }
        listaClienti.setText(datiCliente);
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
    public String getParolaChiave() {
        return parolaChiave.getText();
    }

    @Override
    public void addObserver(ConcessionarioViewObserver observer) {
        osservatori.add(observer);
    }

    @Override
    public void removeObserver(ConcessionarioViewObserver observer) {
        osservatori.remove(observer);
    }
    
}
