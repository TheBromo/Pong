package ch.bbw.model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Field {

    int height = 500, width = 800, step = 250;
    Ball ball = new Ball(300, 20, 20);
    HashMap<InetAddress, Paddle> paddles = new HashMap<>();
    private static InetAddress localhost;

    public Field(InetAddress secondPlayer) throws UnknownHostException {
        localhost = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());
        paddles.put(secondPlayer, new Paddle(width - 30 - 20, 10, 150, 20));
        paddles.put(localhost, new Paddle(30, 10, 150, 20));

    }

    public void update(long passedTime) {
        if (passedTime<0)return;

        Paddle paddle = getPaddles().get(Field.getLocalhost());

        //movement for the paddle
        if (paddle.isMovingUp() && paddle.getY() > 0) {
            int distance = (int) (step / 1000.0 * passedTime);
            paddle.setY(paddle.getY() - distance);
        } else if (paddle.isMovingDown() && paddle.getY() + paddle.getHeight() < getHeight()) {
            int distance = (int) (step / 1000.0 * passedTime);
            paddle.setY(paddle.getY() + distance);
        }
        //if ball hits the roof or floor it Bounces
        //TODO: Make ball bounce according to its direction
        if (ball.y + ball.r / 2 >= height || ball.y - ball.r / 2 <= 0) ball.bounceWall();

        for (InetAddress address : paddles.keySet()) {
            //looks if the Ball is colliding with the paddle
            if (paddles.get(address).isColliding(ball.x, ball.y, ball.r, address == Field.getLocalhost())) {
                //TODO: Rename to "get new Angle", should return
                ball.getNewVelX(0);
                //bounces the ball of the Paddle
                ball.bouncePaddle();
            }
        }
        //calculates distance that ball moved in the time span
        double distance = 100.0 / 1000.0 * passedTime;
        //moves the ball in its directions
        ball.move(distance);

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
