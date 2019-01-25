import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Bible extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton jbtn1, jbtn2; //�����б�, �˻� ��ư
	JTextArea todaybible; //�����Ǽ��� ����ִ°�
	String[] strTB = new String[10];	// �����ڰ� ������ ���� ���湮�� 10��
	int numTB = 0;						// numberTodayBible�� ����
	goBible gb = new goBible();			// �� ��ü�� �پ��� �˻� ����� �����ϴ� �ϳ��� �˻������� �����ϸ� ����.
	
	   Bible() throws IOException {
	      super("���Ἲ��");
	      setLayout(new FlowLayout());
	      JLabel lb = new JLabel("������ ���汸��");
	      todaybible = new JTextArea(7,20);
	      add(lb);
	      add(todaybible);
	      
	      // �����ڰ� ���� ���� ������ �����ߴ�.
	      strTB[0] = "��_6_37";
	      strTB[1] = "��_10_10";
	      strTB[2] = "��_37_4";
	      strTB[3] = "��_100_5";
	      strTB[4] = "��_8_12";
	      strTB[5] = "��_100_4";
	      strTB[6] = "��_9_1";
	      strTB[7] = "��_3_3";
	      strTB[8] = "��_11_1";
	      strTB[9] = "��_27_17";
	      
	      todaybible.setLineWrap(true);		// �ڵ� �� �ٲ�
	      todaybible.setEditable(false);	// ����ڰ� ���� �Ұ�
	      numTB = (int)(Math.random()*10);	// �������� ������ ���汸���� �����ش�.
	      todaybible.setText("[" + strTB[numTB] + "]\n" + gb.findaBible(strTB[numTB]));	// ������ ���� ���� ���
	      jbtn1 = new JButton("�б�"); 
	      add(jbtn1); 
	      jbtn2 = new JButton("�˻�"); 
	      add(jbtn2);
	      
	      jbtn1.addActionListener(this);
	      jbtn2.addActionListener(this);
	      
	      setSize(300, 300); 
	      setVisible(true); 
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	   } 
	   public void actionPerformed(ActionEvent e) {
	
		   if(e.getSource()==jbtn1) {	   // �б� ��ư Ŭ�� ��
			   try {
				new Bible1();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		   }
		   else if(e.getSource() == jbtn2) {	// �˻� ��ư Ŭ�� ��
			   new Bible2();
		   }
	   }
	   
	   public static void main(String[] args) throws IOException {
		  new Bible();
		  
		  }
}
