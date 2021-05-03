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
		
		this.pack(); // pack() �޼ҵ�� JFrame�� ���빰�� �°� ������ ũ�� ����
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = textField.getText();
		textArea.append(text + "\n"); // text �̾���̱�
		
		textField.selectAll(); // textField �� ����(�˻��� �� �巡��)
		textArea.setCaretPosition(textArea.getDocument().getLength());
		// �� �ڵ带 append �ؿ� �߰����ָ� �׻� �Ʒ��� ��ũ�� �ȴ�
		
	}
	public static void main(String[] args) {
		new TextAreaDemo();
	}

}
