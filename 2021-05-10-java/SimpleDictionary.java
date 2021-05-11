package scpark3;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleDictionary extends JPanel implements ActionListener{

	private JTextField inputField = new JTextField(30); // �Է� �ʵ�
	private JButton searchBtn = new JButton("�˻�");
	private JButton addBtn = new JButton("�߰�");
	
	// �ѿ� ���� : �ѱ۴ܾ�� �����Ǵ� ����ܾ��� ���� ����
	private Map<String, String> dict = new HashMap<>();
	private static final String DIC_FILE_NAME = "dict.props";
	
	public SimpleDictionary() {
		this.add(inputField);
		this.add(searchBtn);
		this.add(addBtn);
		searchBtn.addActionListener(this);
		addBtn.addActionListener(this);
		
		this.setPreferredSize(new Dimension(600, 50));
		buildDictionaryFromFile();
	}
	
	private void addWordToFile(String key, String value) {
		try (FileWriter fWriter = new FileWriter(DIC_FILE_NAME)){
			// writer = ���ڿ��� �ش�
			String str = key + "=" + value + "\n";
			fWriter.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void buildDictionaryFromFile(){
		//Properties�� Map�� ����� �����ϰ� ����
		//�̰� �������� �� �پ� �߶� �޾Ҿ�� ��.
		Properties props = new Properties();
		// exception �߻��� �� ������ try/catch �ʼ�
		try(FileReader fReader = new FileReader(DIC_FILE_NAME)){
			props.load(fReader);	
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Set<Object> set = props.keySet();
		for (Object key : set) {
			Object value = props.get(key);
			dict.put((String) key, (String) value);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		String key = inputField.getText();
		System.out.println("["+key+"]");
		
		if(key.trim().length() == 0) return;
		
		if(e.getSource() == searchBtn) {
			// inputfield�� �Էµ� �ܾ ����
			// dict �� ��ü���� �� �ܾ �����Ǵ� ����ܾ ã�� ���÷���
			String value = dict.get(key);
			if(value == null) {
				JOptionPane.showMessageDialog(this, "�ܾ� �� ã��.", key, JOptionPane.ERROR_MESSAGE);
				
			} else {
				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(e.getSource() == addBtn) {
			// inputfield�� �Էµ� �ܾ ����
			// �� �ܾ �����Ǵ� ����ܾ �Է¹ް�
			// dict �� ��ü�� <�ѱ۴ܾ�, ����ܾ�> �߰�,
			String value = JOptionPane.showInputDialog(this, key + "�� �����Ǵ� �ܾ� �Է�", JOptionPane.INFORMATION_MESSAGE);
			if(value == null || value.trim().length() == 0) return;
			dict.put(key, value);
			JOptionPane.showInputDialog(this, "�߰��Ǿ����ϴ�.","����");
		}
		inputField.requestFocus();
	}
	
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new SimpleDictionary());
		frame.setTitle("�ѿ�����");
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
