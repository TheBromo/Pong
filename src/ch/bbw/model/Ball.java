package ch.bbw.model;

public class Ball {
    int x, y, velX, velY;
    double r, angle;

    public Ball(int x, int y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        velX = -5;
        velY = 5;
    }

    public void move(int distance) {
        //TODO: Add different velocities for x and y
        x += distance;
        y += distance;
    }

    public void bounceWall() {
        velY *= -1;
    }
    public void bouncePaddle(){
        velX *= -1;
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

    public double getNewVelX(double relativePos) {
        if (relativePos!=-1){
        }
        return 0;
    }
}
