// 파일을 만들기 위해 사용한 코드라 더이상 컴파일할 필요가 없다.
import java.io.*;

import java.util.*;

public class countBible {
	// Map 자료구조를 이용. 키를 String으로, 값을 Integer로
	public static Map<String, Integer> mJang = new HashMap<>();
	public static Map<String, Integer> mJeol = new HashMap<>();

	public static void main(String[] args) throws IOException {
		String path = seperateBible.class.getResource("").getPath();	// 절대 경로로 지정
		File dirFile = new File(path + "myBible");
		File[] fileList = dirFile.listFiles();

		File countJang = new File(path + "countJang.txt");
		File countJeol = new File(path + "countJeol.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(countJeol));
		
		// 모든 파일을 돌면서 최대 장과 그 장의 최대 절을 구한다.
		for(File file : fileList){
			String fileName, chapter = "";
		
			if(file.isFile()){
				fileName = file.getName();
			} else{
				continue;
			}
			int now = fileName.length(), maxJang=0, maxJeol=0,dan=1; // 파일의 맨 끝부터 시작한다. dan은 단위이다. 
			if(fileName == "KoreanBible.txt") continue;	// 원본 파일은 건너 뛴다.
			
			while(fileName.charAt(--now) != '.'){ }	// 확장자 부분 문자는 건너뛴다. 즉 txt부분
			
			while(fileName.charAt(--now) != '_'){	// 언더바를 만날 때 까지 절을 구해준다.
				
				maxJeol += (fileName.charAt(now) - '0') * dan;
				dan *= 10;
			}
		
			dan = 1;
			while(fileName.charAt(--now) != '_'){	// 언더바를 만날 때 까지 장을 구해준다.
				maxJang += (fileName.charAt(now) - '0') * dan;
				dan *= 10;
			}
			now = -1;
			while(fileName.charAt(++now) != '_'){ // 나머지는 성경 제목으로 저장한다.
				chapter += fileName.charAt(now);
			}
			
			// 최대 장을 구한다. key는 성경 제목
			if(mJang.containsKey(chapter)){
				mJang.put(chapter, Math.max(mJang.get(chapter),maxJang));
			} else{
				mJang.put(chapter, maxJang);
			}
			
			// 최대 절을 구한다. key는 성경제목_장
			if(mJeol.containsKey(chapter + "_" + maxJang)){
				mJeol.put(chapter + "_" + maxJang, Math.max(mJeol.get(chapter + "_" + maxJang),maxJeol));
			} else{
				mJeol.put(chapter + "_" + maxJang, maxJeol);
			}

			System.out.println(chapter + " " + maxJang + " " + maxJeol + " " + mJeol.get(chapter+"_"+maxJang));
		}

		// 밑으로 모두 파일 생성 코드
		Set<String> keyset = mJang.keySet();
		Iterator<String> keyIterator = keyset.iterator();
		while(keyIterator.hasNext()){
			String k = keyIterator.next();
			
			for(int i=1;i<=mJang.get(k);i++){
				System.out.println(k + "_" + i + "_" + mJeol.get(k + "_" + i));
				writer.write(k + "_" + i + "_" + mJeol.get(k + "_" + i));
				writer.newLine();
			}
		}
		
		writer.close();
		
		writer = new BufferedWriter(new FileWriter(countJang));
		keyset = mJang.keySet();
		keyIterator = keyset.iterator();
		while(keyIterator.hasNext()){
			String k = keyIterator.next();
			
			System.out.println(k + "_" + mJang.get(k));
			writer.write(k + "_" + mJang.get(k));
			writer.newLine();
		}
		
		writer.close();
		
	}

}

