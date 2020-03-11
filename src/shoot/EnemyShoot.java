package shoot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class EnemyShoot {
	public Image img;//图片对象
	public int x;
	public int y;//画的坐标
	public int xStep,yStep;//移动时x，y坐标的增长幅度
	int n=0;//控制动图速度
	int time=0;//子弹存活时间
		
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
		if(n%48==0){//弹药变换
			img=Toolkit.getDefaultToolkit().getImage("src/el_bb.gif");
		}else if(n%48==24)
			img=Toolkit.getDefaultToolkit().getImage("src/el_bb_0.gif");
		this.y +=yStep;
		this.x +=xStep;
		n++;	
		
	}

}
