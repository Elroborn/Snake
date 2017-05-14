package cn.coder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//蛇的身体节点
public class Node {
	Dir dir = Dir.LEFT;// 每个节点的方向
	int x, y;// 蛇的位置
	Node pre = null;// 每个节点的前一个节点
	Node next = null;// 每个节点的后一个节点

	public Node(int x, int y, Dir dir) {
		super();
		this.dir = dir;
		this.x = x;
		this.y = y;
	}
   //将节点画出
	public void draw(Graphics g) {
		Color c = g.getColor();//与最后一个g.setColor(c);一起使用保护颜色现场
		g.setColor(Color.BLACK);
		//每个节点用矩形标识
		g.fillRect(PlayGround.BLOCK_SIZE * x, PlayGround.BLOCK_SIZE * y,
				PlayGround.BLOCK_SIZE, PlayGround.BLOCK_SIZE);
		g.setColor(c);
	}

	public Rectangle getNodeLocation() {
		return new Rectangle(x * PlayGround.BLOCK_SIZE, y
				* PlayGround.BLOCK_SIZE, PlayGround.BLOCK_SIZE,
				PlayGround.BLOCK_SIZE);
	}
}
