package shoot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Boom {
	public Image img;//图片对象
	public int x;
	public int y;//画的坐标
	public int width=70, height=70;
	public int xStep,yStep;//移动时x，y坐标的增长幅度
	int n=0;//动图标记

	public Boom(int x, int y) {//爆炸位置和初始图
		img=Toolkit.getDefaultToolkit().getImage("src/bomb_0.gif");
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height,null);
	}

	public void move(){//返回是否继续撞击判定
		if( n%10==0){//动图切换与速度控制
			img=Toolkit.getDefaultToolkit().getImage("src/bomb_"+n/10+".gif");
		}
//		System.out.println(n);
		if(n>=40){//播放一遍后停止，移除爆炸效果
			n=0;//重制切换判定
			x=-100;
			
		}
		else{
			n++;		
			x+=xStep;
			y+=yStep;
			//未播放完继续
		}
	}

	public void move(EnemyPlane enemyPlane) {
		xStep=enemyPlane.xStep;
		yStep=enemyPlane.yStep;
	}

}
