package shoot;

public abstract  class FlyingObject {//������
	public int x;
	public int y;//��������
	public int width,height;//���󻭵ĸ߶�
	public int xStep,yStep;//�ƶ�ʱx��y�������������
	
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
	
	public abstract void move();//���󷽷�
		
	
}
