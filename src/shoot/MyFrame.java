package shoot;

import java.awt.Toolkit;

import javax.swing.JFrame;//����java�������ṩ�õĴ�����JFrame

public class MyFrame {
	public static final int FRAME_WIDTH=Toolkit.getDefaultToolkit().getScreenSize().width/3;//������
	public static final int FRAME_HEIGHT=Toolkit.getDefaultToolkit().getScreenSize().height-50;//����߶�

	public static void main(String[] args) {
//		�½�һ��������󣬰Ѹô���������ڴ��е���ʼ��ַ����������frame
//		�Ժ�����ͨ��Ŷframe�������ո��½��Ĵ���
		JFrame frame=new JFrame();
		frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		frame.setTitle("�ɻ���ս������");//���崰���������
		frame.setResizable(false);//���ô����С���ɸı�
		//���ô������˭��λ�ã���NULL���������Ļ��λ����Ļ�м䣬������
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ô���Ĭ�Ϲر���Ϊ��Ĭ�ϴ���ر�ʱ�˳�����
		MyPanel panel=new MyPanel();//����MyPanel����
		frame.add(panel);//�ڴ�����������
		frame.setVisible(true);//��ʾ����	
		panel.star();
		panel.action();
		
	}

}
