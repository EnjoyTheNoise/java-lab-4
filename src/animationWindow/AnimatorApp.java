package animationWindow;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

public class AnimatorApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private AnimPanel kanwa;
	private JButton btnAdd;
	private JButton btnAnimate;

	/**
	 * Launch the application.
	 */
	
	class ResizeListener extends ComponentAdapter{			//skalowanie ui
		public void componentResized(ComponentEvent e) {
			kanwa.setBounds(10, 11, e.getComponent().getWidth() - 30, e.getComponent().getHeight() - 80);
			btnAdd.setBounds(10, e.getComponent().getHeight() - 65, 80, 23);
			btnAnimate.setBounds(100, e.getComponent().getHeight() - 65, 80, 23);
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final AnimatorApp frame = new AnimatorApp();
					frame.addComponentListener(frame.new ResizeListener());		//dodanie listenera do zmiany rozmiaru okna
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param delay 
	 */
	public AnimatorApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int ww = 450, wh = 300;
		setBounds((screen.width-ww)/2, (screen.height-wh)/2, ww, wh);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		kanwa = new AnimPanel();
		kanwa.addComponentListener(kanwa.new PanelResizeListener()); 		//dodanie listenera do zmiany rozmiaru panelu
		setBackground(Color.WHITE);
		kanwa.setBounds(10, 11, 420, 220);
		contentPane.add(kanwa);
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				kanwa.initialize();
			}
		});

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kanwa.addFig();
			}
		});
		btnAdd.setBounds(10, 235, 80, 23);
		contentPane.add(btnAdd);
		
		btnAnimate = new JButton("Anim");
		btnAnimate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kanwa.animate();
			}
		});
		btnAnimate.setBounds(100, 235, 80, 23);
		contentPane.add(btnAnimate);
		
	}

}