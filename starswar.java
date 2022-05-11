package com.example.omatprojektit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Luokka joka sisältää suurimman osan pelin toiminnallisuudesta
 */
public class starswar extends Application {
    /**
     * Start metodi, joka sisältää suurimman osan pelin toiminnallisuudesta
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        Player player1 = new Player();
        Enemy enemy1 = new Enemy();
        Random random = new Random();
        Pointsystem points = new Pointsystem(0);

        /**
         * erilaisia JPG ja PNG kuvia peliä varten
         */
        Image spaceBackground = new Image("D:\\OmatProjektit\\src\\main\\images\\Background1.jpg");
        ImageView background1 = new ImageView(spaceBackground);
        Image HUD4Ammo = new Image("D:\\OmatProjektit\\src\\main\\images\\AmmoHud.png");
        ImageView ammoHUD = new ImageView(HUD4Ammo);
        Image HUD4Time = new Image("D:\\OmatProjektit\\src\\main\\images\\TimeHUD.png");
        ImageView timeHUD = new ImageView(HUD4Time);
        Image HUD4Score = new Image("D:\\OmatProjektit\\src\\main\\images\\ScoreHud.png");
        ImageView scoreHUD = new ImageView(HUD4Score);

        /**
         * Ryhmiä eri ruutuja varten
         */
        Group menu = new Group();
        Group afterMenu = new Group();
        Group game = new Group();
        Group HUD = new Group();
        Group gameEnd = new Group();
        Group scoreGroup = new Group();
        Pane root = new Pane(background1,menu);

        /**
         * Monia erilaisia tekstejä peliä ja menuja varten
         */
        Text title = new Text("STARS WAR");
        title.setFont(Font.font("comic sans ms", FontWeight.BOLD,100) );
        title.setStroke(Color.YELLOW);
        title.setStrokeWidth(3);
        title.setX(670);
        title.setY(300);

        Text play = new Text("PLAY GAME");
        play.setFont(Font.font("comic sans ms", FontWeight.BOLD,50) );
        play.setStroke(Color.YELLOW);
        play.setStrokeWidth(2);
        play.setX(820);
        play.setY(450);

        Text exit = new Text("EXIT GAME");
        exit.setFont(Font.font("comic sans ms", FontWeight.BOLD,50) );
        exit.setStroke(Color.YELLOW);
        exit.setStrokeWidth(2);
        exit.setX(820);
        exit.setY(700);

        Text how2play = new Text("HOW TO PLAY");
        how2play.setFont(Font.font("comic sans ms", FontWeight.BOLD,100) );
        how2play.setStroke(Color.YELLOW);
        how2play.setStrokeWidth(3);
        how2play.setX(600);
        how2play.setY(200);

        Text how2play2 = new Text("Liikkuminen" + "\n"+
                " W: YLÖS"+ "\n"+
                " A: VASEN" + "\n"+
                " S: ALAS" + "\n" +
                " D: OIKEA"
                );

        how2play2.setFont(Font.font("comic sans ms", FontWeight.BOLD,50));
        how2play2.setX(400);
        how2play2.setY(300);
        how2play2.setStroke(Color.YELLOW);

        Text how2play3 = new Text("Ampuminen: VÄLILYÖNTI" + "\n"+
                "Lataus: R"
        );

        how2play3.setFont(Font.font("comic sans ms", FontWeight.BOLD,50));
        how2play3.setX(400);
        how2play3.setY(700);
        how2play3.setStroke(Color.YELLOW);

        Text how2play4 = new Text("Tuhoa mahdollisimman monta"+ "\n"+" alusta 60 sekunnissa!");
        how2play4.setFont(Font.font("comic sans ms", FontWeight.BOLD,50));
        how2play4.setStroke(Color.YELLOW);
        how2play4.setStrokeWidth(2);
        how2play4.setX(1000);
        how2play4.setY(400);
        Text ready = new Text("READY!");
        ready.setFont(Font.font("comic sans ms", FontWeight.BOLD,50) );
        ready.setStroke(Color.YELLOW);
        ready.setStrokeWidth(3);
        ready.setX(820);
        ready.setY(900);

        Text ammo = new Text("Ammo count: 1");
        ammo.setFont(Font.font("comic sans ms", FontWeight.BOLD,25) );
        ammo.setStroke(Color.LIGHTBLUE);
        ammo.setStrokeWidth(2);
        ammo.setFill(Color.BLACK);
        ammo.setX(155);
        ammo.setY(95);
        ammo.setScaleX(0.9);
        AtomicInteger ammocount = new AtomicInteger(1);

        ammoHUD.setScaleX(3.5);
        ammoHUD.setScaleY(3);
        ammoHUD.setX(125);
        ammoHUD.setY(35);

