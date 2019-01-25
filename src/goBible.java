import java.util.*;
import java.io.*;
public class goBible {
	
	public static Map<String, String> bibleName = new HashMap<>();	// ����ڿ��� ������ �̸��� Key��, ���� �ؽ�Ʈ������ ���� ������ Value��.
	public File dirFile = new File(".\\myBible");	// ��� ��η� ����
	public File[] fileList = dirFile.listFiles();	// ���� ������ ���ϵ�
	
	void putBiblename(){	// Map �ڷᱸ����  �ϴ��� ����

		if(bibleName.isEmpty()){
			bibleName.put("â����", "â");
			bibleName.put("��ֱ���", "��");
			bibleName.put("������", "��");
			bibleName.put("�μ���", "��");
			bibleName.put("�Ÿ��", "��");
			bibleName.put("��ȣ����", "��");
			bibleName.put("����", "��");
			bibleName.put("���", "��");
			bibleName.put("�繫����", "���");
			bibleName.put("�繫����", "����");
			bibleName.put("���ձ��", "�ջ�");
			bibleName.put("���ձ���", "����");
			bibleName.put("�����", "���");
			bibleName.put("������", "����");
			bibleName.put("������", "��");
			bibleName.put("����̾�", "��");
			bibleName.put("������", "��");
			bibleName.put("���", "��");
			bibleName.put("����", "��");
			bibleName.put("���", "��");
			bibleName.put("������", "��");
			bibleName.put("�ư�", "��");
			bibleName.put("�̻��", "��");
			bibleName.put("�����̾�", "��");
			bibleName.put("�����̾߾ְ�", "��");
			bibleName.put("������", "��");
			bibleName.put("�ٴϿ�", "��");
			bibleName.put("ȣ����", "ȣ");
			bibleName.put("�俤", "��");
			bibleName.put("�Ƹ�", "��");
			bibleName.put("���ٴ�", "��");
			bibleName.put("�䳪", "��");
			bibleName.put("�̰�", "��");
			bibleName.put("����", "��");
			bibleName.put("�Ϲڱ�", "��");
			bibleName.put("���ٳ�", "��");
			bibleName.put("�а�", "��");
			bibleName.put("������", "��");
			bibleName.put("�����", "��");
			bibleName.put("���º���", "��");
			bibleName.put("��������", "��");
			bibleName.put("��������", "��");
			bibleName.put("���Ѻ���", "��");
			bibleName.put("�絵����", "��");
			bibleName.put("�θ���", "��");
			bibleName.put("��������", "����");
			bibleName.put("�����ļ�", "����");
			bibleName.put("�����Ƽ�", "��");
			bibleName.put("�����Ҽ�", "��");
			bibleName.put("��������", "��");
			bibleName.put("��λ���", "��");
			bibleName.put("����δϰ�����", "����");
			bibleName.put("����δϰ��ļ�", "����");
			bibleName.put("�������", "����");
			bibleName.put("����ļ�", "����");
			bibleName.put("�𵵼�", "��");
			bibleName.put("������", "��");
			bibleName.put("���긮��", "��");
			bibleName.put("�߰���", "��");
			bibleName.put("���������", "����");
			bibleName.put("������ļ�", "����");
			bibleName.put("����1��", "����");
			bibleName.put("����2��", "����");
			bibleName.put("����3��", "���");
			bibleName.put("���ټ�", "��");
			bibleName.put("���Ѱ�÷�", "��");
		}
	}
	
	public String findaBible(String s2find) throws IOException{	// ���� ������ ã�´�.
		 FileReader namereader = new FileReader(".\\names.txt");
		 int noww;

		String s2show = "", fileName = "";	// �ʱ�ȭ
		boolean esc = false;				// Ż�� ����. ���� ������ ã���� true�� �ٲ���.
		if(bibleName.isEmpty()){	// Map�� Null�̸� �ȵǱ⿡ �˻� ����. ����ִٸ� ��������.
			putBiblename();
		}	

		// ��� �������ϵ��� ���鼭 �ش��ϴ� ���汸���� ã�´�.
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

				while(true){	// 1�� ����
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

	int makeInteger(String s){	// ���ڸ� ���ڷ� ��ȯ�Ѵ�.
		int dan=1,su=0;
		int len = s.length();
		
		// ���� �ϳ��ϳ��� �ƽ�Ű �ڵ带 �̿��� ���ڷ� ������ְ� 1, 10, 100 .... �� ���Ͽ� �˸´� ���� �ٲپ� �ش�.
		for(int i=len-1;i>=0;i--){
			su += (s.charAt(i) -'0') * dan;
			dan *= 10;
		}
		return su;
	}
	
	String findMaxJang(String jcmName) throws IOException{	// �ִ� ���� �˻��Ѵ�.
		 FileReader jangreader = new FileReader(".\\countJang.txt");	// ��� ��η� ����.

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

	String findMaxJeol(String jcmName) throws IOException{	// �ִ� ���� �˻��Ѵ�.
		 FileReader jeolreader = new FileReader(".\\countJeol.txt");	// ��� ��η� ����.
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
	
	String searchBible(String p, String fileName) throws IOException{	// KMP �˰����� �����ߴ�. ����ڰ� �Է��� ���� ������ ã���ش�. p�� ã���� �ϴ� ����
		String s = "";
		fileName += ".txt";
		FileReader reader = new FileReader(dirFile + "\\" + fileName);	// ��� ��η� ����.
		
	    int m = p.length(),idx;
		boolean check = false;
	    int now;
	    // ���� ������ �޾ƿ´�.
	    while(true){
	    	now = reader.read();
	    	if(now == -1) break;
	    	s += (char)now;
	    }
	    int n = s.length();
	    String p2 = "";	// p2�� ã���� �ϴ� ���忡�� �� �ܾ�
	    
	    /*
	     * ���� ã���� �ϴ� �ܾ��� 1���� �ܾ��� ���̱��� ������ ���ξ�� ���̾ ��ġ�ϴ� �ִ��� ���̸� ���Ѵ�.
	     * �� �������� ���� ���� �ϳ��ϳ��� ���鼭 ������ ������ ���� ������.
	     * ���ڰ� ��ġ�ϸ� ���� ���ڸ� ���ϰ� ����ġ�ϸ� ���� ���� ������ �ִ� ��ġ�ߴ� �Ͱ� ���Ѵ�.
	     * �̷��� �ؼ� �ִ� ���̿��� ��ġ�ϸ� ã�� ���̸�,�׷��� �ʴٸ� ���� �ܾ�� �Ѿ�µ�
	     * �� �� ã���� �ϴ� �ܾ��� �ٷ� ���� ��ġ���� ã�´�. �� �ܾ��� ���� + 1
	     * n = (���� ���� ����), m = (ã���� �ϴ� �ܾ�)�� ���� ��
	     * O(n+m)�� �ð��ȿ� ã�� �� �ִ�.
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
	    if(check == false) return "";	// ã�� �� ����

	    return s;	// ã�� �� ����
	}
	
}
