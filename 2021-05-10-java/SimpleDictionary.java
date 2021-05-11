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

	private JTextField inputField = new JTextField(30); // 입력 필드
	private JButton searchBtn = new JButton("검색");
	private JButton addBtn = new JButton("추가");
	
	// 한영 사전 : 한글단어와 대응되는 영어단어의 쌍을 저장
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
			// writer = 문자열로 준다
			String str = key + "=" + value + "\n";
			fWriter.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void buildDictionaryFromFile(){
		//Properties는 Map의 입출력 가능하게 해줌
		//이게 없었으면 한 줄씩 잘라서 받았어야 함.
		Properties props = new Properties();
		// exception 발생할 수 있으니 try/catch 필수
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
			// inputfield에 입력된 단어를 추출
			// dict 맵 객체에서 그 단어에 대응되는 영어단어를 찾아 디스플레이
			String value = dict.get(key);
			if(value == null) {
				JOptionPane.showMessageDialog(this, "단어 못 찾음.", key, JOptionPane.ERROR_MESSAGE);
				
			} else {
				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(e.getSource() == addBtn) {
			// inputfield에 입력된 단어를 추출
			// 그 단어에 대응되는 영어단어를 입력받고
			// dict 맵 객체에 <한글단어, 영어단어> 추가,
			String value = JOptionPane.showInputDialog(this, key + "에 대응되는 단어 입력", JOptionPane.INFORMATION_MESSAGE);
			if(value == null || value.trim().length() == 0) return;
			dict.put(key, value);
			JOptionPane.showInputDialog(this, "추가되었습니다.","성공");
		}
		inputField.requestFocus();
	}
	
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new SimpleDictionary());
		frame.setTitle("한영사전");
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
