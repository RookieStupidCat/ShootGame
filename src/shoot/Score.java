package shoot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score extends FlyingObject {

	public Score(int x, int y, int width, int height, int xStep, int yStep) {
		super(x, y, width, height, xStep, yStep);
		// TODO �Զ����ɵĹ��캯�����
	}

	@Override
	public void move() {
		
		
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("",Font.BOLD,20));
		g.drawString("100", x, y);
		
	}

}
