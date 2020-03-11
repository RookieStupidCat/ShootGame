package shoot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Boom {
	public Image img;//ͼƬ����
	public int x;
	public int y;//��������
	public int width=70, height=70;
	public int xStep,yStep;//�ƶ�ʱx��y�������������
	int n=0;//��ͼ���

	public Boom(int x, int y) {//��ըλ�úͳ�ʼͼ
		img=Toolkit.getDefaultToolkit().getImage("src/bomb_0.gif");
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height,null);
	}

	public void move(){//�����Ƿ����ײ���ж�
		if( n%10==0){//��ͼ�л����ٶȿ���
			img=Toolkit.getDefaultToolkit().getImage("src/bomb_"+n/10+".gif");
		}
//		System.out.println(n);
		if(n>=40){//����һ���ֹͣ���Ƴ���ըЧ��
			n=0;//�����л��ж�
			x=-100;
			
		}
		else{
			n++;		
			x+=xStep;
			y+=yStep;
			//δ���������
		}
	}

	public void move(EnemyPlane enemyPlane) {
		xStep=enemyPlane.xStep;
		yStep=enemyPlane.yStep;
	}

}
