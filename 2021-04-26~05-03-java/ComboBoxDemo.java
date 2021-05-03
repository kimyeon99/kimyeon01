package Ysj;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ComboBoxDemo extends JFrame implements ActionListener{
	private JLabel label;
	private JComboBox animalList;
	
	public ComboBoxDemo() {
		this.setTitle("YSJ Combo");
		this.setSize(500,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		label = new JLabel();
		
		String[] animals = {"Y", "S", "J"};
		animalList = new JComboBox(animals);
		animalList.setSelectedIndex(0);
		animalList.addActionListener(this);
		
		this.add(label);
		this.add(animalList, BorderLayout.NORTH);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String name = (String) animalList.getSelectedItem();
		changePicture(name);
	}
	
	protected void changePicture(String name) {
		label.setText(name);
	}
	

	public static void main(String[] args) {
		new ComboBoxDemo();
	}

}
