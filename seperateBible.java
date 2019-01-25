/*
 * 20150887 Kang Hyeon Dae
 * �ҽ� ���� ��α� ��ó�� �����ּ���.
*/
// ������ ����� ���� ����� �ڵ�� ���̻� �������� �ʿ䰡 ����.
import java.io.*;
public class seperateBible {	// ������ �����ٴ� ��
	public static void main(String[] args) throws IOException{
		// �о���� ���� ��� ����
		String path = seperateBible.class.getResource("").getPath();	// ���� ��η� ����
		
		FileReader reader = new FileReader(path + "KoreanBible.txt");	
		int now,slen2=0;
		String s = "";	// ���� ���� ���ڿ� ����
		String p = "";	// ��:���� ��� �ִ� ���ڿ� ����
		String imsi2 = "";
		String imsi3 = "";
		boolean first =true;	// ó�� �ݷ��� ������ ��
		boolean init = false;	// �ʱ�ȭ�� �ؾ� �ϴ°�

		while(true){	// 1�� ����
			now = reader.read();	// ���� �а� �ִ� ����
			
			if(now == -1)	// ������ ���̸� Ż��
				break;
			
			if(now == ':'){	// �ݷ��� ������ ���
				int slen=s.length();
				slen2 = slen;
				
				// ó�� �ݷ��� ������ ��
				if(first == false){
					int imsi = slen-1;
					
					// : �������� �������� �̵����� �� ������ ������ Ż��
					while(true){	// 2�� ����
						if(s.charAt(imsi) == '\n' || s.charAt(imsi) == '\r' || imsi == -1)	// �������� ���
							break;
						imsi--;
					}
				
					imsi2 = s.substring(1, imsi);	// �տ� ���� �� ĭ ��������
					System.out.print(imsi2);
				
				}

				p += "_";	// : ��� _�� �־���. �޸��� �������� :�� �� �� ���
				
				// ù ������ ���� �� ���� ����.
				while(true){
					now = reader.read();
					if(now == ' ') break;
					p += (char)now;
				}
				// ������� �ϸ� ���� ������ �ϼ� ��.
				boolean underBar = false;
				// �ι� ° �� �ķ� 
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
					else {	// ������ ������ Ż��
						System.out.println(p);
						init =true;
						break;
					}
			
				}
				
					
				/* 
				 * ���� ���� ���ϴ°� ���� - ���� ���ε�
				 * ������ Ư���� ó���� ����� ������ �� ���� ���̰� ����
				 * ��� �޸𸮿� �־�� �� ù ���� �� ������ �׶����� ������ �����.
				 */
					
				if(init == true){
					if(first == false){
						File bibleFile = new File(path + imsi3 + ".txt");
						imsi3="";
						BufferedWriter writer = new BufferedWriter(new FileWriter(bibleFile));
						writer.write(imsi2);
						init = false;
						imsi3 = p;	// ��� �޸𸮿� �־� �д�.
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
		 * �ѹ� �� �з����Ƿ�
		 * ������ ������ ���� �ݺ������δ� �������� �ʴ´�.
		 * �׷��� ������ ������ ���� ����� ��� �Ѵ�.
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
