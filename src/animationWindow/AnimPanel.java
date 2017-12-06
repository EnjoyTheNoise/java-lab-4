package animationWindow;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;
import animationWindow.Square;
import animationWindow.Ellipse;

public class AnimPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Figure> figureList = new LinkedList<Figure>();			//lista figur

	// bufor
	Image image;
	// wykreslacz ekranowy
	Graphics2D device;
	// wykreslacz bufora
	Graphics2D buffer;

	private int delay = 30;

	private Timer timer;

	private static int numer = 0;

	public AnimPanel() {
		super();
		setBackground(Color.WHITE);
		timer = new Timer(delay, this);
	}
	
	class PanelResizeListener extends ComponentAdapter{		//skalowanie animacji - resetuje ca³oœæ
		public void componentResized(ComponentEvent e) {
			image = createImage(e.getComponent().getWidth(), e.getComponent().getHeight());
			buffer = (Graphics2D) image.getGraphics();
			buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			device = (Graphics2D) getGraphics();
			device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
	}

	public void initialize() {
		int width = getWidth();
		int height = getHeight();

		image = createImage(width, height);
		buffer = (Graphics2D) image.getGraphics();
		buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		device = (Graphics2D) getGraphics();
		device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	void addFig() {
		Figure fig = null;
		if(numer % 5 == 0) {
		fig =  new Square(buffer, delay, getWidth(), getHeight());
		}
		if(numer % 5 == 1) {
		fig =  new Ellipse(buffer, delay, getWidth(), getHeight());
			}
		if(numer % 5 == 2) {
		fig =  new Triangle(buffer, delay, getWidth(), getHeight());
			}
		if(numer % 5 == 3) {
			fig =  new Rectangle(buffer, delay, getWidth(), getHeight());
				}
		if(numer % 5 == 4) {
			fig =  new Circle(buffer, delay, getWidth(), getHeight());
				}
		numer++;
		timer.addActionListener(fig);
		figureList.add(fig);
		new Thread(fig).start();
	}

	void animate() {
		if (timer.isRunning()) {
			timer.stop();
			for(Figure fig : figureList) {			//dla ka¿ej figury w kolekcji odpowiednio pause/unpause
				fig.pause();
			}
		} else {
			timer.start();
			for(Figure fig : figureList) {
				fig.unpause();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		device.drawImage(image, 0, 0, null);
		buffer.clearRect(0, 0, getWidth(), getHeight());
	}
}