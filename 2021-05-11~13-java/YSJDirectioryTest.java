package Ysj;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class YSJDirectioryTest extends JFrame implements ActionListener{

    private Map<String, String> map = new HashMap<>();
	private JButton aBtn = new JButton("�˻�");
	private JButton bBtn = new JButton("�߰�");
	private JTextField field = new JTextField(30);
	private static final String DIC_FILE_NAME = "dict.props";


	public YSJDirectioryTest() {
		this.setTitle("YSJ DirectioryTest");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.add(field);
		field.setText("�ܾ �Է��ϼ���.");
		
		this.add(aBtn);
		aBtn.addActionListener(this);

		this.add(bBtn);
		bBtn.addActionListener(this);
		
		this.setLayout(new FlowLayout());
		//this.setPreferredSize(new Dimension(600, 50));// ����

		this.pack();
		this.setVisible(true);
		
		// ���� map���� �о����
		readFileWithMap();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == aBtn) { // aBtn Ŭ�� (�˻�)
			if(map.get(field.getText()) == null) { // �˻��Ϸ��� ������ ���� ��
				JOptionPane.showMessageDialog(this, "�ܾ �����ϴ�.", 
						field.getText(), JOptionPane.INFORMATION_MESSAGE);
			} else { // �˻��Ϸ��� ������ ���� ��
				JOptionPane.showMessageDialog(this , map.get(field.getText()),
						field.getText(), JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(e.getSource() == bBtn) { // bBtn Ŭ�� (�߰�)
			String value = JOptionPane.showInputDialog(this,  "�߰��Ϸ��� �ܾ� �Է�\n" +field.getText() + ": " ,
					"�ܾ� �߰�", JOptionPane.INFORMATION_MESSAGE);
			if(value == null || value.trim().length() == 0) {
					return;
			}
			map.put(field.getText(), value);
			JOptionPane.showMessageDialog(this, "�߰� ����");
			addWord();
		}
	}
	
	public void addWord() {
		// �ܾ� �߰��� �ʿ��ٰ� �߰��ϴ� ���� �ƴ϶�
		// FileWriter�� �̿��Ͽ� dict.props�� �ܾ �߰��Ѵ�!!!!
		
		try(FileWriter fw = new FileWriter(DIC_FILE_NAME);
			BufferedWriter bw = new BufferedWriter(fw);	
			) {
			String str = field.getText() + "=" + map.get(field.getText());
			 bw.write(str);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void readFileWithMap() {			
		// windows�� INI�� ���� ���
		// DB�� ���� ���������� ���Ϸ� �����س��� ����ϴ� �뵵
		Properties pro = new Properties();

		try( FileReader fr = new FileReader(DIC_FILE_NAME);
				BufferedReader br = new BufferedReader(fr);
				){

				pro.load(br);

			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			Set<Object> set = pro.keySet();
			for (Object key : set) {
				Object value = pro.get(key);
				map.put((String) key, (String) value);
			}
	}
	
	public static void main(String[] args) {
		new YSJDirectioryTest();
	}
}
