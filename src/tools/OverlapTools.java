package tools;

import models.Vector2;

public class OverlapTools {

    private static double dist(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static Vector2 circleCircle(double c1x, double c1y, double c1r,
            double c2x, double c2y, double c2r) {
        if (CollidingTools.circleCircle(c1x, c1y, c1r, c2x, c2y, c2r)) {
            Vector2 c1 = new Vector2(c1x, c1y);
            Vector2 c2 = new Vector2(c2x, c2y);
            Vector2 c2_to_c1 = c1.sub(c2);
            double overlap = c1r + c2r - c2_to_c1.length();
            return c2_to_c1.unit().mul(overlap);
        }
        return null;
    }

    public static Vector2 circleRect(double cx, double cy, double cr, double rx,
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
        if (dist(cx, cy, nearX, nearY) <= cr) {
            Vector2 c = new Vector2(cx, cy);
            Vector2 near = new Vector2(nearX, nearY);
            Vector2 near_to_c = c.sub(near);
            double overlap = cr - near_to_c.length();
            return near_to_c.unit().mul(overlap);
        }
        return null;
    }

    public static Vector2 circleInRect(double cx, double cy, double cr,
            double rx, double ry, double rw, double rh) {
        double overlapX = 0;
        double overlapY = 0;
        if (cx - cr < rx) // left
            overlapX = rx - (cx - cr);
        else if (cx + cr > rx + rw) // right
            overlapX = rx + rw - (cx + cr);
        if (cy - cr < ry) // top
            overlapY = ry - (cy - cr);
        else if (cy + cr > ry + rh) // bottom
            overlapY = ry + rh - (cy + cr);
        Vector2 overlap = new Vector2(overlapX, overlapY);
        if (overlap.length() == 0)
            return null;
        return overlap;
    }
}
