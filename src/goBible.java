import java.util.*;
import java.io.*;
public class goBible {
	
	public static Map<String, String> bibleName = new HashMap<>();	// 사용자에게 보여질 이름을 Key로, 실제 텍스트파일의 성경 제목을 Value로.
	public File dirFile = new File(".\\myBible");	// 상대 경로로 지정
	public File[] fileList = dirFile.listFiles();	// 현재 폴더의 파일들
	
	void putBiblename(){	// Map 자료구조에  일대일 맵핑

		if(bibleName.isEmpty()){
			bibleName.put("창세기", "창");
			bibleName.put("출애굽기", "출");
			bibleName.put("레위기", "레");
			bibleName.put("민수기", "민");
			bibleName.put("신명기", "신");
			bibleName.put("여호수아", "수");
			bibleName.put("사사기", "삿");
			bibleName.put("룻기", "룻");
			bibleName.put("사무엘상", "삼상");
			bibleName.put("사무엘하", "삼하");
			bibleName.put("열왕기상", "왕상");
			bibleName.put("열왕기하", "왕하");
			bibleName.put("역대상", "대상");
			bibleName.put("역대하", "대하");
			bibleName.put("에스라", "스");
			bibleName.put("느헤미야", "느");
			bibleName.put("에스더", "에");
			bibleName.put("욥기", "욥");
			bibleName.put("시편", "시");
			bibleName.put("잠언", "잠");
			bibleName.put("전도서", "전");
			bibleName.put("아가", "아");
			bibleName.put("이사야", "사");
			bibleName.put("예레미야", "렘");
			bibleName.put("예레미야애가", "애");
			bibleName.put("에스겔", "겔");
			bibleName.put("다니엘", "단");
			bibleName.put("호세아", "호");
			bibleName.put("요엘", "욜");
			bibleName.put("아모스", "암");
			bibleName.put("오바다", "옵");
			bibleName.put("요나", "욘");
			bibleName.put("미가", "미");
			bibleName.put("나훔", "나");
			bibleName.put("하박국", "합");
			bibleName.put("스바냐", "습");
			bibleName.put("학개", "학");
			bibleName.put("스가랴", "슥");
			bibleName.put("말라기", "말");
			bibleName.put("마태복음", "마");
			bibleName.put("마가복음", "막");
			bibleName.put("누가복음", "눅");
			bibleName.put("요한복음", "요");
			bibleName.put("사도행전", "행");
			bibleName.put("로마서", "롬");
			bibleName.put("고린도전서", "고전");
			bibleName.put("고린도후서", "고후");
			bibleName.put("갈라디아서", "갈");
			bibleName.put("에베소서", "엡");
			bibleName.put("빌립보서", "빌");
			bibleName.put("골로새서", "골");
			bibleName.put("데살로니가전서", "살전");
			bibleName.put("데살로니가후서", "살후");
			bibleName.put("디모데전서", "딤전");
			bibleName.put("디모데후서", "딤후");
			bibleName.put("디도서", "딛");
			bibleName.put("빌레몬서", "몬");
			bibleName.put("히브리서", "히");
			bibleName.put("야고보서", "약");
			bibleName.put("베드로전서", "벧전");
			bibleName.put("베드로후서", "벧후");
			bibleName.put("요한1서", "요일");
			bibleName.put("요한2서", "요이");
			bibleName.put("요한3서", "요삼");
			bibleName.put("유다서", "유");
			bibleName.put("요한계시록", "계");
		}
	}
	
	public String findaBible(String s2find) throws IOException{	// 성경 구절을 찾는다.
		 FileReader namereader = new FileReader(".\\names.txt");
		 int noww;

		String s2show = "", fileName = "";	// 초기화
		boolean esc = false;				// 탈출 조건. 성경 구절을 찾으면 true로 바꿔줌.
		if(bibleName.isEmpty()){	// Map이 Null이면 안되기에 검사 해줌. 비어있다면 맵핑해줌.
			putBiblename();
		}	

		// 모든 성경파일들을 돌면서 해당하는 성경구절을 찾는다.
		while(true){
		
			  String name = "";
	 		  while(true){
	 			  noww = namereader.read();
	 			  if(noww == -1){
	 				  break;
	 			  }
	 	
		 		  if(noww == '\n' || noww == '\r'){
		
		 			  break;
		 		  }
				  name += (char)noww;
	 		  }
			fileName = name;
			if(s2find.equals(fileName)){
				int now;
				FileReader reader = new FileReader(dirFile + "\\" + fileName + ".txt");

				while(true){	// 1번 루프
					now = reader.read();
					if(now == -1){
						esc = true;
						break;
					}
					s2show += (char)now;
				}
				reader.close();
			}
			if(esc == true) break;
		}
	//	System.out.println(s2show);
		namereader.close();
		return s2show;
	}

	int makeInteger(String s){	// 문자를 숫자로 변환한다.
		int dan=1,su=0;
		int len = s.length();
		
		// 문자 하나하나를 아스키 코드를 이용해 숫자로 만들어주고 1, 10, 100 .... 을 곱하여 알맞는 수로 바꾸어 준다.
		for(int i=len-1;i>=0;i--){
			su += (s.charAt(i) -'0') * dan;
			dan *= 10;
		}
		return su;
	}
	
