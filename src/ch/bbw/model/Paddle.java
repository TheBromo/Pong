package ch.bbw.model;

public class Paddle {
    int x, y, height, width;
    boolean movingUp, movingDown;

    public Paddle(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getY() {
        return y;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public boolean isColliding(double x, double y, double r, boolean isHost) {
        double rr = r / 2;
        if (isHost) {
            return x - rr >= this.x && x <= this.x + rr + width && (y - rr) <= (this.y + height) && (y + rr) >= this.y;
        } else {
            return x + rr >= this.x && x <= this.x + rr + width && (y - rr) <= (this.y + height) && (y + rr) >= this.y;
        }
    }

    public double getRelativePos(int x) {
        if (x >= this.x && x <= this.x + height) return (double) (x - this.x) / height;
        return -1;

    }

    public double getCollideAngle(int x) {
        return 0;
    }
}
