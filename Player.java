package com.example.omatprojektit;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * Luokka Player, joka sisältää metodeja pelaajan liikkumista ja toimintoja varten
 */
public class Player{
    /**
     * Kuva aluksesta
     */
    Image ship1 = new Image("D:\\OmatProjektit\\src\\main\\images\\Ship1.png");
    /**
     * Näkyvä kuva pelaajasta
     */
    ImageView player = new ImageView(ship1);
    /**
     * Kuva laaserista
     */
    Image laser = new Image("D:\\OmatProjektit\\src\\main\\images\\lasers.png");
    /**
     * Näkyvä kuva ammuttavasta laaserista
     */
    ImageView projectile = new ImageView(laser);
    /**
     * Suora viiva laaserin lentorataa varten
     */
    Line line4laser = new Line();

    /**
     * Metodi jolla pelaaja voi liikuttaa alusta vasemmalle
     */
    public void moveLeft() {

        if (player.getRotate() != -90){
            player.setRotate(-90);
        }
        player.setX(player.getX()-20);
        if(player.getBoundsInParent().getMinX() < 0) {
            player.setX(player.getX()+20);
        }
    }

    /**
     * Metodi jolla pelaaja voi liikuttaa alusta oikealle
     */
    public void moveRight(){
        if (player.getRotate() != 90){
            player.setRotate(90);
        }
        player.setX(player.getX()+15);
        if(player.getBoundsInParent().getMaxX() > 1900){
            player.setX(player.getX()-15);
        }
    }

    /**
     * Metodi jolla pelaaja voi liikuttaa alusta ylös
     */
    public void moveUp(){
        if (player.getRotate() != 0) {
            player.setRotate(0);
        }
        player.setY(player.getY()-15);
        if(player.getBoundsInParent().getMinY() < 0){
            player.setY(player.getY()+15);
        }
    }
    /**
     * Metodi jolla pelaaja voi liikuttaa alusta alas
     */
    public void moveDown(){
        if (player.getRotate() != 180){
            player.setRotate(180);
        }
        player.setY(player.getY()+15);
        if(player.getBoundsInParent().getMaxY() > 1100){
            player.setY(player.getY()-15);
        }
    }

    /**
     * Metodi jolla pelaaja voi ampua
     */
    public void shooting() {
        /**
         * Ehto jolla pelaaja voi ampua oikealle
         */
        if (player.getRotate() == 90){
            projectile.setScaleY(20);
            projectile.setRotate(90);
            line4laser.setStartX(player.getX()+50);
            line4laser.setStartY(player.getY()+50);
            line4laser.setEndX(player.getX()+2000);
            line4laser.setEndY(player.getY()+50);
            projectile.setRotate(90);

        }
        /**
         * Ehto jolla pelaaja voi ampua ylöspäin
         */
        if (player.getRotate() == 0){
            projectile.setRotate(0);
            projectile.setScaleY(20);
            line4laser.setStartX(player.getX()+50);
            line4laser.setStartY(player.getY()-50);
            line4laser.setEndX(player.getX()+50);
            line4laser.setEndY(player.getY()-1500);
        }
        /**
         * Ehto jolla pelaaja voi ampua vasemmalle
         */
        if (player.getRotate() == -90){
            projectile.setRotate(-90);
            projectile.setScaleY(20);
            line4laser.setStartX(player.getX()-50);
            line4laser.setStartY(player.getY()+50);
            line4laser.setEndX(player.getX()-2000);
            line4laser.setEndY(player.getY()+50);
        }
        /**
         * Ehto jolla pelaaja voi ampua alas
         */
        if (player.getRotate() == 180){
            projectile.setRotate(180);
            projectile.setScaleY(20);
            line4laser.setStartX(player.getX()+50);
            line4laser.setStartY(player.getY()+150);
            line4laser.setEndX(player.getX()+50);
            line4laser.setEndY(player.getY()+1500);
        }

        /**
         * Animaatio ampumiseen
         */
        PathTransition pewpew = new PathTransition();
        line4laser.setStrokeWidth(0);
        line4laser.setStroke(Color.TRANSPARENT);
        pewpew.setNode(projectile);
        pewpew.setDuration(Duration.seconds(0.3));
        pewpew.setPath(line4laser);
        pewpew.setCycleCount(1);
        pewpew.play();
    }
}