	String findMaxJang(String jcmName) throws IOException{	// 최대 장을 검색한다.
		 FileReader jangreader = new FileReader(".\\countJang.txt");	// 상대 경로로 지정.

		 int now;
	 	  while(true){
		 	  String Jang = "";
	 		  while(true){
	 			  now = jangreader.read();
	
	 			  Jang += (char)now;
		 		  if(now == '\n' || now == '\r' || now == -1){	
		
		 			  break;
		 		  }
	 		  }

	 		  int len = Jang.length();
	 		  String Jang2 = "";
	 		  for (int i=0;i<len;i++){
				  char ji = Jang.charAt(i);
	 			  if(ji == '_'){
	 		 		  if(Jang2.equals(jcmName)){
	 		 			  String imsi = "";

	 		 			  for(int j=i+1;j<len;j++){
		 		 			  char jj = Jang.charAt(j);
		
	 		 				  if(jj == '\n' || jj == '\r' || jj == -1){
	 		 					  jangreader.close();
	 		 					  return imsi;
	 		 				  }
	 		 				  imsi += jj;
	 		 			  }
	 		 		  }
	 				  break;
				  }	
	 			  Jang2 += ji;
	 		  }

	 	  }
	}

	String findMaxJeol(String jcmName) throws IOException{	// 최대 절을 검색한다.
		 FileReader jeolreader = new FileReader(".\\countJeol.txt");	// 상대 경로로 지정.
		 int now;
	 	  while(true){
		 	  String Jeol = "";
	 		  while(true){
	 			  now = jeolreader.read();
	
	 			  Jeol += (char)now;
		 		  if(now == '\n' || now == '\r' || now == -1){
		
		 			  break;
		 		  }
	 		  }
	 		 
	 		  int len = Jeol.length();
	 		  boolean under_bar=false;
	 		  String Jeol2 = "";
	 		  for (int i=0;i<len;i++){
				  char ji = Jeol.charAt(i);
	 			  if(ji == '_'){
	 				  if(under_bar == false){
	 		 			  Jeol2 += ji;
	 		 			  under_bar = true;
	 		 			  continue;
	 				  }
	 				  
	 		 		  if(Jeol2.equals(jcmName)){
	 		 			  String imsi = "";
	 		
	 		 			  for(int j=i+1;j<len;j++){
		 		 			  char jj = Jeol.charAt(j);
	
	 		 				  if(jj == '\n' || jj == '\r' || jj == -1){
	 		 					  jeolreader.close();
	 		 					  return imsi;
	 		 				  }
	 		 				  imsi += jj;
	 		 			  }
	 		 		  }
	 		 		  
	 				  break;
				  }	
	 			  Jeol2 += ji;

	 		  }

	 	  }
	}
	
	String searchBible(String p, String fileName) throws IOException{	// KMP 알고리즘을 참고했다. 사용자가 입력한 성경 구절을 찾아준다. p는 찾고자 하는 문장
		String s = "";
		fileName += ".txt";
		FileReader reader = new FileReader(dirFile + "\\" + fileName);	// 상대 경로로 지정.
		
	    int m = p.length(),idx;
		boolean check = false;
	    int now;
	    // 성경 내용을 받아온다.
	    while(true){
	    	now = reader.read();
	    	if(now == -1) break;
	    	s += (char)now;
	    }
	    int n = s.length();
	    String p2 = "";	// p2는 찾고자 하는 문장에서 한 단어
	    
	    /*
	     * 먼저 찾고자 하는 단어의 1부터 단어의 길이까지 각각의 접두어와 접미어가 일치하는 최대의 길이를 구한다.
	     * 그 다음부터 성경 파일 하나하나를 돌면서 성경의 구절과 비교해 나간다.
	     * 문자가 일치하면 다음 문자를 비교하고 불일치하면 이전 문자 길이의 최대 일치했던 것과 비교한다.
	     * 이렇게 해서 최대 길이에서 일치하면 찾은 것이며,그렇지 않다면 다음 단어로 넘어가는데
	     * 이 때 찾고자 하는 단어의 바로 다음 위치부터 찾는다. 즉 단어의 길이 + 1
	     * n = (성경 구절 길이), m = (찾고자 하는 단어)라 했을 때
	     * O(n+m)의 시간안에 찾을 수 있다.
	     */
	    
	    for(int k=0;k<m;k++){
	    	if(p.charAt(k) == ' ' || k == m-1){
	    		if(p2.equals("")) continue;
	    		if(k == m-1){
	    			p2 += p.charAt(k);
	    		}
	    		int m2 = p2.length();

	    		check = false;
	    		Vector<Integer> pi = new Vector<>();
	    	    pi.setSize(m2);
	    	    for(int j = 0;j<m2;j++){
	    	    	pi.setElementAt(0, j);
	    	    }
	    	    
	    	    idx = 0;
	    	    for(int i=1;i<m2;i++){
	    	    	while(idx>0 && p2.charAt(i) != p2.charAt(idx)){
	    	    		idx = pi.get(idx-1);   
	    	    	}
	    	    	
	    	        if(p2.charAt(i) == p2.charAt(idx)){
	    	            pi.setElementAt(++idx, i);
	    	        }
	    	   
	    	    }
	    		idx = 0;

	    	    for(int i=0;i<n;i++){
	    	        while(idx>0 && s.charAt(i) != p2.charAt(idx)){
	    	            idx = pi.get(idx-1);   
	    	        }
	    	        
	    	        if(s.charAt(i) == p2.charAt(idx)){
	    	        	
	    	            if(idx == m2-1){
	    	            	check = true;
	    	                break;
	    	            }
	    	            else{
	    	                idx++;
	    	            }
	    	        }
	    	        
	    	    }

	    	    if(check == false) {
	    	    	reader.close();
	    	    	return "";
	    	    }
	    	    
	    	    p2 = "";
	    		continue;
	    	}
	    	
	    	p2 += p.charAt(k);
	    }
	    
	    reader.close();
	    if(check == false) return "";	// 찾은 게 없음

	    return s;	// 찾은 게 있음
	}
	
}
