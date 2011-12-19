package net.bplaced.razkiel.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Game extends Canvas  implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final String NAME = "Supertoll";
	public static final int HEIGHT = 240;
	public static final int WIDTH = HEIGHT * 16/10;
	
	private int ticks = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private boolean running = false;
	
	
	
	public void start() {
		running = true;
		new Thread(this).start();
		
	}
	public void stop() {
		running = false;
		
	}
	public void run() {
		while (running) {
			runStep();
		}
			
	}
	public void runStep() {
		tick();
		render();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		g.setColor(Color.blue);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.dispose();
		
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	public void tick() {
		
		System.out.println(ticks + "Ticks");
		ticks++;
		
	}
	public static void main (String[] args) {
		Game game = new Game();

		game.setMinimumSize(new Dimension(WIDTH *2 ,HEIGHT * 2));
		game.setMaximumSize(new Dimension(WIDTH *2 ,HEIGHT * 2));
		game.setPreferredSize(new Dimension(WIDTH *2 ,HEIGHT * 2));
		
		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
		
	}
}
