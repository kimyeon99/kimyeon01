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
	
	String str[] = {"���� �׾���.", "�ѳ� �� ������ ����� ���̸� ���� �˷�.", "�� ���̶� ����. �� ������ ���̿�, �ٽ� �� ��!"};
	
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
        
        JLabel l1 = new JLabel("������ �Ұ����ϳ� ���ο��ϴ� ���� �����̴�.");
        JLabel l2 = new JLabel("-�������-");
        
        JList <String> ni_st = new JList<String>(str);
        ni_st.setBounds(120,150,350,200);
        ni_st.setVisible(false);
        c.add(ni_st);

        
        l1.setBounds(20,0,300,20);
        l2.setBounds(100,25,70,20);
        
        c.add(l1);
        c.add(l2);
        
        JButton btn1 = new JButton("����ο�");
        JButton btn2 = new JButton("��");
        JButton btn3 = new JButton("���");
        JButton btn_ni_1 = new JButton("��ü");

        
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
