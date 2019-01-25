import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Bible extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton jbtn1, jbtn2; //성경읽기, 검색 버튼
	JTextArea todaybible; //오늘의성경 띄워주는곳
	String[] strTB = new String[10];	// 개발자가 선정한 좋은 선경문구 10개
	int numTB = 0;						// numberTodayBible의 약자
	goBible gb = new goBible();			// 이 객체는 다양한 검색 기능을 제공하는 하나의 검색봇으로 이해하면 쉽다.
	
	   Bible() throws IOException {
	      super("성결성경");
	      setLayout(new FlowLayout());
	      JLabel lb = new JLabel("오늘의 성경구절");
	      todaybible = new JTextArea(7,20);
	      add(lb);
	      add(todaybible);
	      
	      // 개발자가 직접 좋은 구절을 선별했다.
	      strTB[0] = "눅_6_37";
	      strTB[1] = "롬_10_10";
	      strTB[2] = "시_37_4";
	      strTB[3] = "시_100_5";
	      strTB[4] = "요_8_12";
	      strTB[5] = "시_100_4";
	      strTB[6] = "시_9_1";
	      strTB[7] = "시_3_3";
	      strTB[8] = "히_11_1";
	      strTB[9] = "잠_27_17";
	      
	      todaybible.setLineWrap(true);		// 자동 줄 바꿈
	      todaybible.setEditable(false);	// 사용자가 수정 불가
	      numTB = (int)(Math.random()*10);	// 랜덤으로 오늘의 성경구절을 보여준다.
	      todaybible.setText("[" + strTB[numTB] + "]\n" + gb.findaBible(strTB[numTB]));	// 오늘의 성경 구절 출력
	      jbtn1 = new JButton("읽기"); 
	      add(jbtn1); 
	      jbtn2 = new JButton("검색"); 
	      add(jbtn2);
	      
	      jbtn1.addActionListener(this);
	      jbtn2.addActionListener(this);
	      
	      setSize(300, 300); 
	      setVisible(true); 
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	   } 
	   public void actionPerformed(ActionEvent e) {
	
		   if(e.getSource()==jbtn1) {	   // 읽기 버튼 클릭 시
			   try {
				new Bible1();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		   }
		   else if(e.getSource() == jbtn2) {	// 검색 버튼 클릭 시
			   new Bible2();
		   }
	   }
	   
	   public static void main(String[] args) throws IOException {
		  new Bible();
		  
		  }
}