        Text reminder = new Text("Press R to Reload");
        reminder.setFont(Font.font("comic sans ms", FontWeight.BOLD,30) );
        reminder.setStroke(Color.YELLOW);
        reminder.setStrokeWidth(2);
        reminder.setFill(Color.BLACK);
        reminder.setX(50);
        reminder.setY(200);
        reminder.setVisible(false);

        Text score = new Text("Score: " + points.getPoints());
        score.setFont(Font.font("comic sans ms", FontWeight.NORMAL, 30));
        score.setStroke(Color.YELLOW);
        score.setX(880);
        score.setY(110);
        scoreHUD.setScaleY(3);
        scoreHUD.setScaleX(3);
        scoreHUD.setX(900);
        scoreHUD.setY(50);

        Text gameOver = new Text("Game Over");
        gameOver.setFont(Font.font("comic sans ms",FontWeight.BOLD, 100));
        gameOver.setStroke(Color.YELLOW);
        gameOver.setStrokeWidth(3);
        gameOver.setX(670);
        gameOver.setY(300);

        Text back2menu = new Text("Return to Menu");
        back2menu.setFont(Font.font("comic sans ms", FontWeight.NORMAL, 50));
        back2menu.setStroke(Color.YELLOW);
        back2menu.setStrokeWidth(2);
        back2menu.setX(750);
        back2menu.setY(900);

        Text recentScores = new Text("RECENT SCORES");
        recentScores.setFont(Font.font("comic sans ms", FontWeight.BOLD,50) );
        recentScores.setStroke(Color.YELLOW);
        recentScores.setStrokeWidth(2);
        recentScores.setX(750);
        recentScores.setY(575);

        Text recentScores2 = new Text("RECENT SCORES");
        recentScores2.setFont(Font.font("comic sans ms", FontWeight.BOLD,100) );
        recentScores2.setStroke(Color.YELLOW);
        recentScores2.setStrokeWidth(3);
        recentScores2.setX(550);
        recentScores2.setY(200);

        Text one = new Text("1. ");
        one.setFont(Font.font("comic sans ms", FontWeight.BOLD,50) );
        one.setStroke(Color.YELLOW);
        one.setStrokeWidth(2);
        one.setX(750);
        one.setY(300);

        Text onevalue = new Text("");
        onevalue.setFont(Font.font("comic sans ms", FontWeight.BOLD,50) );
        onevalue.setStroke(Color.YELLOW);
        onevalue.setStrokeWidth(2);
        onevalue.setX(800);
        onevalue.setY(300);

        Text two = new Text("2. ");
        two.setFont(Font.font("comic sans ms", FontWeight.BOLD,50) );
        two.setStroke(Color.YELLOW);
        two.setStrokeWidth(2);
        two.setX(750);
        two.setY(400);

        Text twovalue = new Text("");
        twovalue.setFont(Font.font("comic sans ms", FontWeight.BOLD,50) );
        twovalue.setStroke(Color.YELLOW);
        twovalue.setStrokeWidth(2);
        twovalue.setX(800);
        twovalue.setY(400);

        Text three = new Text("3. ");
        three.setFont(Font.font("comic sans ms", FontWeight.BOLD,50) );
        three.setStroke(Color.YELLOW);
        three.setStrokeWidth(2);
        three.setX(750);
        three.setY(500);

        Text threevalue = new Text("");
        threevalue.setFont(Font.font("comic sans ms", FontWeight.BOLD,50) );
        threevalue.setStroke(Color.YELLOW);
        threevalue.setStrokeWidth(2);
        threevalue.setX(800);
        threevalue.setY(500);

        /**
         * aikajana itse peliä varten
         */
        Timeline timeline = new Timeline();
        Text timer = new Text("0");
        timer.setFont(Font.font("comic sans ms", FontWeight.BOLD,30));
        timer.setStroke(Color.YELLOW);
        timer.setX(1665);
        timer.setY(110);

        final int[] time = {0};
        Text endScore = new Text();

