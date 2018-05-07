package ch.bbw.model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Field {

    int height = 500, width = 800, step = 5;
    Ball ball = new Ball(300, 20, 20);
    HashMap<InetAddress, Paddle> paddles = new HashMap<>();
    private static InetAddress localhost;

    public Field(InetAddress secondPlayer) throws UnknownHostException {
        localhost = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());
        paddles.put(secondPlayer, new Paddle(width - 30 - 20, 10, 150, 20));
        paddles.put(localhost, new Paddle(30, 10, 150, 20));

    }

    public void update() {
        Paddle paddle = getPaddles().get(Field.getLocalhost());

        if (paddle.isMovingUp() && paddle.getY() > 0) {
            paddle.setY(paddle.getY() - step);
        } else if (paddle.isMovingDown() && paddle.getY() + paddle.getHeight() < getHeight()) {
            paddle.setY(paddle.getY() + step);
        }
        if (ball.y + ball.r / 2 >= height || ball.y - ball.r / 2 <= 0) ball.bounceWall();
        for (InetAddress address: paddles.keySet()) {
            if (paddles.get(address).isColliding(ball.x, ball.y, ball.r, address == Field.getLocalhost())) {
                ball.getNewVelX();
                ball.bouncePaddle();
            }
        }
        ball.move();

    }

    public Ball getBall() {
        return ball;
    }

    public HashMap<InetAddress, Paddle> getPaddles() {
        return paddles;
    }

    public static InetAddress getLocalhost() {
        return localhost;
    }

    public int getHeight() {
        return height;
    }


}
