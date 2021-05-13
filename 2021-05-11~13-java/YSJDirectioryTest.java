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
	private JButton aBtn = new JButton("검색");
	private JButton bBtn = new JButton("추가");
	private JTextField field = new JTextField(30);
	private static final String DIC_FILE_NAME = "dict.props";


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
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == aBtn) { // aBtn 클릭 (검색)
			if(map.get(field.getText()) == null) { // 검색하려는 내용이 없을 때
				JOptionPane.showMessageDialog(this, "단어가 없습니다.", 
						field.getText(), JOptionPane.INFORMATION_MESSAGE);
			} else { // 검색하려는 내용이 있을 때
				JOptionPane.showMessageDialog(this , map.get(field.getText()),
						field.getText(), JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(e.getSource() == bBtn) { // bBtn 클릭 (추가)
			String value = JOptionPane.showInputDialog(this,  "추가하려는 단어 입력\n" +field.getText() + ": " ,
					"단어 추가", JOptionPane.INFORMATION_MESSAGE);
			if(value == null || value.trim().length() == 0) {
					return;
			}
			map.put(field.getText(), value);
			JOptionPane.showMessageDialog(this, "추가 성공");
			addWord();
		}
	}
	
	public void addWord() {
		// 단어 추가는 맵에다가 추가하는 것이 아니라
		// FileWriter를 이용하여 dict.props에 단어를 추가한다!!!!
		
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
				map.put((String) key, (String) value);
			}
	}
	
	public static void main(String[] args) {
		new YSJDirectioryTest();
	}
}
