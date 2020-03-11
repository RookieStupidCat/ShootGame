package shoot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class HeroPlane extends FlyingObject{

	public Image img=Toolkit.getDefaultToolkit().getImage("src/hero.png");//图片对象
	public int time;
	public final int MAX_TIME=100;
	
	public HeroPlane(int x, int y, int width, int height, int xStep, int yStep) {
		super(x, y, width, height, xStep, yStep);
	}
	
	public void draw(Graphics g){
		g.drawImage(img, x, y, width,height,null);
		
	}


	public void move(int x,int y) {
		if(x>MyPanel.PANEL_WIDTH-width)
			this.x=MyPanel.PANEL_WIDTH-width;
		else if(x<0)
			this.x=0;
		else this.x=x;
		
		if(y>MyPanel.PANEL_HEIGHT-height)
			this.y=MyPanel.PANEL_HEIGHT-height;
		else if(y<0)
			this.y=0;
		else this.y=y;
	}

	public void afterHit(){
		x=MyPanel.PANEL_WIDTH/2-50;
		if(time==0)
			y=MyPanel.PANEL_HEIGHT;
		else y-=2;
		time++;
	}
	@Override
	public void move() {
		// TODO 自动生成的方法存根
		
	}

}
