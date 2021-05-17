package Ysj;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class YSJDirectioryTest extends JFrame implements ActionListener{

    private Map<String, String> dict = new HashMap<>();
	private JButton aBtn = new JButton("�˻�");
	private JButton bBtn = new JButton("�߰�");
	private JTextField field = new JTextField(30);
	private static final String DIC_FILE_NAME = "dict.props";

	//DB ���ῡ �ʿ��� �غ�
	private static final String JDBC_CLASS_NAME = "org.mariadb.jdbc.Driver";
	private static final String DB_SERVER_URL = "jdbc:mariadb://localhost:3306/oop";
	private static final String DB_USER= "root";
	private static final String DB_USER_PW= "1214";

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
		
		try {
			Class.forName(JDBC_CLASS_NAME);	
			bulidDictionaryFromDB();
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == aBtn) { // aBtn Ŭ�� (�˻�)
			if(dict.get(field.getText()) == null) { // �˻��Ϸ��� ������ ���� ��
				JOptionPane.showMessageDialog(this, "�ܾ �����ϴ�.", 
						field.getText(), JOptionPane.INFORMATION_MESSAGE);
			} else { // �˻��Ϸ��� ������ ���� ��
				JOptionPane.showMessageDialog(this , dict.get(field.getText()),
						field.getText(), JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(e.getSource() == bBtn) { // bBtn Ŭ�� (�߰�)
			String value = JOptionPane.showInputDialog(this,  "�߰��Ϸ��� �ܾ� �Է�\n" +field.getText() + ": " ,
					"�ܾ� �߰�", JOptionPane.INFORMATION_MESSAGE);
			if(value == null || value.trim().length() == 0) {
					return;
			}
			dict.put(field.getText(), value);
			JOptionPane.showMessageDialog(this, "�߰� ����");
			addWordToDB(field.getText(), dict.get(field.getText()));
		}
	}
	
	public void addWord() {
		// �ܾ� �߰��� �ʿ��ٰ� �߰��ϴ� ���� �ƴ϶�
		// FileWriter�� �̿��Ͽ� dict.props�� �ܾ �߰��Ѵ�!!!!
		
		try(FileWriter fw = new FileWriter(DIC_FILE_NAME);
			BufferedWriter bw = new BufferedWriter(fw);	
			) {
			String str = field.getText() + "=" + dict.get(field.getText());
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
				dict.put((String) key, (String) value);
			}
	}
	
	public void bulidDictionaryFromDB() {
		
		try(Connection con = //DB ������ ���� 
				DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)) 
		{
			// my sql JDBC ����̹��� �޸𸮿� ����
			// ����̹� Ŭ�����̸��� dbms���� �ٸ���.
			
			
			// select �� ����
			String sql = "select * from dict";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				// ���� �����Ͱ� ����Ű�� Į�� ���� ������ ��.
				// �� �÷��� Ÿ�Կ� ����, ȣ���� �޼��尡 �޶�����.
				// ���� �� char, varchar Ÿ���� Į����
				// getString("Į���̸�", �Ǵ� "Į�� ��ġ);
				// int Ÿ���� Į���� getInt(...);
				// DateTime, Date Ÿ���� Į�� ����
				// getDate();
				// rs.getString("han");
				String han = rs.getString(1);
				// rs.getString("eng");
				String eng = rs.getString(2);
				dict.put(han,eng);
				dict.put(eng, han); // �ֹ��� �˻��� �����ϵ��� ��
				System.out.println(han + " : " + eng);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
//		} finally {
//			try {con.close();} catch(Exception ignore) {}
//		}
		  
		}
	}
	
	public void addWordToDB(String key, String value) {
		
		/*
		 * ����̹��� �޸𸮸��� �����Ѵ� <- �޸� ����� �� ���� �ϸ� �ǰ�,
		 * �̹� �����ڿ��� �ߴ�.
		 * DB�� �����ؼ� connection ��ü�� ��ȯ�޴´�.
		 * connection ��ü���� PreparedStatement ��ü�� ��û�Ѵ�.
		 * PreparedStatement ��ü�� executeUpdate() �޼��带
		 * ȣ���ؼ� DB�� �����Ѵ�.
		 */
		
		try (Connection con = 
				// con.setAutoCommit(false); // ���� con.commit()�� �ؾ� Ŀ���� ��. ������ �ö�
				
				DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)){
				String sql = "insert into dict values(?, ?)"; 	// ?�� placeholder��.
				String sql2 = "update deposit set amount = amount - 100 where name = 'ȫ�浿' ";
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				pstmt2.executeUpdate();
				// �� ���� ������ �ļ� db������ shutdown ��
				sql2 = "update deposit set amount = amount - 100 where name = '������' ";
				
				/*
				 * ���� �غ�
				 * 1. ���� �˻�
				 * 2. ���缺 �˻�(���̺�, Į�� ���� ������ �ִ���, �ִٸ� ����ڰ�
				 *    ���ڵ带 ������ ������ �ִ��� ����� üũ)
				 * 3. �����ȹ�� �����. (execution plan)
				 */
				PreparedStatement pstmt = con.prepareStatement(sql);
				//  ? �ڸ��� Į�� ������ Ÿ�Կ� ����
				// ������ setXXX() �޼��带 ȣ���ؾ��Ѵ�.
				// ���� ���, Į���� char �Ǵ� varchar Ÿ���̸� setString()
				// Į���� TimeStamp Ÿ���̸� setDate(), setTimestamp(),
				// Į���� int Ÿ���̸� setInt()
				pstmt.setString(1, key); // ù ��° �ڸ��� key
				pstmt.setString(2, value); // �� ��° �ڸ��� value, �ڵ��� ������ ��� ����
				
				pstmt.executeUpdate(); // update, insert, delete ���� ������ ��ȯ��
				
				pstmt.setString(1, value);
				pstmt.setString(2, key);
				
				pstmt.executeUpdate(); // update, insert, delete ���� ������ ��ȯ��
				
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(); // ������ �˷��� ? ������ �� �̷��� �ϴ� �͵� ����
		}
	}
	
	public static void main(String[] args) {
		new YSJDirectioryTest();
	}
}
