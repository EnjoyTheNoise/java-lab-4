/**
 * 
 */
package animationWindow;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.Random;

/**
 * @author tb
 *
 */
public abstract class Figure implements Runnable, ActionListener {

	// wspolny bufor
	protected Graphics2D buffer;
	protected Area area;
	// do wykreslania
	protected Shape shape;
	// przeksztalcenie obiektu
	protected AffineTransform aft;

	// przesuniecie
	private int dx, dy;
	// rozciaganie
	private double sf;
	// kat obrotu
	private double an;
	private int delay;
	private int width;
	private int height;
	private Color clr;
	boolean isPaused;			//flaga pauzowania

	protected static final Random rand = new Random();

	public Figure(Graphics2D buf, int del, int w, int h) {
		delay = del;
		buffer = buf;
		width = w;
		height = h;

		dx = 1 + rand.nextInt(5);
		dy = 1 + rand.nextInt(5);
		sf = 1 + 0.05 * rand.nextDouble();
		an = 0.1 * rand.nextDouble();

		clr = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		// reszta musi byæ zawarta w realizacji klasy Figure
		// (tworzenie figury i przygotowanie transformacji)

	}
	
	public void pause() { 			//pauzowanie w¹tku
	    this.isPaused = true;
	}
	
	public void unpause() { 			//uruchamianie dalej
	    this.isPaused = false;
	}

	@Override
	public void run() {
		isPaused = false;
		// przesuniecie na srodek
		aft.translate(100, 100);
		area.transform(aft);
		shape = area;

		while (true) {
			// przygotowanie nastepnego kadru
			shape = nextFrame();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}
			while(isPaused)						//jeœli w¹tek jest spauzowany, czekamy 5ms i sprawdzamy raz jeszcze
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
				}
		}
	}

	protected Shape nextFrame() {
		// zapamietanie na zmiennej tymczasowej
		// aby nie przeszkadzalo w wykreslaniu
		area = new Area(area);
		aft = new AffineTransform();
		Rectangle bounds = area.getBounds();
		int cx = bounds.x + bounds.width / 2;
		int cy = bounds.y + bounds.height / 2;
		// odbicie
		if(cx - bounds.height / 2 < 0) {
			if(dx < 0)
			dx = -dx;
		}
		else if(cx + bounds.height / 2 > width) {
			if(dx > 0)
			dx = -dx;
		}
		else if(cy - bounds.width / 2 < 0) {
			if(dy < 0)
			dy = -dy;
		}
		else if(cy + bounds.width / 2 > height) {
			if(dy > 0)
			dy = -dy;
		}
		// zwiekszenie lub zmniejszenie
		if (bounds.height > height / 3 || bounds.height < 10)
			sf = 1 / sf;
		// konstrukcja przeksztalcenia
		aft.translate(cx, cy);
		aft.scale(sf, sf);
		aft.rotate(an);
		aft.translate(-cx, -cy);
		aft.translate(dx, dy);
		// przeksztalcenie obiektu
		area.transform(aft);
		return area;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// wypelnienie obiektu
		buffer.setColor(clr.brighter());
		buffer.fill(shape);
		// wykreslenie ramki
		buffer.setColor(clr.darker());
		buffer.draw(shape);
	}

}