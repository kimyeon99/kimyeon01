package scpark;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MiniPingPongGame extends JPanel implements KeyListener {
	private Ball ball;
	private Racquet racquet1;
	private Racquet racquet2;
	private Score score;
	
	public MiniPingPongGame() {
		ball = new Ball(this, Color.red);
		this.setBackground(Color.green);
		racquet1 = new Racquet(this, 10, 150, Color.blue, 1);
		racquet2 = new Racquet(this, 560, 150, Color.yellow, 2);
		score = new Score(600,400);
		
		this.setFocusable(true);
		this.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		racquet1.KeyPressed(e);
		racquet2.KeyPressed(e);
	}
	
	@Override
    public void keyReleased(KeyEvent e) {
		racquet1.keyReleased(e);
		racquet2.keyReleased(e);
	}
	
	void move() {
		ball.move();
		racquet1.move();
		racquet2.move();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		ball.draw(g2d);
		racquet1.draw(g2d);
		racquet2.draw(g2d);
		score.draw(g2d);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("PingPong game");
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		MiniPingPongGame game = new MiniPingPongGame();
		frame.add(game);
		frame.setVisible(true);
		while(true) {
			game.move();
			game.repaint();
		    try {
		    	Thread.sleep(10);
		    } catch(InterruptedException e) {
		    	e.printStackTrace();
		    }
		}
	}
	
	class Ball {
		private static final int RADIUS = 20;
		private int x = 0, y = 0, xSpeed = 2, ySpeed = 2;
		private MiniPingPongGame game;
		private Color color;
		
		public Ball(MiniPingPongGame game, Color color) {
			this.game = game;
			this.color = color;
		}
		
		void move() {
			if(x + xSpeed < 0) {
				reset();
				xSpeed = 2;
				score.player1 += 1;
			}
			if(x + xSpeed > game.getWidth() - 2 * RADIUS) {
				reset();
				xSpeed = -2;
				score.player2 += 1;
			}
			if(y + ySpeed < 0) {
				ySpeed = 2;
			}
			if(y + ySpeed > game.getHeight() - 2 * RADIUS) {
				ySpeed = -2;
			}
			if(collision()) xSpeed = -xSpeed;
			x += xSpeed;
			y += ySpeed;
		}
		
		
		private boolean collision() {
			return game.racquet1.getBounds().intersects(getBounds())
					|| game.racquet2.getBounds().intersects(getBounds());
		}
		
		public void draw(Graphics2D g) {
			g.setColor(color);
			g.fillOval(x, y, 2 * RADIUS, 2 * RADIUS);
		}
		
		public Rectangle getBounds() {
			return new Rectangle(x, y, 2 * RADIUS, 2 * RADIUS);
		   }
		
		public void reset() {
			x = 300;
			y = 200;
			racquet1.y = 150;
			racquet2.y = 150;
			
			xSpeed = 2;
			ySpeed = 2;
		}
		
	    
	    }
	    
	class Racquet {
	    	private static final int WIDTH = 10;
	    	private static final int HEIGHT = 80;
	    	private int x = 0, y = 0;
	    	private int xSpeed = 0;
	    	private int ySpeed = 0;
	    	private MiniPingPongGame game;
	    	private Color color;
	    	private int type = 1;
	    	
	    public Racquet(MiniPingPongGame game, int x, int y, Color color, int type) {
	    	this.game = game;
	    	this.x = x;
	    	this.y = y;
	    	this.color = color;
	    	this.type = type;
	    }
	    
	    public void move() {
	    	if(y + ySpeed > 0 && y + ySpeed < game.getHeight() - HEIGHT) {
	    		y += ySpeed;
	    	}
	    }
	    
	    public void draw(Graphics2D g) {
	    	g.setColor(color);
	    	g.fillRect(x, y, WIDTH, HEIGHT);
	    }
		
		public void keyReleased(KeyEvent e) {
			if(type == 2) {
				racquet2.ySpeed = 0;
			}
			if(type == 1) {
				racquet1.ySpeed = 0;
			}
		}
		
		public void KeyPressed(KeyEvent e) {
			
			if(type == 2) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					racquet2.ySpeed = -4;
				}
				else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					racquet2.ySpeed = 4;
				}
			}
			
			if(type == 1) {
				if(e.getKeyCode() == KeyEvent.VK_W) {
					racquet1.ySpeed = -4;
				}
				else if(e.getKeyCode() == KeyEvent.VK_S) {
					racquet1.ySpeed= 4;
			    	}
			}
		}
		
		public Rectangle getBounds() {
			return new Rectangle(x, y, WIDTH, HEIGHT);
		}
	}
	
	public class Score {
		private int GAME_WIDTH ;
		private int GAME_HEIGHT;
		protected int player1 = 0;
		protected int player2 = 0;
		
		public Score(int gameWidth, int gameHeight) {
			GAME_WIDTH = gameWidth;
			GAME_HEIGHT = gameHeight;
		}
		public void draw(Graphics g) {
			g.setColor(Color.white);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
			
			g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
			
			g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), GAME_WIDTH/2 - 85, 50);
			g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), GAME_WIDTH/2 + 20, 50);
		}
	}
}
