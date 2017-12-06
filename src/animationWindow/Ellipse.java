package animationWindow;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figure {
	
	public Ellipse(Graphics2D buffer, int delay, int width, int height){
		super(buffer,delay,width,height);
		shape = new Ellipse2D.Double(0, 0, 25, 15);
		aft = new AffineTransform();
		area = new Area(shape);
	}

}
