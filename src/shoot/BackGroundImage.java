package shoot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BackGroundImage extends FlyingObject{//继承飞行类
	
	public Image img=Toolkit.getDefaultToolkit().getImage("src/back.JPG");;//图片对象
	
	public BackGroundImage(int x, int y, int width, int height, int xStep, int yStep) {
		super(x, y, width, height, xStep, yStep);//调用父类构造方法完成，完成本类对象的构建
	}
	
	public void draw(Graphics g){
		g.drawImage(img, x, y, width,height,null);
	}
	
	
	public void move(){
		if(y>=0){//永续背景
//			y=-height+MyPanel.PANEL_HEIGHT;
			return;
		}
		y+=yStep;//背景移动
}
	
}
