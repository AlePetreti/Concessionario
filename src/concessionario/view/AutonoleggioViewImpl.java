package concessionario.view;

import javax.swing.*;


import concessionario.model.autonoleggio.Autonoleggio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutonoleggioViewImpl {

    private Autonoleggio model;

    public AutonoleggioViewImpl() {
        JFrame frame = new JFrame("Autonoleggio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JButton noleggioButton = new JButton("Noleggio Auto");
        noleggioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.scegliOperazione();
            }
        });

        JButton leasingButton = new JButton("Leasing Auto");
        leasingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.scegliOperazione();
            }
        });

        panel.add(noleggioButton);
        panel.add(leasingButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public void AutonoleggioViewImpl() {
    }
}

