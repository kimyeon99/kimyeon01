package animation_jy;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Animation extends JPanel implements ActionListener {
	private final int WIDTH = 500, HEIGHT = 500;
	private BufferedImage image;
	private Timer timer;
	private int x, y;
	private final int START_X = 250, START_Y = 250;
	private int dx, dy;
	private int airx = 1;
	private int airy = 1;

	public Animation() {

		File file = new File("./airplane.png");
		
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1); // 0 이 아니면 다 똑같은 오류 종료
		}

		x = START_X;
		y = START_Y;

		timer = new Timer(3, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, x, y, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
        if(x > 270 || x < 0) {
        	airx = -airx;
        }
        if(y > 250 || y < 0) {
        	airy = -airy;
        }
        
        if(airx > 0) {
        	x = x + 2;
        } else {
        	x = x - 2;
        }
        
        if(airy > 0) {
        	y = y - 1;
        } else {
        	y = y + 1;
        }
        


		repaint();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Animation());
		frame.setTitle("애니메이션 테스트");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
