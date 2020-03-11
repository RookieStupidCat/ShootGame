package shoot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Goad {
	public Image img;//图片对象
	public int x;
	public int y;//画的坐标
	public Goad() {
		img=Toolkit.getDefaultToolkit().getImage("src/pp.png");
	}
	public void draw(Graphics g){
		g.drawImage(img, x, y, 120,120,null);		
	}
	public void move(){
		
	}
}
