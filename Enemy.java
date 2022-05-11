package com.example.omatprojektit;

import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Luokka Enemy, joka sisältää metodeja pelin vihollisille
 */
public class Enemy {

    /**
     * Kuva vihollisaluksesta
     */
    Image enemyShip = new Image("D:\\OmatProjektit\\src\\main\\images\\enemy.png");
    /**
     * Näkyvä kuva vihollisaluksesta
     */
    ImageView enemy = new ImageView(enemyShip);
    /**
     * Kuva räjähdyksestä
     */
    Image boom = new Image("D:\\OmatProjektit\\src\\main\\images\\explosion.png");
    /**
     * Näkyvä kuva räjähdyksestä
     */
    ImageView explosion = new ImageView(boom);

    /**
     * Metodi joka luo X:n ja Y:n arvot vihollisten syntymistä varten
     * @param randomX
     * @param randomY
     */
    public void enemySpawning(int randomX, int randomY) {
        enemy.setVisible(true);
        enemy.setScaleY(1.5);
        enemy.setScaleX(1.5);
        enemy.setX(randomX);
        enemy.setY(randomY);
    }

    /**
     * RotateTransition aluksen pyörimistä varten
     */
    RotateTransition spinning = new RotateTransition();

    /**
     * Metodi, joka pyörittää alusta sen ollessa ruudulla
     */
    public void spinAnimation(){
        spinning.setNode(enemy);
        spinning.setDuration(Duration.seconds(3));
        spinning.setCycleCount(Timeline.INDEFINITE);
        spinning.setByAngle(720);
        spinning.play();
    }

    /**
     * RotateTransition räjähdyskuvan pyörimistä varten
     */
    RotateTransition boomAnimation = new RotateTransition();

    /**
     * Metodi, joka näyttää räjähdysanimaation, kun vihollisalus tuhoutuu
     */
    public void setBoomAnimation(){
        explosion.setScaleX(2);
        explosion.setScaleY(2);
        explosion.setVisible(true);
        explosion.setX(enemy.getX());
        explosion.setY(enemy.getY());
        boomAnimation.setNode(explosion);
        boomAnimation.setCycleCount(1);
        boomAnimation.setDuration(Duration.seconds(0.5));
        boomAnimation.setByAngle(1080);
        boomAnimation.play();
        boomAnimation.setOnFinished(actionEvent -> {
            explosion.setVisible(false);
            boomAnimation.stop();
        });
    }
}

