/*
 * 20150887 Kang Hyeon Dae
 * 소스 사용시 블로그 출처를 남겨주세요.
*/
// 파일을 만들기 위해 사용한 코드라 더이상 컴파일할 필요가 없다.
import java.io.*;
public class seperateBible {	// 성경을 나눈다는 뜻
	public static void main(String[] args) throws IOException{
		// 읽어들일 파일 경로 지정
		String path = seperateBible.class.getResource("").getPath();	// 절대 경로로 지정
		
		FileReader reader = new FileReader(path + "KoreanBible.txt");	
		int now,slen2=0;
		String s = "";	// 절을 담을 문자열 변수
		String p = "";	// 장:절을 담고 있는 문자열 변수
		String imsi2 = "";
		String imsi3 = "";
		boolean first =true;	// 처음 콜론을 만나는 가
		boolean init = false;	// 초기화를 해야 하는가

		while(true){	// 1번 루프
			now = reader.read();	// 현재 읽고 있는 문자
			
			if(now == -1)	// 파일의 끝이면 탈출
				break;
			
			if(now == ':'){	// 콜론을 만났을 경우
				int slen=s.length();
				slen2 = slen;
				
				// 처음 콜론을 만났을 때
				if(first == false){
					int imsi = slen-1;
					
					// : 기준으로 왼쪽으로 이동했을 때 개행을 만나면 탈출
					while(true){	// 2번 루프
						if(s.charAt(imsi) == '\n' || s.charAt(imsi) == '\r' || imsi == -1)	// 리눅스를 고려
							break;
						imsi--;
					}
				
					imsi2 = s.substring(1, imsi);	// 앞에 공백 한 칸 제거해줌
					System.out.print(imsi2);
				
				}

				p += "_";	// : 대신 _을 넣어줌. 메모장 제목으로 :는 들어갈 수 없어서
				
				// 첫 공백을 만날 때 까지 읽음.
				while(true){
					now = reader.read();
					if(now == ' ') break;
					p += (char)now;
				}
				// 여기까지 하면 파일 제목이 완성 됨.
				boolean underBar = false;
				// 두번 째 이 후로 
					while(true){
					slen--;
					if(s.charAt(slen) != '\n' && s.charAt(slen) != '\r'){
						if(!(s.charAt(slen)-'0' >= 0 && s.charAt(slen)-'0' <= 9) && underBar == false){
							p = "_" + p;
							underBar = true;
						}
						
						p = s.charAt(slen) + p;
						
						if(slen == 0) {
							init = true;
							break;
						}
						
						
					}
					else {	// 개행을 만나면 탈출
						System.out.println(p);
						init =true;
						break;
					}
			
				}
				
					
				/* 
				 * 먼저 값을 구하는건 제목 - 내용 순인데
				 * 데이터 특성상 처음은 제목과 내용이 한 절씩 차이가 나서
				 * 잠시 메모리에 넣어둔 후 첫 절을 다 읽으면 그때부터 파일을 만든다.
				 */
					
				if(init == true){
					if(first == false){
						File bibleFile = new File(path + imsi3 + ".txt");
						imsi3="";
						BufferedWriter writer = new BufferedWriter(new FileWriter(bibleFile));
						writer.write(imsi2);
						init = false;
						imsi3 = p;	// 잠시 메모리에 넣어 둔다.
						s = "";
						p = "";
						writer.close();
					}
					else{
						imsi3 = p;
						p="";
					}
				}
				s = "";
				first = false;
	
			}
			s += (char)now;
		}
		
					
		/*
		 * 한번 씩 밀렸으므로
		 * 마지막 파일은 위의 반복문으로는 생성되지 않는다.
		 * 그래서 마지막 파일은 직접 만들어 줘야 한다.
		 */
		
		s = s.substring(1, s.length());
		System.out.println(s);
		File bibleFile = new File(path + imsi3 + ".txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(bibleFile));
		writer.write(s);
		writer.close();
		reader.close();
		
	}
}
