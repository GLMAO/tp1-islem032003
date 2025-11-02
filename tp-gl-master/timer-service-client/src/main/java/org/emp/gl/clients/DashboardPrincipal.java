package org.emp.gl.clients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.emp.gl.timer.service.TimerService;

public class DashboardPrincipal extends JFrame {

    private TimerService timerService;

    public DashboardPrincipal(TimerService timerService) {
        this.timerService = timerService;

        // Configuration de la fenêtre principale
        setTitle("Dashboard - Observateur Temps Réel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Panel de contrôle
        JPanel panelControle = new JPanel();
        JButton btnNouvelleHorloge = new JButton("➕ Nouvelle Horloge");
        JButton btnNouveauCompte = new JButton("⏱ Nouveau Compte à Rebours");

        btnNouvelleHorloge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = JOptionPane.showInputDialog("Nom de l'horloge:");
                if (nom != null && !nom.trim().isEmpty()) {
                    new HorlogeGraphique(nom, timerService);
                }
            }
        });

        btnNouveauCompte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = JOptionPane.showInputDialog("Nom du compte:");
                if (nom != null && !nom.trim().isEmpty()) {
                    try {
                        String valeurStr = JOptionPane.showInputDialog("Valeur initiale:");
                        int valeur = Integer.parseInt(valeurStr);
                        new CompteAReboursGraphique(nom, timerService, valeur);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Valeur invalide !");
                    }
                }
            }
        });

        panelControle.add(btnNouvelleHorloge);
        panelControle.add(btnNouveauCompte);
        add(panelControle, BorderLayout.NORTH);

        // Informations
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(new Color(240, 240, 240));
        textArea.setText(
                "🎯 SYSTÈME D'OBSERVATEUR TEMPS RÉEL\n\n" +
                        "✅ Fonctionnalités :\n" +
                        "• Horloges graphiques affichant l'heure en temps réel\n" +
                        "• Comptes à rebours avec barre de progression\n" +
                        "• Changement de couleur selon l'état\n" +
                        "• Notifications de fin de compte à rebours\n" +
                        "• Interface intuitive de création dynamique\n\n" +
                        "🚀 Utilisation :\n" +
                        "1. Cliquez sur 'Nouvelle Horloge' pour créer une horloge\n" +
                        "2. Cliquez sur 'Nouveau Compte' pour un compte à rebours\n" +
                        "3. Observez le pattern Observer en action !"
        );
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Status
        JLabel labelStatus = new JLabel("✅ Dashboard initialisé - Prêt à utiliser", SwingConstants.CENTER);
        labelStatus.setFont(new Font("Arial", Font.BOLD, 12));
        labelStatus.setForeground(Color.DARK_GRAY);
        add(labelStatus, BorderLayout.SOUTH);

        setVisible(true);

        System.out.println("Dashboard Principal initialisé !");
    }
}