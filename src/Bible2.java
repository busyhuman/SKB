import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;
public class Bible2 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
		JTextField s; //�˻�â
		JTextArea sr; //�˻���� ����ִ°�
		JPanel jp4; //��ũ�ѹ� ��������� �г�
		JButton jbtn5; // Ȯ�ι�ư
		goBible gb = new goBible();	// �� ��ü�� �پ��� �˻� ����� �����ϴ� �ϳ��� �˻������� �����ϸ� ����.
		
		void rotateBible(String p) throws IOException{	// KMP �˰����� �����ߴ�. ����ڰ� �Է��� ���� ������ ã���ش�. �ڼ��� ������ goBible.class��.
			FileReader namereader = new FileReader(".\\names.txt");	// ��� ��η� ����
			String output = "";
			String nope = "�˻������ �����ϴ�.";	// �˻� ����� ���� ���� ���� �� ��� ����.
			waitMsg wm = new waitMsg();		// �˻����� �� ����ִ� â ����
			
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
		super("���� ã��");
		setLayout(new FlowLayout());
		jp4 = new JPanel();
		JLabel lb1 = new JLabel("�˻�"); 
		s = new JTextField(20);
		sr = new JTextArea(15,25); 
	    sr.setLineWrap(true);	// ���� �ڵ� �ٹٲ�.
	    sr.setEditable(false);	// ����ڰ� ���� �Ұ�
		jbtn5 = new JButton("Ȯ��");
		jbtn5.addActionListener(this);
	    int v2 = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;		//���� ��ũ�ѹ� ����
	    int h2 = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER; 	//���� ��ũ�ѹ� ����
	    JScrollPane js3 = new JScrollPane(jp4, v2, h2); //�гξȿ� ��ũ�ѹ� �߰�
	      
	    add(lb1); 
	    add(s);
	    add(jbtn5);
	    add(sr);
	    jp4.add(sr); //�г� �ȿ� TextArea �߰���

	    add(js3, BorderLayout.CENTER);
	    gb.putBiblename();
		setSize(400, 400); 
	    setVisible(true); 
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		  if(e.getSource() == jbtn5) {
			  String imsi = s.getText();

			  if(imsi.length() > 1){	// �� ���� �̻� �Է��ؾ� �˻�
					try {
						rotateBible(imsi);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			  } else{					// �� ���� �̸��̸� �ش� ���� ���
				  sr.setText("�� ���� �̻� �Է����ּ���.");
			  }
		  }
		
	}
	
	
	
}
