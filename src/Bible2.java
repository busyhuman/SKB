import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;
public class Bible2 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
		JTextField s; //검색창
		JTextArea sr; //검색결과 띄어주는곳
		JPanel jp4; //스크롤바 만들기위한 패널
		JButton jbtn5; // 확인버튼
		goBible gb = new goBible();	// 이 객체는 다양한 검색 기능을 제공하는 하나의 검색봇으로 이해하면 쉽다.
		
		void rotateBible(String p) throws IOException{	// KMP 알고리즘을 참고했다. 사용자가 입력한 성경 구절을 찾아준다. 자세한 내용은 goBible.class에.
			FileReader namereader = new FileReader(".\\names.txt");	// 상대 경로로 지정
			String output = "";
			String nope = "검색결과가 없습니다.";	// 검색 결과가 존재 하지 않을 시 출력 문구.
			waitMsg wm = new waitMsg();		// 검색중일 때 띄워주는 창 생성
			
			while(true){
				  String name = "";
				  int noww = -1;
		 		  while(true){
		 			  noww = namereader.read();
		 			  if(noww == -1){
		 				  break;
		 			  }
		 		
			 		  if(noww == '\n' || noww == '\r' || noww == ' '){
			 			  break;
			 		  }
			 		  name += (char)noww;
		 		  }
		 		if(noww != -1){
		 				noww = (char)namereader.read();
		 		}
				String fileName = name;
				String imsi = gb.searchBible(p, fileName);
				if(!imsi.equals("")){
					output += "[" + fileName + "]\n" + imsi + "\n\n";
		//			System.out.println(output);
				}
				 if(noww == -1){
	 				  break;
	 			 }
			}
			if(output.equals(""))
				sr.setText(nope);
			else
				sr.setText(output);
			namereader.close();
			wm.dispose();
		}

	Bible2() {
		super("성경 찾기");
		setLayout(new FlowLayout());
		jp4 = new JPanel();
		JLabel lb1 = new JLabel("검색"); 
		s = new JTextField(20);
		sr = new JTextArea(15,25); 
	    sr.setLineWrap(true);	// 라인 자동 줄바꿈.
	    sr.setEditable(false);	// 사용자가 수정 불가
		jbtn5 = new JButton("확인");
		jbtn5.addActionListener(this);
	    int v2 = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;		//세로 스크롤바 지정
	    int h2 = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER; 	//가로 스크롤바 지정
	    JScrollPane js3 = new JScrollPane(jp4, v2, h2); //패널안에 스크롤바 추가
	      
	    add(lb1); 
	    add(s);
	    add(jbtn5);
	    add(sr);
	    jp4.add(sr); //패널 안에 TextArea 추가함

	    add(js3, BorderLayout.CENTER);
	    gb.putBiblename();
		setSize(400, 400); 
	    setVisible(true); 
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		  if(e.getSource() == jbtn5) {
			  String imsi = s.getText();

			  if(imsi.length() > 1){	// 두 글자 이상 입력해야 검색
					try {
						rotateBible(imsi);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			  } else{					// 두 글자 미만이면 해당 문구 출력
				  sr.setText("두 글자 이상 입력해주세요.");
			  }
		  }
		
	}
	
	
	
}
