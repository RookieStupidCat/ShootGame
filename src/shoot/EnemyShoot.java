package shoot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class EnemyShoot {
	public Image img;//ͼƬ����
	public int x;
	public int y;//��������
	public int xStep,yStep;//�ƶ�ʱx��y�������������
	int n=0;//���ƶ�ͼ�ٶ�
	int time=0;//�ӵ����ʱ��
		
	public EnemyShoot(int x, int y,  int xStep, int yStep) {
		super();
		img=Toolkit.getDefaultToolkit().getImage("src/el_bb.gif");
		this.x = x;
		this.y = y;
		this.xStep = xStep;
		this.yStep = yStep;
	}
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);		
		time++;
	}
	
	public void move(int hx,int hy,int px,int py){
		yStep=(int) ((hy-py)/100.0);
		if(yStep<3){
			yStep=3;
		}
//		yStep=4;
		xStep=(int) ((hx-px)/100.0);
	}
	public void move(){
		if(n%48==0){//��ҩ�任
			img=Toolkit.getDefaultToolkit().getImage("src/el_bb.gif");
		}else if(n%48==24)
			img=Toolkit.getDefaultToolkit().getImage("src/el_bb_0.gif");
		this.y +=yStep;
		this.x +=xStep;
		n++;	
		
	}

}
