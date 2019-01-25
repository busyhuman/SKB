// ������ ����� ���� ����� �ڵ�� ���̻� �������� �ʿ䰡 ����.
import java.io.*;

import java.util.*;

public class countBible {
	// Map �ڷᱸ���� �̿�. Ű�� String����, ���� Integer��
	public static Map<String, Integer> mJang = new HashMap<>();
	public static Map<String, Integer> mJeol = new HashMap<>();

	public static void main(String[] args) throws IOException {
		String path = seperateBible.class.getResource("").getPath();	// ���� ��η� ����
		File dirFile = new File(path + "myBible");
		File[] fileList = dirFile.listFiles();

		File countJang = new File(path + "countJang.txt");
		File countJeol = new File(path + "countJeol.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(countJeol));
		
		// ��� ������ ���鼭 �ִ� ��� �� ���� �ִ� ���� ���Ѵ�.
		for(File file : fileList){
			String fileName, chapter = "";
		
			if(file.isFile()){
				fileName = file.getName();
			} else{
				continue;
			}
			int now = fileName.length(), maxJang=0, maxJeol=0,dan=1; // ������ �� ������ �����Ѵ�. dan�� �����̴�. 
			if(fileName == "KoreanBible.txt") continue;	// ���� ������ �ǳ� �ڴ�.
			
			while(fileName.charAt(--now) != '.'){ }	// Ȯ���� �κ� ���ڴ� �ǳʶڴ�. �� txt�κ�
			
			while(fileName.charAt(--now) != '_'){	// ����ٸ� ���� �� ���� ���� �����ش�.
				
				maxJeol += (fileName.charAt(now) - '0') * dan;
				dan *= 10;
			}
		
			dan = 1;
			while(fileName.charAt(--now) != '_'){	// ����ٸ� ���� �� ���� ���� �����ش�.
				maxJang += (fileName.charAt(now) - '0') * dan;
				dan *= 10;
			}
			now = -1;
			while(fileName.charAt(++now) != '_'){ // �������� ���� �������� �����Ѵ�.
				chapter += fileName.charAt(now);
			}
			
			// �ִ� ���� ���Ѵ�. key�� ���� ����
			if(mJang.containsKey(chapter)){
				mJang.put(chapter, Math.max(mJang.get(chapter),maxJang));
			} else{
				mJang.put(chapter, maxJang);
			}
			
			// �ִ� ���� ���Ѵ�. key�� ��������_��
			if(mJeol.containsKey(chapter + "_" + maxJang)){
				mJeol.put(chapter + "_" + maxJang, Math.max(mJeol.get(chapter + "_" + maxJang),maxJeol));
			} else{
				mJeol.put(chapter + "_" + maxJang, maxJeol);
			}

			System.out.println(chapter + " " + maxJang + " " + maxJeol + " " + mJeol.get(chapter+"_"+maxJang));
		}

		// ������ ��� ���� ���� �ڵ�
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

