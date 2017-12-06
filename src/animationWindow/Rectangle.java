package animationWindow;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Figure{

	public Rectangle(Graphics2D buffer, int delay, int width, int height) {
		super(buffer,delay,width,height);
		shape = new Rectangle2D.Float(0,0,30,15);
		aft = new AffineTransform();
		area = new Area(shape);

	}
}
