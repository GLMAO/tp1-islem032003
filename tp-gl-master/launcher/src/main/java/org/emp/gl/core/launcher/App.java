package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.HorlogeGraphique;
import org.emp.gl.clients.CompteAReboursGraphique;
import org.emp.gl.clients.DashboardPrincipal;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;

public class App {
    public static void main(String[] args) throws InterruptedException {
        // Démarrer l'interface graphique dans l'EDT Swing
        SwingUtilities.invokeLater(() -> {
            try {
                // CORRECTION : utiliser getSystemLookAndFeelClassName() au lieu de getSystemLookAndFeel()
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            DummyTimeServiceImpl timer = new DummyTimeServiceImpl();

            // Créer le dashboard principal
            new DashboardPrincipal(timer);

            // Créer quelques composants graphiques par défaut
            new HorlogeGraphique("Horloge Principale", timer);
            new CompteAReboursGraphique("Compte Démo", timer, 15);

            // Garder les composants console existants
            new Horloge("Console 1", timer);
            new Horloge("Console 2", timer);
            new CompteARebours("Console C1", timer, 5);

            for (int i = 1; i <= 3; i++) {
                int val = 10 + (int)(Math.random() * 10);
                new CompteARebours("Console C" + (i + 1), timer, val);
            }
        });

        // Laisser le programme tourner plus longtemps pour voir les interfaces graphiques
        Thread.sleep(30000);
        System.out.println("Programme terminé ✅");
    }
}