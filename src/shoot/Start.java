package shoot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Start extends FlyingObject{//继承飞行类
	
	public Image img=Toolkit.getDefaultToolkit().getImage("src/Star_0");//图片对象
	public int time=0;
	
	public Start(int x, int y, int width, int height, int xStep, int yStep) {
		super(x, y, width, height, xStep, yStep);//调用父类构造方法完成，完成本类对象的构建
	}
	public void draw(Graphics g){
		g.drawImage(img, x, y, width,height,null);
	}

	
	public void move(int time){
		this.time=time;
		switch (time) {
		case 0:
			img=Toolkit.getDefaultToolkit().getImage("src/Star_2.png");
			break;
		case 1:
			img=Toolkit.getDefaultToolkit().getImage("src/Star_3.png");
			break;
		default:
			break;
		}
		
}
	@Override
	public void move() {
		// TODO 自动生成的方法存根
		
	}
	public int mouse(int m_x,int m_y) {
		switch (time) {
		case 0://单击开始
			if(m_x>175 && m_x<460 && m_y>476 && m_y<522)
				return 1;
		case 1:
			if(m_x>270 && m_x<360 && m_y>542 && m_y<586){//简单难度
				MyPanel.MAX_ENEMY_PLANE_NUMBER=4;//4架飞机
				Boss.heaf=2000;//boss生命值
				MyPanel.v=15;
				return 2;
			}
			else if(m_x>270 && m_x<360 && m_y>613 && m_y<656){//正常难度
				MyPanel.MAX_ENEMY_PLANE_NUMBER=6;
				Boss.heaf=5000;
				MyPanel.v=11;
				return 3;
			}
			else if(m_x>270 && m_x<360 && m_y>690 && m_y<727){//噩梦难度
				MyPanel.MAX_ENEMY_PLANE_NUMBER=8;
				Boss.heaf=10000;
				MyPanel.v=7;
				return 4;
			}
		default:
			break;
		}
		return time;
	}

	
}
