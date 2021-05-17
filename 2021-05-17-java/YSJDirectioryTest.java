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
	private JButton aBtn = new JButton("검색");
	private JButton bBtn = new JButton("추가");
	private JTextField field = new JTextField(30);
	private static final String DIC_FILE_NAME = "dict.props";

	//DB 연결에 필요한 준비물
	private static final String JDBC_CLASS_NAME = "org.mariadb.jdbc.Driver";
	private static final String DB_SERVER_URL = "jdbc:mariadb://localhost:3306/oop";
	private static final String DB_USER= "root";
	private static final String DB_USER_PW= "1214";

	public YSJDirectioryTest() {
		this.setTitle("YSJ DirectioryTest");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.add(field);
		field.setText("단어를 입력하세요.");
		
		this.add(aBtn);
		aBtn.addActionListener(this);

		this.add(bBtn);
		bBtn.addActionListener(this);
		
		this.setLayout(new FlowLayout());
		//this.setPreferredSize(new Dimension(600, 50));// 오류

		this.pack();
		this.setVisible(true);
		
		// 파일 map으로 읽어오기
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
		
		if(e.getSource() == aBtn) { // aBtn 클릭 (검색)
			if(dict.get(field.getText()) == null) { // 검색하려는 내용이 없을 때
				JOptionPane.showMessageDialog(this, "단어가 없습니다.", 
						field.getText(), JOptionPane.INFORMATION_MESSAGE);
			} else { // 검색하려는 내용이 있을 때
				JOptionPane.showMessageDialog(this , dict.get(field.getText()),
						field.getText(), JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(e.getSource() == bBtn) { // bBtn 클릭 (추가)
			String value = JOptionPane.showInputDialog(this,  "추가하려는 단어 입력\n" +field.getText() + ": " ,
					"단어 추가", JOptionPane.INFORMATION_MESSAGE);
			if(value == null || value.trim().length() == 0) {
					return;
			}
			dict.put(field.getText(), value);
			JOptionPane.showMessageDialog(this, "추가 성공");
			addWordToDB(field.getText(), dict.get(field.getText()));
		}
	}
	
	public void addWord() {
		// 단어 추가는 맵에다가 추가하는 것이 아니라
		// FileWriter를 이용하여 dict.props에 단어를 추가한다!!!!
		
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
		// windows의 INI와 같은 기능
		// DB에 대한 연결정보를 파일로 저장해놓고 사용하는 용도
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
		
		try(Connection con = //DB 서버에 연결 
				DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)) 
		{
			// my sql JDBC 드라이버를 메모리에 적재
			// 드라이버 클래스이름은 dbms마다 다르다.
			
			
			// select 문 실행
			String sql = "select * from dict";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				// 현재 포인터가 가르키는 칼럼 값을 빼오면 됨.
				// 각 컬럼의 타입에 따라서, 호출할 메서드가 달라진다.
				// 예를 들어서 char, varchar 타입의 칼럼은
				// getString("칼럼이름", 또는 "칼럼 위치);
				// int 타입의 칼럼은 getInt(...);
				// DateTime, Date 타입의 칼럼 같은
				// getDate();
				// rs.getString("han");
				String han = rs.getString(1);
				// rs.getString("eng");
				String eng = rs.getString(2);
				dict.put(han,eng);
				dict.put(eng, han); // 쌍방향 검색이 가능하도록 함
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
		 * 드라이버를 메모리리에 적재한다 <- 메모리 적재는 한 번만 하면 되고,
		 * 이미 생성자에서 했다.
		 * DB에 연결해서 connection 객체를 반환받는다.
		 * connection 객체에게 PreparedStatement 객체를 요청한다.
		 * PreparedStatement 객체의 executeUpdate() 메서드를
		 * 호출해서 DB에 저장한다.
		 */
		
		try (Connection con = 
				// con.setAutoCommit(false); // 이제 con.commit()을 해야 커밋이 됨. 안정성 올라감
				
				DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)){
				String sql = "insert into dict values(?, ?)"; 	// ?는 placeholder임.
				String sql2 = "update deposit set amount = amount - 100 where name = '홍길동' ";
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				pstmt2.executeUpdate();
				// 이 사이 벼락이 쳐서 db서버가 shutdown 됨
				sql2 = "update deposit set amount = amount - 100 where name = '일지매' ";
				
				/*
				 * 실행 준비
				 * 1. 문법 검사
				 * 2. 정당성 검사(테이블, 칼럼 등이 실제로 있는지, 있다면 사용자가
				 *    레코드를 삽입할 권한이 있는지 등등을 체크)
				 * 3. 실행계획을 세운다. (execution plan)
				 */
				PreparedStatement pstmt = con.prepareStatement(sql);
				//  ? 자리의 칼럼 데이터 타입에 따라
				// 적절한 setXXX() 메서드를 호출해야한다.
				// 예를 들어, 칼럼이 char 또는 varchar 타입이면 setString()
				// 칼럼이 TimeStamp 타입이면 setDate(), setTimestamp(),
				// 칼럼이 int 타입이면 setInt()
				pstmt.setString(1, key); // 첫 번째 자리에 key
				pstmt.setString(2, value); // 두 번째 자리에 value, 코드의 순서는 상관 없음
				
				pstmt.executeUpdate(); // update, insert, delete 등의 개수를 반환함
				
				pstmt.setString(1, value);
				pstmt.setString(2, key);
				
				pstmt.executeUpdate(); // update, insert, delete 등의 개수를 반환함
				
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(); // 빨갛게 알려줌 ? 개발할 땐 이렇게 하는 것도 괜찮
		}
	}
	
	public static void main(String[] args) {
		new YSJDirectioryTest();
	}
}
