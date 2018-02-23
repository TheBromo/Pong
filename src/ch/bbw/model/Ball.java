package ch.bbw.model;

public class Ball {
    int x, y, velX, velY;
    double r, angle;

    public Ball(int x, int y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        velX = 5;
        velY = 5;
    }

    public void move() {
        x += velX;
        y += velY;
    }

    public void bounceWall() {
        System.out.println("bounced");
        y *= -1;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
}
