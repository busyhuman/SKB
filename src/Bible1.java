import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
public class Bible1 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JLabel jlb3, jlb4, jlb5; // ���� �� �� �׸� ǥ��
	JButton jbtn3, jbtn4;//Ȯ�� ��ư, ó��ȭ�� ��ư
	JTextArea ta; //���汸�� ��µǴ°�
	String[] ar1 = new String[66];
    Vector<Integer> ar2 = new Vector<>();
    Vector<Integer> ar3 = new Vector<>();
	JComboBox<String> jcm1;		// ���� �� �� �޺��ڽ�
	JComboBox<Integer> jcm2;		// ���� �� �� �޺��ڽ�
	JComboBox<Integer> jcm3;		// ���� �� �� �޺��ڽ�
	goBible gb = new goBible();		// �� ��ü�� �پ��� �˻� ����� �����ϴ� �ϳ��� �˻������� �����ϸ� ����.
	
	   Bible1() throws IOException  {
		  super("���� �б�");
	      setLayout(null); //���̾ƿ� �Ŵ��� ����(x y ��ǥ�� ��ġ �����Ҽ� ����  , ���μ��� ũ�⵵ ��������)
	      jlb3 = new JLabel("����");
	      jlb4 = new JLabel("��");
	      jlb5 = new JLabel("��");
	      jlb3.setBounds(50,30,100,30); // ��ġ�� ũ�� ����(x��ǥ,y��ǥ,����ũ��,����ũ��)
	      jlb4.setBounds(150,30,100,30);
	      jlb5.setBounds(250,30,100,30);
	      add(jlb3); add(jlb4); add(jlb5);
	      
	      // �迭�� ����ڿ��� ������ ���� ������ �־���
	      ar1[0] = "â����"; ar1[1] = "��ֱ���"; ar1[2] = "������"; ar1[3] = "�μ���";
	      ar1[4] = "�Ÿ��"; ar1[5] = "��ȣ����"; ar1[6] = "����"; ar1[7] = "���";
	      ar1[8] = "�繫����"; ar1[9] = "�繫����"; ar1[10] = "���ձ��"; ar1[11] = "���ձ���";
	      ar1[12] = "�����"; ar1[13] = "������"; ar1[14] = "������"; ar1[15] = "����̾�";
	      ar1[16] = "������"; ar1[17] = "���"; ar1[18] = "����"; ar1[19] = "���";
	      ar1[20] = "������"; ar1[21] = "�ư�"; ar1[22] = "�̻��"; ar1[23] = "�����̾�"; 
	      ar1[24] = "�����̾߾ְ�"; ar1[25] = "������"; ar1[26] = "�ٴϿ�"; ar1[27] = "ȣ����";
	      ar1[28] = "�俤"; ar1[29] = "�Ƹ�"; ar1[30] = "���ٴ�"; ar1[31] = "�䳪";
	      ar1[32] = "�̰�"; ar1[33] = "����"; ar1[34] = "�Ϲڱ�"; ar1[35] = "���ٳ�"; 
	      ar1[36] = "�а�";
	      ar1[37] = "������";
	      ar1[38] = "�����";
	      ar1[39] = "���º���";
	      ar1[40] = "��������";
	      ar1[41] = "��������";
	      ar1[42] = "���Ѻ���";
	      ar1[43] = "�絵����";
	      ar1[44] = "�θ���";
	      ar1[45] = "��������";
	      ar1[46] = "�����ļ�";
	      ar1[47] = "�����Ƽ�";
	      ar1[48] = "�����Ҽ�";
	      ar1[49] = "��������";
	      ar1[50] = "��λ���";
	      ar1[51] = "����δϰ�����";
	      ar1[52] = "����δϰ��ļ�";
	      ar1[53] = "�������";
	      ar1[54] = "����ļ�";
	      ar1[55] = "�𵵼�";
	      ar1[56] = "������";
	      ar1[57] = "���긮��";
	      ar1[58] = "�߰���";
	      ar1[59] = "���������";
	      ar1[60] = "������ļ�";
	      ar1[61] = "����1��";
	      ar1[62] = "����2��";
	      ar1[63] = "����3��";
	      ar1[64] = "���ټ�";
	      ar1[65] = "���Ѱ�÷�";

	 	  jcm1 = new JComboBox<>(ar1);		// ���� �޺��ڽ�
	 	  jcm2 = new JComboBox<>(ar2);		// �� �޺��ڽ�
	 	  jcm3 = new JComboBox<>(ar3);		// �� �޺��ڽ�

	      jcm1.setBounds(20,60,100,30);
	      jcm2.setBounds(120,60,100,30);  //�޺��ڽ�
	      jcm3.setBounds(220,60,100,30);
	      jcm1.addActionListener(this);
	      jcm2.addActionListener(this);
	      // jcm3�� �����ʸ� �߰����� �ʿ䰡 ����.
	      
	      add(jcm1);
	      add(jcm2);
	      add(jcm3);
	      
	      jbtn3 = new JButton("Ȯ��");
	      jbtn3.setBounds(200, 200, 60, 30); //Ȯ�ι�ư
	      add(jbtn3);
	      jbtn3.addActionListener(this);
	      ta = new JTextArea(5,10);
	      ta.setBounds(20,240,300,110); //���� ����ִ°�
	      ta.setLineWrap(true);
	      ta.setEditable(false);
	      add(ta);
	      gb.putBiblename();

	      String jangStr = gb.findMaxJang(goBible.bibleName.get(jcm1.getSelectedItem()));
		  int jangInt = gb.makeInteger(jangStr);
		  ar2.clear();
		  for(int i=1;i<=jangInt;i++){	// �ش� ���� ������ �ִ� �常ŭ ����
			  ar2.add(i);
		  }	      
	      String jeolStr = gb.findMaxJeol(goBible.bibleName.get(jcm1.getSelectedItem())+ "_" + jangStr);
	      int jeolInt = gb.makeInteger(jeolStr);
	      ar3.clear();
	      for(int i=1;i<=jeolInt;i++){	// �ش� ���� ����, ���� �ִ� ����ŭ ����
	    	  ar3.add(i);
	      }	    

	      jcm2.setSelectedIndex(0);	// 1��
	      jcm3.setSelectedIndex(0);	// 1��
	
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
		  
			   // ���� ������ ����� ��
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

			  	// ���� ����� ��
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
