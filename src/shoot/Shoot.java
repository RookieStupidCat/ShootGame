package shoot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Shoot {
	public Image img;//图片对象
	public int x;
	public int y;//画的坐标
	public int xStep,yStep;//移动时x，y坐标的增长幅度
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
		if(n%24==0){//弹药变换
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
