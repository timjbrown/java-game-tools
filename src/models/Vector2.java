package models;

import java.awt.Point;

public class Vector2 {

    private double x;
    private double y;

    /**
     * Creates a Vector2 from radians in range (-pi,pi)
     * @param radians in range (-pi,pi)
     */
    public Vector2(double radians) {
        this.x = Math.cos(radians);
        this.y = Math.sin(radians);
    }
    
    /**
     * Creates a Vector2 from x,y components
     * @param x component of the vector
     * @param y component of the vector
     */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a Vector2 from x,y components of the given point
     * @param point whose x,y components form the vector
     */
    public Vector2(Point point) {
        this.x = point.x;
        this.y = point.y;
    }
    
    /**
     * Create a Vector2 randomly between minX,maxX and minY,maxY components
     * @param minX
     * @param maxX
     * @param minY
     * @param maxY
     */
    public Vector2(double minX, double maxX, double minY, double maxY) {
        this.x = Math.random() * (maxX - minX) + minX;
        this.y = Math.random() * (maxY - minY) + minY;
    }
    
    public static Vector2 zero() {
        return new Vector2(0, 0);
    }

    public Vector2 copy() {
        return new Vector2(x, y);
    }

    public double dist(Vector2 other) {
        return Math.sqrt((other.x - this.x) * (other.x - this.x)
                + (other.y - this.y) * (other.y - this.y));
    }

    public double length() {
        return Math.abs(dist(new Vector2(0, 0)));
    }

    public double dot(Vector2 other) {
        return x * other.x + y * other.y;
    }
    
    public Vector2 neg() {
        return this.mul(-1);
    }

    public Vector2 unit() {
        double length = length();
        if (length == 0)
            return copy();
        return new Vector2(x / length, y / length);
    }

    public Vector2 mul(double val) {
        return new Vector2(x * val, y * val);
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }

    public Vector2 sub(Vector2 other) {
        return new Vector2(x - other.x, y - other.y);
    }

    public Vector2 rot(double theta) {
        double c = Math.cos(theta);
        double s = Math.sin(theta);
        return new Vector2(x * c - y * s, x * s + y * c);
    }
    
    /**
     * Returns angle between 2 vectors in range (-pi,pi)
     * @param other the second vector whose angle between will be found
     * @return angle between 2 vectors in range (-pi,pi)
     */
    public double angleBetween(Vector2 other) {
        double result =  Math.atan2(other.getY(), other.getX()) - Math.atan2(this.getY(), this.getX());
        if (result > Math.PI) {
        return (Math.PI * 2 - result) * (-1);
        }
        return result;
    }
    
    /**
     * Returns the angle of this vector in radians
     * @return the angle of this vector in radians
     */
    public double getRadians() {
        return Math.atan2(y, x);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int intX() {
        return (int) x;
    }

    public int intY() {
        return (int) y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
