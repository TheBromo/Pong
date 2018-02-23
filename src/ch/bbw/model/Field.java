package ch.bbw.model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Field {

    int height = 500, width = 800;
    Ball ball = new Ball(1, 1, 1);
    HashMap<InetAddress, Paddle> paddles = new HashMap<>();
    private static InetAddress localhost;

    public Field(InetAddress secondPlayer) throws UnknownHostException {
        localhost = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());
        paddles.put(secondPlayer, new Paddle(width - 30 - 20, 10, 150, 20));
        paddles.put(localhost, new Paddle(30, 10, 150, 20));

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
