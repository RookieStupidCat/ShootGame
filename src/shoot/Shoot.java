package shoot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Shoot {
	public Image img;//ͼƬ����
	public int x;
	public int y;//��������
	public int xStep,yStep;//�ƶ�ʱx��y�������������
	int n=0;
	
	public Shoot(int x, int y,  int xStep, int yStep) {
		super();
		img=Toolkit.getDefaultToolkit().getImage("src/fire.gif");
		this.x = x;
		this.y = y;
		this.xStep = xStep;
		this.yStep = yStep;
	}
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	public void move(){
		if(n%24==0){//��ҩ�任
			img=Toolkit.getDefaultToolkit().getImage("src/fire_1.gif");
		}else if(n%24==12)
			img=Toolkit.getDefaultToolkit().getImage("src/fire.gif");
		this.y -=yStep;
		n++;		

	}
	public void move(int x,int y){
		this.x=x;
		this.y=y;
	}

}
