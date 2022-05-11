package com.example.omatprojektit;

import java.io.Serializable;

/**
 * Luokka Pointsystem, jota käytetään pisteitä varten
 */
public class Pointsystem implements Serializable {
    /**
     * Kokonaisluku arvo: pisteet
     */
    int points = 0;

    /**
     * Luokan Pointsystem konstruktori
     * @param points
     */
    public Pointsystem(int points) {
        this.points = points;
    }

    /**
     * Luokan Poinstystem getteri
     * @return
     */
    public int getPoints() {
        return points;
    }

    /**
     * Luokan Pointsystem setteri
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }
}
