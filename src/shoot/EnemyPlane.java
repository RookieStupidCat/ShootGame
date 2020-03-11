package shoot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class EnemyPlane extends FlyingObject{
	public Image img=Toolkit.getDefaultToolkit().getImage("src/el_0.png");//ͼƬ����
	
	public EnemyPlane( int x, int y, int width, int height, int xStep, int yStep) {
		super(x, y, width, height, xStep, yStep);
	}
	
	public void draw(Graphics g){
		g.drawImage(img, x, y, width,height,null);
		
	}
	public void move(HeroPlane hero){
		if((int)Math.random()*600==0){//�л����ܻ����ʣ������˶��켣����
			if(this.x+this.width-hero.x<-100){//�л���Ӣ�ۻ������Χ֮�⣬�����ƶ�
				this.xStep=1;
			}else if(this.x-(hero.x+hero.width)>100){//�л���Ӣ�ۻ��Һ���Χ֮�⣬�����ƶ�
				this.xStep=-1;
			}
		}
		
		if(y>=MyPanel.PANEL_HEIGHT){//Խ������
			y=-70;
			x=(int)(Math.random()*(MyPanel.PANEL_WIDTH-70));
		}
		y+=yStep;
		x+=xStep;

	}
	public EnemyShoot shoot(){
		int eBullt_x=this.x+width/2-20/2;//ȷ���ڵл��������
		int eBullt_y=this.y+height;
		EnemyShoot shoot=new EnemyShoot(eBullt_x,eBullt_y, 0, 6);
		return shoot;
	}

	@Override
	public void move() {
		y+=yStep;
		
	}
}
