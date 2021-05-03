package Ysj;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextAreaDemo2 extends JFrame implements ActionListener {
	private JTextField field;
	private JTextArea area;
	public TextAreaDemo2() {
		this.setTitle("YSJ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		field = new JTextField(30);
		field.addActionListener(this);
		this.add(field,BorderLayout.NORTH);
		
		area = new JTextArea(10,30);
		area.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(area);
		
		this.add(scroll);
		
		this.pack();
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		String text = field.getText();
		area.append(text+"\n");
		
		field.selectAll();
		area.setCaretPosition(area.getDocument().getLength());
	}
	public static void main(String[] args) {
		new TextAreaDemo2();
	}


}
