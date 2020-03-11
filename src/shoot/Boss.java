package shoot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Boss extends FlyingObject{
	public Image img=Toolkit.getDefaultToolkit().getImage("src/boss.png");//图片对象
	public int n=0,n1=0;//击中次数和变红交替
	public static int heaf=5000;//生命值
	int n2=0;//发射子弹周期
	
	
	public Boss(int x, int y, int width, int height, int xStep, int yStep) {
		super(x, y, width, height, xStep, yStep);
	}
	public void draw(Graphics g){
		g.drawImage(img, x, y, width,height,null);
	}
	
	public void move(Vector<EnemyShoot> bbs,int hx,int hy) {
		if(this.y<20)
			this.y+=yStep;	
		else {
			if(n2%60==0){//常规导弹
				for(int i=-1;i<2;i+=2){
					EnemyShoot shoot=new EnemyShoot(MyPanel.PANEL_WIDTH/2-10+100*i, 430-70*Math.abs(i),0, 2);
					bbs.add(shoot );
					shoot.move(hx, hy, MyPanel.PANEL_WIDTH/2-10, 500);
				}
			}
			if(n2%500==0 || n2%500==20 || n2%500==40 || n2%500==60 || n2%500==80 || n2%500==100){//发射子弹
				for(int i=-20;i<=20;i++){//每轮40发
					bbs.add(new EnemyShoot(MyPanel.PANEL_WIDTH/2-10, 430, i, 2));
					bbs.get(bbs.size()-1).move();
				}
			}
			n2++;
		}
	}
	public void hit(Vector<Boom> booms) {
		n++;  n1++;
		if(n==heaf){//5000次有效攻击
			booms.add(new Boom(x	,y));
			booms.get(booms.size()-1).width=400;
			booms.get(booms.size()-1).height=400;
		}if(n>=heaf+10){
			JOptionPane.showMessageDialog(null, "You Win");
			System.exit(0);
			
		}
		System.out.println((heaf-n)+"/"+heaf);//剩余血量
		if(n1%48==0){//弹药变换
			img=Toolkit.getDefaultToolkit().getImage("src/boss.png");
		}else if(n1%48==40)
			img=Toolkit.getDefaultToolkit().getImage("src/boss_0.png");

		
	}
	@Override
	public void move() {
		// TODO 自动生成的方法存根
		
	}
}
