package shoot;

public abstract  class FlyingObject {//抽象类
	public int x;
	public int y;//画的坐标
	public int width,height;//对象画的高度
	public int xStep,yStep;//移动时x，y坐标的增长幅度
	
	public FlyingObject(int x, int y, int width, int height, int xStep, int yStep) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xStep = xStep;
		this.yStep = yStep;
	}

//	public void draw(Graphics g){
//		g.drawImage(img, x, y, width,height,null);
//	}
	
	public abstract void move();//抽象方法
		
	
}
