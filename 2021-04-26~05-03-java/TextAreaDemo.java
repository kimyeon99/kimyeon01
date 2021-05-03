package Ysj;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextAreaDemo extends JFrame implements ActionListener{
	private JTextField textField;
	private JTextArea textArea;
	
	public TextAreaDemo() {
		this.setTitle("Text Area Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField(30);
		textField.addActionListener(this);
		
		textArea = new JTextArea(10, 30);
		textArea.setEditable(false);
		
		this.add(textField, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.pack(); // pack() 메소드는 JFrame의 내용물에 맞게 윈도우 크기 조절
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = textField.getText();
		textArea.append(text + "\n"); // text 이어붙이기
		
		textField.selectAll(); // textField 다 선택(검색어 다 드래그)
		textArea.setCaretPosition(textArea.getDocument().getLength());
		// 이 코드를 append 밑에 추가해주면 항상 아래로 스크롤 된다
		
	}
	public static void main(String[] args) {
		new TextAreaDemo();
	}

}
