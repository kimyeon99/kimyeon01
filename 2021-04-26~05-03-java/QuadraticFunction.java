package Ysj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QuadraticFunction extends JPanel implements ActionListener {

        private JTextField a , b, c;
        private double al = 1.0, bl = -5.0, cl = 6.0;
       
        public QuadraticFunction() {
        	a = new JTextField("1.0",10);
        	b = new JTextField("-5.0",10);
        	c = new JTextField("6.0",10);
        	
        	this.add(a);
        	this.add(b);
        	this.add(c);
        	
        	JButton drawButton = new JButton("Draw");
        	this.add(drawButton);
        	drawButton.addActionListener(this);
	}
        protected void paintComponent(Graphics g) {
        	super.paintComponent(g);
        	Graphics2D g2 = (Graphics2D) g;
        	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
        	g2.drawLine(200, 200, 500, 200); // 직선 그리기
        	g2.drawLine(300, 0, 300, 400);
        	
        	g2.setPaint(Color.red);
        	System.out.println("a: " + a + "b: " + b + "c: " + c);
        	
        	for(int i = -20; i < 20; i++) {
        		int x = i;
        		int y = (int) (al * x * x - bl * x + cl);
        		Shape s = new Ellipse2D.Float(300+x-2, 200-y+2, 4, 4);
        		g2.fill(s);
        	}
        }
        @Override
        public void actionPerformed(ActionEvent e) {
        	al = Double.parseDouble(a.getText());
        	bl = Double.parseDouble(b.getText());
        	cl = Double.parseDouble(c.getText());
        	
        	System.out.println("al: " + a + "bl : " + bl + "cl: " + cl);
        	repaint();
        }
        
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new QuadraticFunction());
		frame.setSize(600,400);
		frame.setVisible(true);
	}

}
