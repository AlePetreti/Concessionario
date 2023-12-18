package concessionario.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConcessionarioViewImpl implements ConcessionarioView{

    private JFrame frame;
    private JFrame frameClienti;

    private List<ConcessionarioViewObserver> osservatori;

    public ConcessionarioViewImpl() {
        this.osservatori = new LinkedList<>();
        this.frame = new JFrame("CONCESSIONARIO");
        this.frame.setSize(400, 300);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton btn = addButton("Gestione Clienti", frame);
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(TipoEvento.GESTIONE_CLIENTI);
            }
        });
        addButton("Ricerca Auto", frame);
        addButton("Acquista da privato", frame);
        
        // secondo menu. Gestione Clienti
        GestioneClientiView();
    }

    public void GestioneClientiView() {
        frameClienti = new JFrame("Gestione Clienti");
        this.frameClienti.setSize(600,300);
        this.frameClienti.setLayout(new FlowLayout());

        JButton button = addButton("Registra cliente", frameClienti);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {    
                notifyEvent(TipoEvento.REGISTRA_CLIENTI);
            }  
        });
        addButton("Cerca cliente in base a CF", frameClienti);
        addButton("Cerca cliente in vase a parola chiave", frameClienti);


        JLabel nomeCliente = new JLabel("Nome cliente:");
        JTextField campoNome = new JTextField(20);
        frameClienti.add(nomeCliente);
        frameClienti.add(campoNome);

        JLabel cognomeCliente = new JLabel("Cognome cliente:");
        JTextField campoCognome = new JTextField(20);
        frameClienti.add(cognomeCliente);
        frameClienti.add(campoCognome);

        JLabel emailCliente = new JLabel("Email cliente:");
        JTextField campoEmail = new JTextField(20);
        frameClienti.add(emailCliente);
        frameClienti.add(campoEmail);

        JLabel telefonoCliente = new JLabel("Telefono cliente:");
        JTextField campoTel = new JTextField(20);
        frameClienti.add(telefonoCliente);
        frameClienti.add(campoTel);

        JLabel cfCliente = new JLabel("Codice Fiscale cliente:");
        JTextField campoCf = new JTextField(20);
        frameClienti.add(cfCliente);
        frameClienti.add(campoCf);

        
    }
    private static JButton addButton(String text, Container container) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
        return button;
    }
    /**
     * metodo per notificare un evento
     * @param tipoEvento
     */
    private void notifyEvent(TipoEvento tipoEvento) {
        for (ConcessionarioViewObserver concessionarioViewObserver : osservatori) {
            concessionarioViewObserver.eventNotified(new Event(tipoEvento));
        }
    }
    @Override
    public void show() {
        frame.setVisible(true);
    }

     @Override
    public void mostraGestioneClienti() {
        frameClienti.setVisible(true);
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
