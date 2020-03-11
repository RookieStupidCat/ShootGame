package shoot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BackGroundImage extends FlyingObject{//�̳з�����
	
	public Image img=Toolkit.getDefaultToolkit().getImage("src/back.JPG");;//ͼƬ����
	
	public BackGroundImage(int x, int y, int width, int height, int xStep, int yStep) {
		super(x, y, width, height, xStep, yStep);//���ø��๹�췽����ɣ���ɱ������Ĺ���
	}
	
	public void draw(Graphics g){
		g.drawImage(img, x, y, width,height,null);
	}
	
	
	public void move(){
		if(y>=0){//��������
//			y=-height+MyPanel.PANEL_HEIGHT;
			return;
		}
		y+=yStep;//�����ƶ�
}
	
}
