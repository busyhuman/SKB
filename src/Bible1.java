import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
public class Bible1 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JLabel jlb3, jlb4, jlb5; // 성경 장 절 항목 표시
	JButton jbtn3, jbtn4;//확인 버튼, 처음화면 버튼
	JTextArea ta; //성경구절 출력되는곳
	String[] ar1 = new String[66];
    Vector<Integer> ar2 = new Vector<>();
    Vector<Integer> ar3 = new Vector<>();
	JComboBox<String> jcm1;		// 성경 장 절 콤보박스
	JComboBox<Integer> jcm2;		// 성경 장 절 콤보박스
	JComboBox<Integer> jcm3;		// 성경 장 절 콤보박스
	goBible gb = new goBible();		// 이 객체는 다양한 검색 기능을 제공하는 하나의 검색봇으로 이해하면 쉽다.
	
	   Bible1() throws IOException  {
		  super("성결 읽기");
	      setLayout(null); //레이아웃 매니저 삭제(x y 좌표로 위치 조정할수 있음  , 가로세로 크기도 조절가능)
	      jlb3 = new JLabel("성경");
	      jlb4 = new JLabel("장");
	      jlb5 = new JLabel("절");
	      jlb3.setBounds(50,30,100,30); // 위치랑 크기 조절(x좌표,y좌표,가로크기,세로크기)
	      jlb4.setBounds(150,30,100,30);
	      jlb5.setBounds(250,30,100,30);
	      add(jlb3); add(jlb4); add(jlb5);
	      
	      // 배열에 사용자에게 보여질 성경 제목을 넣어줌
	      ar1[0] = "창세기"; ar1[1] = "출애굽기"; ar1[2] = "레위기"; ar1[3] = "민수기";
	      ar1[4] = "신명기"; ar1[5] = "여호수아"; ar1[6] = "사사기"; ar1[7] = "룻기";
	      ar1[8] = "사무엘상"; ar1[9] = "사무엘하"; ar1[10] = "열왕기상"; ar1[11] = "열왕기하";
	      ar1[12] = "역대상"; ar1[13] = "역대하"; ar1[14] = "에스라"; ar1[15] = "느헤미야";
	      ar1[16] = "에스더"; ar1[17] = "욥기"; ar1[18] = "시편"; ar1[19] = "잠언";
	      ar1[20] = "전도서"; ar1[21] = "아가"; ar1[22] = "이사야"; ar1[23] = "예레미야"; 
	      ar1[24] = "예레미야애가"; ar1[25] = "에스겔"; ar1[26] = "다니엘"; ar1[27] = "호세아";
	      ar1[28] = "요엘"; ar1[29] = "아모스"; ar1[30] = "오바다"; ar1[31] = "요나";
	      ar1[32] = "미가"; ar1[33] = "나훔"; ar1[34] = "하박국"; ar1[35] = "스바냐"; 
	      ar1[36] = "학개";
	      ar1[37] = "스가랴";
	      ar1[38] = "말라기";
	      ar1[39] = "마태복음";
	      ar1[40] = "마가복음";
	      ar1[41] = "누가복음";
	      ar1[42] = "요한복음";
	      ar1[43] = "사도행전";
	      ar1[44] = "로마서";
	      ar1[45] = "고린도전서";
	      ar1[46] = "고린도후서";
	      ar1[47] = "갈라디아서";
	      ar1[48] = "에베소서";
	      ar1[49] = "빌립보서";
	      ar1[50] = "골로새서";
	      ar1[51] = "데살로니가전서";
	      ar1[52] = "데살로니가후서";
	      ar1[53] = "디모데전서";
	      ar1[54] = "디모데후서";
	      ar1[55] = "디도서";
	      ar1[56] = "빌레몬서";
	      ar1[57] = "히브리서";
	      ar1[58] = "야고보서";
	      ar1[59] = "베드로전서";
	      ar1[60] = "베드로후서";
	      ar1[61] = "요한1서";
	      ar1[62] = "요한2서";
	      ar1[63] = "요한3서";
	      ar1[64] = "유다서";
	      ar1[65] = "요한계시록";

	 	  jcm1 = new JComboBox<>(ar1);		// 성경 콤보박스
	 	  jcm2 = new JComboBox<>(ar2);		// 장 콤보박스
	 	  jcm3 = new JComboBox<>(ar3);		// 절 콤보박스

	      jcm1.setBounds(20,60,100,30);
	      jcm2.setBounds(120,60,100,30);  //콤보박스
	      jcm3.setBounds(220,60,100,30);
	      jcm1.addActionListener(this);
	      jcm2.addActionListener(this);
	      // jcm3는 리스너를 추가해줄 필요가 없다.
	      
	      add(jcm1);
	      add(jcm2);
	      add(jcm3);
	      
	      jbtn3 = new JButton("확인");
	      jbtn3.setBounds(200, 200, 60, 30); //확인버튼
	      add(jbtn3);
	      jbtn3.addActionListener(this);
	      ta = new JTextArea(5,10);
	      ta.setBounds(20,240,300,110); //구절 띄어주는곳
	      ta.setLineWrap(true);
	      ta.setEditable(false);
	      add(ta);
	      gb.putBiblename();

	      String jangStr = gb.findMaxJang(goBible.bibleName.get(jcm1.getSelectedItem()));
		  int jangInt = gb.makeInteger(jangStr);
		  ar2.clear();
		  for(int i=1;i<=jangInt;i++){	// 해당 성경 제목의 최대 장만큼 생성
			  ar2.add(i);
		  }	      
	      String jeolStr = gb.findMaxJeol(goBible.bibleName.get(jcm1.getSelectedItem())+ "_" + jangStr);
	      int jeolInt = gb.makeInteger(jeolStr);
	      ar3.clear();
	      for(int i=1;i<=jeolInt;i++){	// 해당 성경 제목, 장의 최대 절만큼 생성
	    	  ar3.add(i);
	      }	    

	      jcm2.setSelectedIndex(0);	// 1장
	      jcm3.setSelectedIndex(0);	// 1절
	
	      setSize(350, 420); 
	      setVisible(true); 
	   } 
	   
	   public void actionPerformed(ActionEvent e){

		   if(e.getSource() == jbtn3) {
			   
			   try {
				//   System.out.println(gb.findaBible(jcm1.getSelectedItem().toString() + "_" + jcm2.getName() + "_" + jcm3.getName())); 
				ta.setText(gb.findaBible(goBible.bibleName.get(jcm1.getSelectedItem()) + "_" + jcm2.getSelectedItem() + "_" + jcm3.getSelectedItem()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		  
			   // 성경 제목을 골랐을 때
		   } else if(e.getSource() == jcm1){
			   String jangStr = "";
			try {
				jangStr = gb.findMaxJang(goBible.bibleName.get(jcm1.getSelectedItem()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
				  int jangInt = gb.makeInteger(jangStr);
				  ar2.clear();
			      jcm2.removeAllItems();
				  for(int i=1;i<=jangInt;i++){
					  ar2.addElement(i);
				  }	      
				  jcm2.setSelectedIndex(0);
				
			      String jeolStr = "";
				try {
					jeolStr = gb.findMaxJeol(goBible.bibleName.get(jcm1.getSelectedItem())+ "_" + jcm2.getSelectedItem());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			      int jeolInt = gb.makeInteger(jeolStr);
			     
			      ar3.clear();
			      jcm3.removeAllItems();
			      for(int i=1;i<=jeolInt;i++){
			    	  ar3.addElement(i);
			      }	    
			      jcm3.setSelectedIndex(0);

			  	// 장을 골랐을 때
		   } else if(e.getSource() == jcm2){
			   String jeolStr = "";
				try {
					jeolStr = gb.findMaxJeol(goBible.bibleName.get(jcm1.getSelectedItem())+ "_" + jcm2.getSelectedItem());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			      int jeolInt = gb.makeInteger(jeolStr);
			      ar3.clear();
			      jcm3.removeAllItems();
			      for(int i=1;i<=jeolInt;i++){
			    	  ar3.addElement(i);
			      }	    
			      jcm3.setSelectedIndex(0);
		   }
		   
		   
	   }

}
