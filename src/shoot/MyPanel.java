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
	public static  int MAX_ENEMY_PLANE_NUMBER=8;//���л�����
	public static  int v=10;//���������ٶ�
	public BackGroundImage back;//����
	public Vector<EnemyPlane> planes;//�����л���������
	//	public EnemyPlane[] plane=new EnemyPlane[6];//�л�
	public Vector<Shoot> shoots;//Ӣ�ۻ��ӵ�
	public Vector<Boom> booms;//��ը��
	public HeroPlane hero;//Ӣ�ۻ�
	public Hit hit=new Hit();//ײ����
	public Vector<EnemyShoot> bbs;//�л��ӵ�
	public Boss boss;//boss
	public Start start;//��ʼ�����
	public Vector<Score> score;//��ʾ����
	public static int totalScore=0;//��Ϸ����
	public int time=0;//����ʱ��ڵ�
	int mouse_x,mouse_y;//�����λ��

	public MyPanel() {
		//1.��������Ϸ����ͼ����
		back=new BackGroundImage( 0, -1200*(PANEL_HEIGHT/200)+PANEL_HEIGHT, PANEL_WIDTH, 1200*(PANEL_HEIGHT/200), 0, 2);
		//2.�����л�����
		planes=new Vector<EnemyPlane>();
		for(int i=0;i<MAX_ENEMY_PLANE_NUMBER;i++){
			EnemyPlane plane=new EnemyPlane((int)(Math.random()*(PANEL_WIDTH-70)),-70,70,70,0,3);
			planes.add(plane);			
		}
		//		for(int i=0;i<6;i++){
		//			planes.get(i)=new EnemyPlane((int)(Math.random()*(PANEL_WIDTH-70)),-70,70,70,0,4);
		//			
		//		}
		//����Ӣ�ۻ�
		hero=new HeroPlane(PANEL_WIDTH/2-50, PANEL_HEIGHT-200, 100, 100, 0, 0);
		//hero�ӵ�
		shoots=new Vector<Shoot>();
		//�л��ӵ�
		bbs=new Vector<EnemyShoot>();
		//��ը�ж�
		booms=new Vector<Boom>();
		//boss
		boss=new Boss(PANEL_WIDTH/2-200, -400, 400, 400, 0, 1);
		//		��ʼ���
		start=new Start(0, 0, PANEL_WIDTH, PANEL_HEIGHT, 0,0);
		//��ʾ����
		score=new Vector<Score>();
		this.addMouseMotionListener(this);
		this.addMouseListener(this);		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(time<2){
			start.move(time);
			start.draw(g);//���û�����
		}else{
			back.draw(g);//������ͼ
			for(int i=0;i<MAX_ENEMY_PLANE_NUMBER;i++){
				planes.get(i).draw(g);//���л�

			}
			for(int i=0;i<shoots.size();i++){
				shoots.get(i).draw(g);//���ӵ�
			}
			for(int i=0;i<bbs.size();i++){
				bbs.get(i).draw(g);//���о��ӵ�
			}
			boss.draw(g);//��boss

			for(int i=0;i<booms.size();i++){
				booms.get(i).draw(g);//����ը
				score.add(new Score(booms.get(i).x, booms.get(i).y, 0, 0, 0, 0));
				
			}for(int i=0;i<score.size();i++){
				score.get(i).draw(g);//������
				score.remove(i);
				i--;
			}
			//��Ӣ�ۻ���������Ϸ�÷�
			g.setColor(Color.WHITE);
			g.setFont(new Font("",Font.BOLD,20));
			g.drawString("ʣ��������"+hit.n,0,30);
			g.drawString("Ŀǰ�÷֣�"+totalScore,0,50);

			hero.draw(g);//��Ӣ�ۻ�
		}
	}


	public void star(){
		while(true){
			//			System.out.println(mouse_x+" ,"+mouse_y);
			time=start.mouse(mouse_x,mouse_y);//�Ѷ�ѡ��2/3/4�ֱ�Ϊ�򵥣�������ج��
			if(time>=2){
				return;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	public int n=0;	//��ͼ�ٶȿ��� �ӵ��ͱ�ը

	public void action(){
		while(true){
			//�����ƶ�
			back.move();

			//�Եл��Ĵ���
			for(int i=0;i<MAX_ENEMY_PLANE_NUMBER;i++){
				if(boss.y>=0){//boss���֣�ֹͣ�����·ɻ�
					planes.get(i).move();
				}else if(hero.time<hero.MAX_TIME){//hero�ճ�����ͣ�����л�

				}
				else planes.get(i).move(hero);//���Ƶл��ƶ�
				if((int)(Math.random()*200)==1 && planes.get(i).y>=50){					//�л��ӵ�����Ƶ��
					bbs.add(planes.get(i).shoot());
					bbs.get(bbs.size()-1).move(hero.x+50,hero.y+50,planes.get(i).x+70/2-20/2,planes.get(i).y+70);
				}

			}
			//�з��ӵ��ƶ�
			for(int i=0;i<bbs.size();i++){
				if(bbs.get(i).y>=PANEL_HEIGHT || bbs.get(i).time>=500){//�ӵ����ʱ�估Խ���ж�
					bbs.remove(i);
					i--;
				}
				else bbs.get(i).move();
			}
			//Ӣ���ӵ��ƶ�
			if(hero.time>=hero.MAX_TIME){
				if(n++ %15==0){//�Զ�����
					shoots.add(new Shoot(hero.x,hero.y-100,0,5));
				}
			}
			//Ӣ���ӵ��ƶ�
			for(int i=0;i<shoots.size();i++){
				if(shoots.get(i).y<=-100){
					shoots.remove(i);
					i--;
				}
				else shoots.get(i).move();
			}
			//Ӣ�ۻ������볡
			if(hero.time<hero.MAX_TIME){
				hero.afterHit();
			}

			//һϵ��ײ���ж�
			for(int i=0;i<MAX_ENEMY_PLANE_NUMBER;i++){
				for(int j=0;j<shoots.size();j++){//��л���ײ����ел�
					if(0<=hit.decide(planes.get(i).x , planes.get(i).y, 70, 70,shoots.get(j).x, shoots.get(j).y, 100,100) )//ײ���ж�
					{			
						booms.add(new Boom(planes.get(i).x,planes.get(i).y));//����ײ��λ�ã�������ը��ͼ
						booms.get(booms.size()-1).move(planes.get(i));//��ը�Ƽ����ƶ�
						planes.get(i).y=PANEL_HEIGHT;//�Ƴ��з��ɻ�
						totalScore+=100;//�ӷ�100ÿ��
						if(shoots.get(j).y<=PANEL_HEIGHT-400)//���ع��ܣ��л�����߹��������·�400���أ�ʱ�ӵ��ɴ�͸�л�
							shoots.remove(j);//�Ƴ��ӵ�
					}
					if(0<=hit.decide(planes.get(i).x,planes.get(i).y, 70, 70,hero.x+10, hero.y+10, 80,40)){//��ײ�ж���С��ʵ�����
						JOptionPane.showMessageDialog(null, "you only have "+(hit.n-1)+" life");
						hit.result(bbs,planes,hero,booms);//��ײ����
					}
				}
			}
			for(int i=0;i<bbs.size();i++){//���л�����
				if(0<=hit.decide(bbs.get(i).x,bbs.get(i).y, 20, 20,hero.x+10, hero.y+10, 80,40)){//��ײ�ж���С��ʵ�����
					JOptionPane.showMessageDialog(null, "you only have "+(hit.n-1)+" life");
					hit.result(bbs,planes,hero,booms);//��ײ����
				}
			}

			for(int i=0;i<shoots.size();i++){//����boss
				if(0<=hit.decide(boss.x,boss.y, 400, 400,shoots.get(i).x, shoots.get(i).y, 100,100)){
					boss.hit(booms);
					totalScore+=50;
				}
			}

			//��ը����
			for(int i=0;i<booms.size();i++){
				booms.get(i).move();//��ը�任
				if(booms.get(i).x==-100){//�Ƴ����ƶ���
					booms.remove(i);
				}
			}
			if(back.y>=0){
				boss.move(bbs,hero.x,hero.y);//boss����
			}
			repaint();
			try {
				Thread.sleep(v);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}

		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(hero.time>=hero.MAX_TIME)
			hero.move(e.getX()-50,e.getY()-50);//�϶���Ч
		repaint();

	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if(hero.time>=hero.MAX_TIME)
			hero.move(e.getX()-50,e.getY()-50);//Ӣ�ۻ���������ƶ�

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
		// TODO �Զ����ɵķ������

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}
}
