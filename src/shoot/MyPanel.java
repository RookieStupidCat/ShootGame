package shoot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyPanel extends JPanel implements MouseMotionListener,MouseListener{
	public static final int PANEL_WIDTH=MyFrame.FRAME_WIDTH-7;
	public static final int PANEL_HEIGHT=MyFrame.FRAME_HEIGHT-30; 
	public static  int MAX_ENEMY_PLANE_NUMBER=8;//最大敌机数量
	public static  int v=10;//控制整体速度
	public BackGroundImage back;//背景
	public Vector<EnemyPlane> planes;//构建敌机容器对象
	//	public EnemyPlane[] plane=new EnemyPlane[6];//敌机
	public Vector<Shoot> shoots;//英雄机子弹
	public Vector<Boom> booms;//爆炸类
	public HeroPlane hero;//英雄机
	public Hit hit=new Hit();//撞击类
	public Vector<EnemyShoot> bbs;//敌机子弹
	public Boss boss;//boss
	public Start start;//开始面板类
	public Vector<Score> score;//显示分数
	public static int totalScore=0;//游戏分数
	public int time=0;//控制时间节点
	int mouse_x,mouse_y;//鼠标点击位置

	public MyPanel() {
		//1.构建出游戏背景图对象
		back=new BackGroundImage( 0, -1200*(PANEL_HEIGHT/200)+PANEL_HEIGHT, PANEL_WIDTH, 1200*(PANEL_HEIGHT/200), 0, 2);
		//2.构建敌机容器
		planes=new Vector<EnemyPlane>();
		for(int i=0;i<MAX_ENEMY_PLANE_NUMBER;i++){
			EnemyPlane plane=new EnemyPlane((int)(Math.random()*(PANEL_WIDTH-70)),-70,70,70,0,3);
			planes.add(plane);			
		}
		//		for(int i=0;i<6;i++){
		//			planes.get(i)=new EnemyPlane((int)(Math.random()*(PANEL_WIDTH-70)),-70,70,70,0,4);
		//			
		//		}
		//构建英雄机
		hero=new HeroPlane(PANEL_WIDTH/2-50, PANEL_HEIGHT-200, 100, 100, 0, 0);
		//hero子弹
		shoots=new Vector<Shoot>();
		//敌机子弹
		bbs=new Vector<EnemyShoot>();
		//爆炸判定
		booms=new Vector<Boom>();
		//boss
		boss=new Boss(PANEL_WIDTH/2-200, -400, 400, 400, 0, 1);
		//		开始面板
		start=new Start(0, 0, PANEL_WIDTH, PANEL_HEIGHT, 0,0);
		//显示分数
		score=new Vector<Score>();
		this.addMouseMotionListener(this);
		this.addMouseListener(this);		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(time<2){
			start.move(time);
			start.draw(g);//画用户界面
		}else{
			back.draw(g);//画背景图
			for(int i=0;i<MAX_ENEMY_PLANE_NUMBER;i++){
				planes.get(i).draw(g);//画敌机

			}
			for(int i=0;i<shoots.size();i++){
				shoots.get(i).draw(g);//画子弹
			}
			for(int i=0;i<bbs.size();i++){
				bbs.get(i).draw(g);//画敌军子弹
			}
			boss.draw(g);//画boss

			for(int i=0;i<booms.size();i++){
				booms.get(i).draw(g);//画爆炸
				score.add(new Score(booms.get(i).x, booms.get(i).y, 0, 0, 0, 0));
				
			}for(int i=0;i<score.size();i++){
				score.get(i).draw(g);//画分数
				score.remove(i);
				i--;
			}
			//话英雄机命数和游戏得分
			g.setColor(Color.WHITE);
			g.setFont(new Font("",Font.BOLD,20));
			g.drawString("剩余生命："+hit.n,0,30);
			g.drawString("目前得分："+totalScore,0,50);

			hero.draw(g);//画英雄机
		}
	}


	public void star(){
		while(true){
			//			System.out.println(mouse_x+" ,"+mouse_y);
			time=start.mouse(mouse_x,mouse_y);//难度选择2/3/4分别为简单，正常，噩梦
			if(time>=2){
				return;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	public int n=0;	//动图速度控制 子弹和爆炸

	public void action(){
		while(true){
			//背景移动
			back.move();

			//对敌机的处理
			for(int i=0;i<MAX_ENEMY_PLANE_NUMBER;i++){
				if(boss.y>=0){//boss出现，停止生成新飞机
					planes.get(i).move();
				}else if(hero.time<hero.MAX_TIME){//hero刚出生暂停产生敌机

				}
				else planes.get(i).move(hero);//控制敌机移动
				if((int)(Math.random()*200)==1 && planes.get(i).y>=50){					//敌机子弹发射频率
					bbs.add(planes.get(i).shoot());
					bbs.get(bbs.size()-1).move(hero.x+50,hero.y+50,planes.get(i).x+70/2-20/2,planes.get(i).y+70);
				}

			}
			//敌方子弹移动
			for(int i=0;i<bbs.size();i++){
				if(bbs.get(i).y>=PANEL_HEIGHT || bbs.get(i).time>=500){//子弹存活时间及越界判定
					bbs.remove(i);
					i--;
				}
				else bbs.get(i).move();
			}
			//英雄子弹移动
			if(hero.time>=hero.MAX_TIME){
				if(n++ %15==0){//自动开炮
					shoots.add(new Shoot(hero.x,hero.y-100,0,5));
				}
			}
			//英雄子弹移动
			for(int i=0;i<shoots.size();i++){
				if(shoots.get(i).y<=-100){
					shoots.remove(i);
					i--;
				}
				else shoots.get(i).move();
			}
			//英雄机重新入场
			if(hero.time<hero.MAX_TIME){
				hero.afterHit();
			}

			//一系列撞击判定
			for(int i=0;i<MAX_ENEMY_PLANE_NUMBER;i++){
				for(int j=0;j<shoots.size();j++){//与敌机相撞或击中敌机
					if(0<=hit.decide(planes.get(i).x , planes.get(i).y, 70, 70,shoots.get(j).x, shoots.get(j).y, 100,100) )//撞击判断
					{			
						booms.add(new Boom(planes.get(i).x,planes.get(i).y));//传送撞击位置，构建爆炸动图
						booms.get(booms.size()-1).move(planes.get(i));//爆炸云继续移动
						planes.get(i).y=PANEL_HEIGHT;//移除敌方飞机
						totalScore+=100;//加分100每架
						if(shoots.get(j).y<=PANEL_HEIGHT-400)//隐藏功能，敌机离底线过近（最下方400像素）时子弹可穿透敌机
							shoots.remove(j);//移除子弹
					}
					if(0<=hit.decide(planes.get(i).x,planes.get(i).y, 70, 70,hero.x+10, hero.y+10, 80,40)){//碰撞判定区小于实际体积
						JOptionPane.showMessageDialog(null, "you only have "+(hit.n-1)+" life");
						hit.result(bbs,planes,hero,booms);//碰撞反馈
					}
				}
			}
			for(int i=0;i<bbs.size();i++){//被敌机击中
				if(0<=hit.decide(bbs.get(i).x,bbs.get(i).y, 20, 20,hero.x+10, hero.y+10, 80,40)){//碰撞判定区小于实际体积
					JOptionPane.showMessageDialog(null, "you only have "+(hit.n-1)+" life");
					hit.result(bbs,planes,hero,booms);//碰撞反馈
				}
			}

			for(int i=0;i<shoots.size();i++){//击中boss
				if(0<=hit.decide(boss.x,boss.y, 400, 400,shoots.get(i).x, shoots.get(i).y, 100,100)){
					boss.hit(booms);
					totalScore+=50;
				}
			}

			//爆炸控制
			for(int i=0;i<booms.size();i++){
				booms.get(i).move();//爆炸变换
				if(booms.get(i).x==-100){//移除爆破对象
					booms.remove(i);
				}
			}
			if(back.y>=0){
				boss.move(bbs,hero.x,hero.y);//boss出场
			}
			repaint();
			try {
				Thread.sleep(v);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(hero.time>=hero.MAX_TIME)
			hero.move(e.getX()-50,e.getY()-50);//拖动有效
		repaint();

	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if(hero.time>=hero.MAX_TIME)
			hero.move(e.getX()-50,e.getY()-50);//英雄机跟随鼠标移动

		repaint();		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouse_x=e.getX();
		mouse_y=e.getY();
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根

	}
}
