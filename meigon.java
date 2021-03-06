package program_meigon;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class meigon extends JFrame {
	Container c;
	JPanel panel;
	
	String str[] = {"신은 죽었다.", "한낱 빛 따위가 어둠의 깊이를 어찌 알랴.", "몇 번이라도 좋다. 이 끔찍한 삶이여, 다시 한 번!"};
	
	public meigon() {
		  JFrame frame = new JFrame();
          setTitle("Meigon");
          setSize(500,400);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setButton();
          setVisible(true);
          

	}
	
	void setButton() {
        c = getContentPane();
        
        panel = new JPanel();
        panel.setBackground(Color.cyan);
        
        JLabel l1 = new JLabel("고통은 불가피하나 괴로워하는 것은 선택이다.");
        JLabel l2 = new JLabel("-석가모니-");
        
        JList <String> ni_st = new JList<String>(str);
        ni_st.setBounds(120,150,350,200);
        ni_st.setVisible(false);
        c.add(ni_st);

        
        l1.setBounds(20,0,300,20);
        l2.setBounds(100,25,70,20);
        
        c.add(l1);
        c.add(l2);
        
        JButton btn1 = new JButton("동기부여");
        JButton btn2 = new JButton("돈");
        JButton btn3 = new JButton("사랑");
        JButton btn_ni_1 = new JButton("니체");

        
        btn1.setBounds(10,150,90,35);
        btn2.setBounds(10,200,70,35);
        btn3.setBounds(10,250,70,35);
        btn_ni_1.setBounds(10,300,70,35);

        
        btn_ni_1.addActionListener(event -> {
        	ni_st.setVisible(true);
        });
        
        c.add(btn1);
        c.add(btn2);
        c.add(btn3);
        c.add(btn_ni_1);


        
        //JComboBox <String> humanList = new JComboBox<String>();
        //for(int i = 0; i < str.length; i++) {
        //	humanList.addItem(str[i]);
        //}
        //humanList.setBounds(10,100,80,30);
        //c.add(humanList);
        

        
        JTextArea t1 = new JTextArea(5, 20);
        JTextField t2 = new JTextField(10);
        

        

        c.add(panel);        



		
	}
	



}
