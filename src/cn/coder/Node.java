package cn.coder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//�ߵ�����ڵ�
public class Node {
	Dir dir = Dir.LEFT;// ÿ���ڵ�ķ���
	int x, y;// �ߵ�λ��
	Node pre = null;// ÿ���ڵ��ǰһ���ڵ�
	Node next = null;// ÿ���ڵ�ĺ�һ���ڵ�

	public Node(int x, int y, Dir dir) {
		super();
		this.dir = dir;
		this.x = x;
		this.y = y;
	}
   //���ڵ㻭��
	public void draw(Graphics g) {
		Color c = g.getColor();//�����һ��g.setColor(c);һ��ʹ�ñ�����ɫ�ֳ�
		g.setColor(Color.BLACK);
		//ÿ���ڵ��þ��α�ʶ
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
