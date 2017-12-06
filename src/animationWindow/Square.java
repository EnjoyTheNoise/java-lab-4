package animationWindow;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Square extends Figure{

	public Square(Graphics2D buffer, int delay, int width, int height) {
		super(buffer,delay,width,height);
		shape = new Rectangle2D.Float(0,0,25,25);
		aft = new AffineTransform();
		area = new Area(shape);

	}
}
