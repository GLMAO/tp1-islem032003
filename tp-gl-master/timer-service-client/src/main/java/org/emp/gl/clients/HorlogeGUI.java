package org.emp.gl.clients;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import javax.swing.*;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class HorlogeGUI extends JFrame implements TimerChangeListener {

    private final JLabel label = new JLabel();
    private final TimerService timerService;

    public HorlogeGUI(String titre, TimerService timerService) {
        super(titre);
        this.timerService = timerService;
        timerService.addTimeChangeListener(this);

        label.setFont(new Font("Consolas", Font.BOLD, 48));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                timerService.removeTimeChangeListener(HorlogeGUI.this);
                dispose();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            SwingUtilities.invokeLater(() -> label.setText(String.format(
                    "%02d : %02d : %02d",
                    timerService.getHeures(),
                    timerService.getMinutes(),
                    timerService.getSecondes()
            )));
        }
    }
}