        KeyFrame timerstuff = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            /**
             * KeyFrame luokan olio, joka sisältää pelin toiminnallisuuksia, kuten ajastimen ja mitä tapahtuu ajan loputtua
             * @param actionEvent
             */
            public void handle(ActionEvent actionEvent) {
                int minX = 100;
                int maxX = 1800;
                int minY = 100;
                int maxY = 900;

                time[0]++;
                timer.setText(""+ time[0]);
                int randomX = random.nextInt(minX,maxX);
                int randomY = random.nextInt(minY,maxY);
                if (game.getChildren().contains(enemy1.enemy) == false){
                    game.getChildren().add(enemy1.enemy);
                    enemy1.enemySpawning(randomX,randomY);
                    enemy1.spinAnimation();
                }
                if (time[0] > 59){
                    gameEnd.getChildren().remove(endScore);
                    root.getChildren().remove(game);
                    root.getChildren().add(gameEnd);
                    gameEnd.getChildren().add(back2menu);
                    timeline.stop();
                    endScore.setText("Final score: " + points.getPoints());
                    endScore.setFont(Font.font("comic sans ms", FontWeight.NORMAL,50));
                    endScore.setStrokeWidth(2);
                    endScore.setStroke(Color.YELLOW);
                    endScore.setX(750);
                    endScore.setY(400);
                    gameEnd.getChildren().add(endScore);

                    /**
                     * Tiedostoon kirjoittamista, tiedostosta lukemista ja arvojen asettamista tiedoston kautta.
                     */
                    String fileName = "D:\\OmatProjektit\\src\\main\\java\\com\\example\\omatprojektit\\points.dat";
                    try {
                        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
                        os.writeObject(points);
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
                        Pointsystem finalpoints = (Pointsystem) is.readObject();
                        if (onevalue.getText() == ""){
                            onevalue.setText(""+finalpoints.getPoints());
                        }
                        else if (twovalue.getText() == ""){
                            twovalue.setText(onevalue.getText());
                            onevalue.setText(""+finalpoints.getPoints());
                        }
                        else if (threevalue.getText() == ""){
                            threevalue.setText(twovalue.getText());
                            twovalue.setText(onevalue.getText());
                            onevalue.setText(""+finalpoints.getPoints());
                        }
                        else {
                            threevalue.setText(twovalue.getText());
                            twovalue.setText(onevalue.getText());
                            onevalue.setText(""+finalpoints.getPoints());
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        timeline.getKeyFrames().add(timerstuff);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeHUD.setX(1675);
        timeHUD.setY(50);
        timeHUD.setScaleX(3.5);
        timeHUD.setScaleY(3);


        /**
         * Tekstien ja nappien toiminnallisuudet
         */
        play.setOnMouseEntered(mouseEvent ->
        {
            play.setFont(Font.font("comic sans ms", FontWeight.BOLD,55) );
            play.setX(805);
        });
        play.setOnMouseExited(mouseEvent -> {
            play.setFont(Font.font("comic sans ms", FontWeight.BOLD,50) );
            play.setX(820);
        });
        play.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                root.getChildren().remove(menu);
                root.getChildren().add(afterMenu);
            }
        });

        exit.setOnMouseEntered(mouseEvent -> {
            exit.setFont(Font.font("comic sans ms", FontWeight.BOLD, 55));
            exit.setX(805);
        });

        exit.setOnMouseExited(mouseEvent -> {
            exit.setFont(Font.font("comic sans ms", FontWeight.BOLD,50) );
            exit.setX(820);
        });

        exit.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                System.exit(1);
            }
        });

        ready.setOnMouseEntered(mouseEvent -> {
            ready.setFont(Font.font("comic sans ms", FontWeight.BOLD, 55));
            ready.setX(810);
        });

        ready.setOnMouseExited(mouseEvent -> {
            ready.setFont(Font.font("comic sans ms", FontWeight.BOLD,50) );
            ready.setX(820);
        });

        ready.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                time[0]=0;
                timer.setText("0");
                score.setText("Score: 0");
                points.setPoints(0);
                root.getChildren().remove(afterMenu);
                root.getChildren().add(game);
                enemy1.enemy.setVisible(true);
                enemy1.explosion.setVisible(false);
                timeline.play();
                player1.player.setScaleX(2);
                player1.player.setScaleY(2);
                player1.player.setX(960);
                player1.player.setY(600);
                root.requestFocus();
            }
        });

        back2menu.setOnMouseEntered(mouseEvent -> {
            back2menu.setFont(Font.font("comic sans ms", FontWeight.NORMAL, 55));
            back2menu.setX(back2menu.getX()-5);
        });
        back2menu.setOnMouseExited(mouseEvent -> {
            back2menu.setFont(Font.font("comic sans ms", FontWeight.NORMAL, 50));
            back2menu.setX(back2menu.getX()+5);
        });
        back2menu.setOnMouseClicked(mouseEvent ->{
            if (mouseEvent.getButton() == MouseButton.PRIMARY){
                try {
                    root.getChildren().remove(gameEnd);
                }catch (Exception ex){}
                try {
                    root.getChildren().remove(scoreGroup);
                }catch (Exception ex){}
                root.getChildren().add(menu);
            }
        });

        recentScores.setOnMouseEntered(mouseEvent -> {
            recentScores.setFont(Font.font("comic sans ms", FontWeight.BOLD,55));
            recentScores.setX(recentScores.getX()-5);
        });
        recentScores.setOnMouseExited(mouseEvent -> {
            recentScores.setFont(Font.font("comic sans ms", FontWeight.BOLD,50));
            recentScores.setX(recentScores.getX()+5);
        });

        recentScores.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY){
                root.getChildren().remove(menu);
                root.getChildren().add(scoreGroup);
                scoreGroup.getChildren().add(back2menu);
            }
        });

        root.setOnKeyPressed(keyEvent -> {

            if (keyEvent.getCode() == KeyCode.D) {
                player1.moveRight();
            }
            if (keyEvent.getCode() == KeyCode.W) {
                player1.moveUp();
            }
            if (keyEvent.getCode() == KeyCode.A) {
                player1.moveLeft();
            }

            if (keyEvent.getCode() == KeyCode.S) {
                player1.moveDown();
            }

            if (keyEvent.getCode() == KeyCode.SPACE) {
                try {
                    game.getChildren().addAll(player1.line4laser,player1.projectile);
                    player1.shooting();
                    ammo.setText("Ammo count: 0");
                    reminder.setVisible(true);
                }catch (Exception e){}

                if (player1.player.getRotate() == 0){
                    if ((player1.player.getX() - enemy1.enemy.getX()) < 50 && (enemy1.enemy.getX() - player1.player.getX() < 50) &&player1.player.getY() > enemy1.enemy.getY() && ammocount.get()==1){
                        enemy1.setBoomAnimation();
                        game.getChildren().remove(enemy1.enemy);
                        ammocount.set(0);
                        points.setPoints(points.getPoints()+10);
                        score.setText("Score: " + points.getPoints());
                    }
                }
                else if (player1.player.getRotate() == 90){
                    if (player1.player.getX() < enemy1.enemy.getX() && (player1.player.getY()-enemy1.enemy.getY())< 50 &&(enemy1.enemy.getY()-player1.player.getY())< 50 && ammocount.get()==1){
                        enemy1.setBoomAnimation();
                        game.getChildren().remove(enemy1.enemy);
                        ammocount.set(0);
                        points.setPoints(points.getPoints()+10);
                        score.setText("Score: " + points.getPoints());
                    }
                }
                else if (player1.player.getRotate()==180){
                    if ((enemy1.enemy.getX() - player1.player.getX() < 50) && (player1.player.getX() - enemy1.enemy.getX() < 50) && enemy1.enemy.getY() > player1.player.getY()&& ammocount.get() == 1){
                        enemy1.setBoomAnimation();
                        game.getChildren().remove(enemy1.enemy);
                        ammocount.set(0);
                        points.setPoints(points.getPoints()+10);
                        score.setText("Score: " + points.getPoints());
                    }
                }

                else if (player1.player.getRotate() == -90){
                    if ((enemy1.enemy.getY() - player1.player.getY()) < 20 && (player1.player.getY()-enemy1.enemy.getY()) < 20 && enemy1.enemy.getX() < player1.player.getX() && ammocount.get() == 1){
                        enemy1.setBoomAnimation();
                        game.getChildren().remove(enemy1.enemy);
                        ammocount.set(0);
                        points.setPoints(points.getPoints()+10);
                        score.setText("Score: " + points.getPoints());
                    }
                }
                else{
                    ammocount.set(0);
                }
                }
            if (keyEvent.getCode()==KeyCode.R){
                try {
                    game.getChildren().removeAll(player1.line4laser,player1.projectile);
                    ammo.setText("Ammo count: 1");
                    ammocount.set(1);
                    reminder.setVisible(false);
                    enemy1.enemy.setX(enemy1.enemy.getX());
                    enemy1.enemy.setY(enemy1.enemy.getY());
                }
                catch (Exception e){}
            }
        });

        /**
         * Asioiden lisäämistä erilaisiin Grouppeihin
         */
        menu.getChildren().addAll(title,play,recentScores,exit);
        scoreGroup.getChildren().addAll(recentScores2,one,two,three,onevalue,twovalue,threevalue);
        afterMenu.getChildren().addAll(how2play,how2play2,how2play3,how2play4,ready);
        game.getChildren().addAll(player1.player,HUD,timer,score, enemy1.explosion);
        HUD.getChildren().addAll(ammoHUD,ammo,reminder,timeHUD,scoreHUD);
        gameEnd.getChildren().addAll(gameOver);

        /**
         * Scenen luonti
         */
        Scene scene = new Scene(root,1900,1000);
        stage.setTitle("Stars War");
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }
    /**
     * Main metodi
     */
    public static void main(String[] args) {
        launch(args);
    }
}