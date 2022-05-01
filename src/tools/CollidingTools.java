package tools;

public class CollidingTools {

    private static double dist(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static boolean pointPoint(double x1, double y1, double x2,
            double y2) {
        return x1 == x2 && y1 == y2;
    }

    public static boolean pointCircle(double px, double py, double cx,
            double cy, double cr) {
        return dist(px, py, cx, cy) <= cr;
    }

    public static boolean circleCircle(double c1x, double c1y, double c1r,
            double c2x, double c2y, double c2r) {
        return dist(c1x, c1y, c2x, c2y) <= c1r + c2r;
    }

    public static boolean pointRect(double px, double py, double rx, double ry,
            double rw, double rh) {
        return px >= rx && px <= rx + rw && py >= ry && py <= ry + rh;
    }

    public static boolean rectRect(double r1x, double r1y, double r1w,
            double r1h, double r2x, double r2y, double r2w, double r2h) {
        return r1x + r1w >= r2x && r1x <= r2x + r2w && r1y + r1h >= r2y
                && r1y <= r2y + r2h;
    }

    public static boolean circleRect(double cx, double cy, double cr, double rx,
            double ry, double rw, double rh) {
        // find nearest point on rect to circle
        double nearX = cx;
        double nearY = cy;
        if (cx < rx)
            nearX = rx;
        else if (cx > rx + rw)
            nearX = rx + rw;
        if (cy < ry)
            nearY = ry;
        else if (cy > ry + rh)
            nearY = ry + rh;

        // colliding if dist(circle center, nearest point on rect) <= radius
        return dist(cx, cy, nearX, nearY) <= cr;
    }

    public static boolean linePoint(double x1, double y1, double x2, double y2,
            double px, double py) {
        // get distance from the point to the two ends of the line
        double d1 = dist(px, py, x1, y1);
        double d2 = dist(px, py, x2, y2);

        // get the length of the line
        double lineLen = dist(x1, y1, x2, y2);

        double buffer = 0.1;

        // colliding if distance sum ~= lineLen
        return Math.abs(d1 + d2 - lineLen) <= buffer;
    }
}
