import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	public static final int SIM_SPEED = 100;
	public static final int WORLD_SIZE = 25;

	public static final int TILE_SIZE = 25;
	static int length = 4;
	
	public static void main(String[] args) throws InterruptedException {
		
		Stack<Vector2> snake = new Stack<>();
		Vector2 head = new Vector2(10, 10);
		Vector2 apple = new Vector2(15,15);
		Vector2 direction = new Vector2();
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WORLD_SIZE*TILE_SIZE, WORLD_SIZE*TILE_SIZE);
		frame.setContentPane(new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, WORLD_SIZE*TILE_SIZE, WORLD_SIZE*TILE_SIZE);
				
				g.setColor(Color.GREEN);
				for (Vector2 vec: snake) {
					g.fillRect(vec.x*TILE_SIZE, vec.y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
				}
				
				g.fillRect(head.x * TILE_SIZE, head.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				
				g.setColor(Color.RED);
				g.fillRect(apple.x * TILE_SIZE, apple.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				
				g.setColor(Color.WHITE);
				g.drawString("Length: " + Main.length, 10, 15);
			}
		});
		
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_W) {
					direction.set(0, -1);
				}
				if(e.getKeyCode() == KeyEvent.VK_A) {
					direction.set(-1, 0);
				}
				if(e.getKeyCode() == KeyEvent.VK_S) {
					direction.set(0, 1);
				}
				if(e.getKeyCode() == KeyEvent.VK_D) {
					direction.set(1, 0);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		frame.setVisible(true);
		
		while(true) {
			// Give the game some time for human to think
			// Human dumb
			Thread.sleep(SIM_SPEED);
			
			snake.push(new Vector2(head.x, head.y));
			
			head.x += direction.x;
			head.y += direction.y;

			for (Vector2 vec: snake) {
				if(head.x == vec.x && head.y == vec.y) {
					length = 4;
					head.set(5, 5);
					snake.clear();
					break;
				}
			}
			
			if(head.x < 0 || head.x >= WORLD_SIZE || head.y < 0 || head.y >= WORLD_SIZE) {
				length = 4;
				head.set(5, 5);
				snake.clear();
			}
			
			if(head.x == apple.x && head.y == apple.y) {
				length++;
				apple.set((int)(Math.random()*WORLD_SIZE), (int)(Math.random()*WORLD_SIZE));
			}
			
			if(snake.size() > length &&  length > 0) {
				snake.remove(0);
			}
			
			frame.repaint();
		}
	}
	
	public static class Vector2 {
		int x;
		int y;

		public Vector2(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Vector2() {
			this.x = 0;
			this.y = 0;
		}
		
		public void set(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
