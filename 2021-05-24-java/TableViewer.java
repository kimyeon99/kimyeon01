package scpark3;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TableViewer extends JFrame implements ActionListener{

	private JTextField idField, titleField, publisherField, priceField, yearField, dateField;
	private JButton previousBtn, nextBtn, insertBtn, finishBtn;
	private ResultSet rs = null;
	private Connection con = null;
	
	
	public TableViewer() {
		// �� ������Ʈ�� frame�� �߰��ϱ�
		// tableviewer ��ü�� ������ ��, db����
		// ���ڵ���� �о�´�
		// 1. �����ͺ��̽��� ����
		// 2. select ���� �����ϰ� ��ȯ�� resultset ��ü�� ������ �־����.
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/will", "root", "1214");
					
			String sql = "select * from books order by book_id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("�����߻����� ���� ���α׷� ����");
			System.exit(0);
		}
		this.setLayout(new GridLayout(0, 2));
		this.add(new JLabel("ID", JLabel.CENTER));
		idField = new JTextField(10);
		this.add(idField);
		
		this.add(new JLabel("Title", JLabel.CENTER));
		titleField = new JTextField(10);
		this.add(titleField);
		
		this.add(new JLabel("publisher", JLabel.CENTER));
		publisherField = new JTextField(10);
		this.add(publisherField);
		
		this.add(new JLabel("yearr", JLabel.CENTER));
		yearField = new JTextField(10);
		this.add(yearField);
		
		this.add(new JLabel("Price", JLabel.CENTER));
		priceField = new JTextField(10);
		this.add(priceField);
		
		previousBtn = new JButton("Previous");
		previousBtn.addActionListener(this);
		this.add(previousBtn);
		
		nextBtn = new JButton("Next");
		nextBtn.addActionListener(this);
		this.add(nextBtn);
		
		insertBtn = new JButton("Insert");
		insertBtn.addActionListener(this);
		this.add(insertBtn);
		
		finishBtn = new JButton("Finish");
		finishBtn.addActionListener(this);
		this.add(finishBtn);
		
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// this.setUndecorated(true); // ��ܹ� ������
		this.setSize(350, 200);
		this.setResizable(false);
		this.setVisible(true);
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource() == nextBtn || e.getSource() == previousBtn) {
				if(e.getSource() == nextBtn) {
					rs.next();
				} else if(e.getSource() == previousBtn) {
					rs.previous();
				}
				
				/*
				 * ���� ���ڵ��� Į�� ���� �о��
				 * JTextField�� ������ �����Ѵ�.
				 * idField, titleField, publisherField
				 * yearField, priceField 
				 */
				int bookId = rs.getInt("book_id");
				idField.setText(String.valueOf(bookId));
				String title = rs.getString("title");
				titleField.setText(title);
				String publisher = rs.getString("publisher");
				publisherField.setText(publisher);
				Date year = rs.getDate("year");
				yearField.setText(year.toString());
				int price = rs.getInt("price");
				priceField.setText(String.valueOf(price));
				
			} else if(e.getSource() == insertBtn) {
				/*
				 * �̹� db ������ �Ǿ��ְ�
				 * �̹� ���� ������ ������ �ִ� connection ��ü��
				 * insert ����
				 * �̿��� prepare�ϰ�
				 * ���ѵ� PreparedStatement ��ü�� �̿��ؼ�
				 * �����û�� ������ ������.
				 * 
				 * Statement �Ǵ� PreparedStatement ��ü�� ����� �� �ִ�.
				 * �׷���, PreparedStatement ��ü ����� �ǰ��Ѵ�.
				 * ���Ȼ��� ������ ���ؼ��̴�.
				 * SQL Injection ����� ����� ��Ŀ�� ������ ���� �� �ֱ� ����.
				 */
				
				String sql = "insert into books(title, publisher, year, price) values(?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				// ? �ڸ��� �� ���� ���� ������ �Ŀ� ���� ��û�� ������ �������Ѵ�.
				// ?�ڸ��� ���� �����Ҷ�, �����Ǵ� Į���� ������Ÿ�Կ� ���� ������
				// setXXX �޼��带 ȣ���ؾ��Ѵ�.
				// ������� ?�� �����Ǵ� Į���� title�̰� �� ������Ÿ���� varchar�̸�
				// setString�� �ؾ���. �����Ǵ� Į���� ������Ÿ���� int�� ��쿡�� 
				// setInt�� �ؾ���.
				String title = titleField.getText();
				pstmt.setString(1, title);
				
				String publisher = publisherField.getText();
				pstmt.setString(2, publisher);
				
				String year = yearField.getText();
				/*
				 * ���ڿ��κ��� java.util.Date ��ü�� ������ �� �ִ�
				 * SimpleDateFormat ��ü�� �����Ѵ�.
				 * �� ��, date ���� ������ �˷��ش�.
				 */
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// 1�� ���: Date sqlDate = new Date(sdf.parse(year).getTime());
			    // 2�� ���: pstmt.setDate(3, Date.valueOf(year));	
				
				
				// 3�� ���: ���ڿ��� parsing�ؼ� java.util.Date ��ü�� ����
				java.util.Date date = sdf.parse(year);
				// java.util.Date ��ü�κ��� java.util.sql ��ü�� ����
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				// java.sql.Date ��ü ���� 3��° ? �ڸ��� ����
				pstmt.setDate(3, sqlDate);
				
				
				
				String price = priceField.getText();
				pstmt.setInt(4, Integer.parseInt(price)); // Integer.valueOf(price)�� ��
				
				
				// ������ sql���� select ���� ��쿡�� executeQuery �޼��带 ȣ��
				// ������ sql���� insert, delete, �Ǵ� update�� ��쿡,
				// executeUpdate �޼��带 ȣ��
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(this, "��� ����!");	
				reloading();
			} else if(e.getSource() == finishBtn) {
				System.out.println("�ý����� �����մϴ�. . .  .");
				con.close();
				this.dispose();
				System.exit(0);
			}
			
		} catch(Exception err) {
			JOptionPane.showMessageDialog(this, "���� �߻�: [" + err.getMessage() + "]");
			System.out.println(err.getMessage());
		}
		
	}
	
	private void reloading() throws Exception{
		String sql = "select * from books order by book_id desc";
		PreparedStatement pstmt = con.prepareStatement(sql);
		rs.close(); // rs�� ���� �����߱� ������ ������ �ִ� rs�� ��������
					// �����Ѵٴ� �ǹ̿��� close�� �Ѵ�. ���� ��� ������
					// ����ȭ�� ���� ���� ���� ����.
		rs = pstmt.executeQuery();
	}
	
	public static void main(String[] args) {
		new TableViewer();
	}
	
	


}
