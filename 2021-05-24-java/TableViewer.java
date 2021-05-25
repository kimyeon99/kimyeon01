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
		// 위 컴포넌트를 frame에 추가하기
		// tableviewer 객체가 생성될 때, db에서
		// 레코드들을 읽어온다
		// 1. 데이터베이스와 연결
		// 2. select 문을 실행하고 반환된 resultset 객체를 가지고 있어야함.
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/will", "root", "1214");
					
			String sql = "select * from books order by book_id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("문제발생으로 인한 프로그램 종료");
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
		// this.setUndecorated(true); // 상단바 없애줌
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
				 * 현재 레코드의 칼럼 값을 읽어와
				 * JTextField의 값으로 설정한다.
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
				 * 이미 db 연결은 되어있고
				 * 이미 연결 정보를 가지고 있는 connection 객체를
				 * insert 문을
				 * 이용해 prepare하고
				 * 반한된 PreparedStatement 객체를 이용해서
				 * 실행요청을 서버에 보낸다.
				 * 
				 * Statement 또는 PreparedStatement 객체를 사용할 수 있다.
				 * 그런데, PreparedStatement 객체 사용을 권고한다.
				 * 보안상의 이유로 인해서이다.
				 * SQL Injection 방법을 사용한 해커의 공격을 받을 수 있기 때문.
				 */
				
				String sql = "insert into books(title, publisher, year, price) values(?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				// ? 자리에 들어갈 값을 먼저 설정한 후에 실행 요청을 서버에 보내야한다.
				// ?자리에 값을 설정할때, 대응되는 칼럼의 데이터타입에 따라 적절한
				// setXXX 메서드를 호출해야한다.
				// 예를들어 ?에 대응되는 칼럼이 title이고 그 데이터타입이 varchar이면
				// setString을 해야함. 대응되는 칼럼의 데이터타입이 int일 경우에는 
				// setInt를 해야함.
				String title = titleField.getText();
				pstmt.setString(1, title);
				
				String publisher = publisherField.getText();
				pstmt.setString(2, publisher);
				
				String year = yearField.getText();
				/*
				 * 문자열로부터 java.util.Date 객체를 생성할 수 있는
				 * SimpleDateFormat 객체를 생성한다.
				 * 이 때, date 값을 포맷을 알려준다.
				 */
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// 1번 방법: Date sqlDate = new Date(sdf.parse(year).getTime());
			    // 2번 방법: pstmt.setDate(3, Date.valueOf(year));	
				
				
				// 3번 방법: 문자열을 parsing해서 java.util.Date 객체를 생성
				java.util.Date date = sdf.parse(year);
				// java.util.Date 객체로부터 java.util.sql 객체를 생성
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				// java.sql.Date 객체 값을 3번째 ? 자리에 설정
				pstmt.setDate(3, sqlDate);
				
				
				
				String price = priceField.getText();
				pstmt.setInt(4, Integer.parseInt(price)); // Integer.valueOf(price)도 됨
				
				
				// 실행할 sql문이 select 문인 경우에는 executeQuery 메서드를 호출
				// 실행할 sql문이 insert, delete, 또는 update일 경우에,
				// executeUpdate 메서드를 호출
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(this, "등록 성공!");	
				reloading();
			} else if(e.getSource() == finishBtn) {
				System.out.println("시스템을 종료합니다. . .  .");
				con.close();
				this.dispose();
				System.exit(0);
			}
			
		} catch(Exception err) {
			JOptionPane.showMessageDialog(this, "오류 발생: [" + err.getMessage() + "]");
			System.out.println(err.getMessage());
		}
		
	}
	
	private void reloading() throws Exception{
		String sql = "select * from books order by book_id desc";
		PreparedStatement pstmt = con.prepareStatement(sql);
		rs.close(); // rs를 새로 생성했기 때문에 기존에 있는 rs의 정보들을
					// 삭제한다는 의미에서 close를 한다. 물론 없어도 되지만
					// 최적화를 위해 적는 것이 좋다.
		rs = pstmt.executeQuery();
	}
	
	public static void main(String[] args) {
		new TableViewer();
	}
	
	


}
