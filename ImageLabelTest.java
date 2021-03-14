package scpark;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageLabelTest extends JFrame implements ActionListener{
	private JPanel panel;
	private JLabel label;
	private JButton button;
	
	
	public ImageLabelTest() {
		this.setSize(300, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		label = new JLabel("버튼을 누르세여");
		
		button = new JButton("이미지 보기");
		ImageIcon icon = new ImageIcon("icon.gif");
		button.setIcon(icon);
		button.addActionListener(this);
		
		panel.add(label);
		panel.add(button);
		
		this.add(panel);
		this.setVisible(true);
		

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ImageIcon dog = new ImageIcon("교됴소.png");
		File file = new File("교도소.png");
		System.out.println(file.getAbsolutePath());
		label.setIcon(dog);
		label.setText(null);
	}
	
	public static void main(String[] args) {
		new ImageLabelTest();
	}
}
