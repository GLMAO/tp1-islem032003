package org.emp.gl.time.service.impl;

import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class DummyTimeServiceImpl implements TimerService {

    private final Timer timer = new Timer();
    private boolean running = true;

    private int dixiemeDeSeconde;
    private int secondes;
    private int minutes;
    private int heures;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public DummyTimeServiceImpl() {
        setTimeValues();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (running) {
                    timeChanged();
                } else {
                    cancel();
                }
            }
        }, 100, 100);
    }

    private void timeChanged() {
        setTimeValues();
    }
    public void stop() {
        running = false;
        timer.cancel();
        System.out.println("⏹ TimerService arrêté !");
    }

    private void setTimeValues() {
        LocalTime localTime = LocalTime.now();
        setDixiemeDeSeconde(localTime.getNano() / 100000000);
        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
    }

    // ---- PropertyChangeSupport ----
    @Override
    public void addTimeChangeListener(TimerChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    // ---- Setters avec notification ----
    public void setDixiemeDeSeconde(int newValue) {
        if (dixiemeDeSeconde != newValue) {
            int old = dixiemeDeSeconde;
            dixiemeDeSeconde = newValue;
            pcs.firePropertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP, old, newValue);
        }
    }

    public void setSecondes(int newValue) {
        if (secondes != newValue) {
            int old = secondes;
            secondes = newValue;
            pcs.firePropertyChange(TimerChangeListener.SECONDE_PROP, old, newValue);
        }
    }

    public void setMinutes(int newValue) {
        if (minutes != newValue) {
            int old = minutes;
            minutes = newValue;
            pcs.firePropertyChange(TimerChangeListener.MINUTE_PROP, old, newValue);
        }
    }

    public void setHeures(int newValue) {
        if (heures != newValue) {
            int old = heures;
            heures = newValue;
            pcs.firePropertyChange(TimerChangeListener.HEURE_PROP, old, newValue);
        }
    }

    // ---- Getters ----
    @Override public int getDixiemeDeSeconde() { return dixiemeDeSeconde; }
    @Override public int getSecondes() { return secondes; }
    @Override public int getMinutes() { return minutes; }
    @Override public int getHeures() { return heures; }
}
