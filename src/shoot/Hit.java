package shoot;

import java.util.Vector;

public class Hit {

	public int n=5;//ʣ������
	public Hit() {

	}
	public int decide(int x1,int y1,int w1,int h1,int x2,int y2,int w2,int h2){
		//ֻ�ڽ����ڽ���ײ���ж�
		if((x1>=0 && x1+w1<=MyPanel.PANEL_WIDTH  && y1>=0 && y1+h1<=MyPanel.PANEL_HEIGHT )
				&&(x2>=0 && x2+w2<=MyPanel.PANEL_WIDTH  && y2>=0 && y2+h2<=MyPanel.PANEL_HEIGHT)){
			if(x2<=x1+w1 && x2+w2>=x1 && y1+h1>=y2 && y2+h2>=y1){//ײ���ж�
				return n;
			}
			else	return -10;
		}else	return -10;
	}
	//Ӣ�ۻ�����
	public void result(Vector<EnemyShoot>bbs,Vector<EnemyPlane>planes,HeroPlane hero,Vector<Boom> booms){			
		//����ײ��λ�ã�������ը��ͼ
		booms.add(new Boom(hero.x,hero.y));
		hero.y=MyPanel.PANEL_HEIGHT;
		hero.afterHit();//�����볡
		hero.time=0;
		for(int k=0;k<MyPanel.MAX_ENEMY_PLANE_NUMBER;k++){
			booms.add(new Boom(planes.get(k).x,planes.get(k).y));
			planes.get(k).y=MyPanel.PANEL_HEIGHT;//�Ƴ��з����зɻ�
		}
		bbs.clear();//�Ƴ���Ļ�ϵ��ӵ�
		n--;//������
		if(n==0){
			System.exit(0);//��������
		}
	}
}
