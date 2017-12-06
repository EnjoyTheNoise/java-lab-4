package animationWindow;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Circle extends Figure {
	
	public Circle(Graphics2D buffer, int delay, int width, int height){
		super(buffer,delay,width,height);
		shape = new Ellipse2D.Double(0, 0, 20, 20);
		aft = new AffineTransform();
		area = new Area(shape);
	}

}
