package models;

public class Rectangle {

    private Vector2 topLeft;
    private Vector2 botRight;

    public Rectangle(double minX, double minY, double maxX, double maxY) {
        setTopLeft(new Vector2(minX, minY));
        setBotRight(new Vector2(maxX, maxY));
    }

    public Rectangle(Vector2 p1, Vector2 p2) {
        double minX = Math.min(p1.getX(), p2.getX());
        double minY = Math.min(p1.getY(), p2.getY());
        double maxX = Math.max(p1.getX(), p2.getX());
        double maxY = Math.max(p1.getY(), p2.getY());
        setTopLeft(new Vector2(minX, minY));
        setBotRight(new Vector2(maxX, maxY));
    }

    public Vector2 getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Vector2 p1) {
        this.topLeft = p1;
    }

    public Vector2 getBotRight() {
        return botRight;
    }

    public void setBotRight(Vector2 p2) {
        this.botRight = p2;
    }
    
    public double getTop() {
        return topLeft.getY();
    }
    
    public double getBottom() {
        return botRight.getY();
    }
    
    public double getLeft() {
        return topLeft.getX();
    }
    
    public double getRight() {
        return botRight.getX();
    }

    public double getWidth() {
        return botRight.getX() - topLeft.getX();
    }

    public double getHeight() {
        return botRight.getY() - topLeft.getY();
    }

    public int intWidth() {
        return (int) getWidth();
    }

    public int intHeight() {
        return (int) getHeight();
    }
}
