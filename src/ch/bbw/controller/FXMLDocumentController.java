package ch.bbw.controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import ch.bbw.model.Field;
import ch.bbw.model.Paddle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * @author TheBromo
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private Field field;
    private long lastUpdate;
    private AnimationTimer animationTimer;
    int step = 5;

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        Paddle paddle = field.getPaddles().get(Field.getLocalhost());
        if (event.getCode() == KeyCode.W) {
            paddle.setMovingUp(true);
        } else if (event.getCode() == KeyCode.S) {
            paddle.setMovingDown(true);
        }
    }

    @FXML
    private void handleKeyReleased(KeyEvent event) {

        Paddle paddle = field.getPaddles().get(Field.getLocalhost());
        if (event.getCode() == KeyCode.W) {
            paddle.setMovingUp(false);
        } else if (event.getCode() == KeyCode.S) {
            paddle.setMovingDown(false);
        }
    }

    private void draw() {
        gc.setFill(Color.color(0, 0, 0));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.color(1, 1, 1));
        for (InetAddress key : field.getPaddles().keySet()) {
            Paddle paddle = field.getPaddles().get(key);
            gc.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
        }
        double ballR = field.getBall().getR();
        gc.fillOval(field.getBall().getX() - (ballR / 2), field.getBall().getY() - (ballR / 2), ballR, ballR);
        gc.setFill(Color.RED);
        gc.fillRect(0,  500,800,500);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        canvas.setHeight(screenSize.getHeight());
        canvas.setWidth(screenSize.getWidth());
        try {
            field = new Field(InetAddress.getByName("localhost"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        draw();

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                field.update(TimeUnit.NANOSECONDS.toMillis(now) - lastUpdate);
                lastUpdate = TimeUnit.NANOSECONDS.toMillis(now);
                draw();

            }
        };
        lastUpdate = System.currentTimeMillis();
        animationTimer.start();
    }

}
