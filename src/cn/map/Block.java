package cn.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import cn.coder.PlayGround;

public class Block {
	private int x, y;
	private Mark mark = Mark.Block;// 标识普通砖块0 边界1 蛋2

	public Block(int x, int y, Mark m) {
		super();
		this.x = x;
		this.y = y;
		this.mark = m;
	}

	public Block(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Mark getMark() {
		return mark;
	}

	public void setMark(Mark mark) {
		this.mark = mark;
	}

	public Rectangle getBlockLocation() {
		return new Rectangle(x * PlayGround.BLOCK_SIZE, y
				* PlayGround.BLOCK_SIZE, PlayGround.BLOCK_SIZE,
				PlayGround.BLOCK_SIZE);
	}
    //将Block画出
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		// 蛋
		if (mark == Mark.Egg) {
			g.fillOval(x * PlayGround.BLOCK_SIZE, y * PlayGround.BLOCK_SIZE,
					PlayGround.BLOCK_SIZE, PlayGround.BLOCK_SIZE);

		}
		// 砖
		else if (mark == Mark.Block) {
			g.fillRect(x * PlayGround.BLOCK_SIZE, y * PlayGround.BLOCK_SIZE,
					PlayGround.BLOCK_SIZE, PlayGround.BLOCK_SIZE);
		}
		// 边界
		else {
			g.setColor(Color.BLUE);
			g.fillRect(x * PlayGround.BLOCK_SIZE, y * PlayGround.BLOCK_SIZE,
					PlayGround.BLOCK_SIZE, PlayGround.BLOCK_SIZE);

		}
		g.setColor(c);
	}
}
