package shoot;

import java.util.Vector;

public class Hit {

	public int n=5;//剩余生命
	public Hit() {

	}
	public int decide(int x1,int y1,int w1,int h1,int x2,int y2,int w2,int h2){
		//只在界面内进行撞击判定
		if((x1>=0 && x1+w1<=MyPanel.PANEL_WIDTH  && y1>=0 && y1+h1<=MyPanel.PANEL_HEIGHT )
				&&(x2>=0 && x2+w2<=MyPanel.PANEL_WIDTH  && y2>=0 && y2+h2<=MyPanel.PANEL_HEIGHT)){
			if(x2<=x1+w1 && x2+w2>=x1 && y1+h1>=y2 && y2+h2>=y1){//撞击判定
				return n;
			}
			else	return -10;
		}else	return -10;
	}
	//英雄机处理
	public void result(Vector<EnemyShoot>bbs,Vector<EnemyPlane>planes,HeroPlane hero,Vector<Boom> booms){			
		//传送撞击位置，构建爆炸动图
		booms.add(new Boom(hero.x,hero.y));
		hero.y=MyPanel.PANEL_HEIGHT;
		hero.afterHit();//重新入场
		hero.time=0;
		for(int k=0;k<MyPanel.MAX_ENEMY_PLANE_NUMBER;k++){
			booms.add(new Boom(planes.get(k).x,planes.get(k).y));
			planes.get(k).y=MyPanel.PANEL_HEIGHT;//移除敌方所有飞机
		}
		bbs.clear();//移除屏幕上的子弹
		n--;//五条命
		if(n==0){
			System.exit(0);//结束运行
		}
	}
}
