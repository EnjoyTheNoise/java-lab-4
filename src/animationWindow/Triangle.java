package animationWindow;

import java.awt.Polygon;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class Triangle extends Figure {
    public Triangle(Graphics2D buffer, int delay, int width, int height) {
        super(buffer, delay, width, height);
        shape = new Polygon(new int[] {0,50,25}, new int[] {0,0,50}, 3);
        aft = new AffineTransform();
        area = new Area(shape);
    }

}