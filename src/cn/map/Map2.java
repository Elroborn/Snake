package cn.map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import cn.coder.PlayGround;

public class Map2 extends Map {
	public Map2() {
		// 添加障碍物5行第5列 长10
		block = new ArrayList<Block>();
		for (int i = 0; i < 10; i++) {
			block.add(new Block(28, 5 + i));
		}
		// 添加蛋
		block.add(new Block(10, 7, Mark.Egg));
		block.add(new Block(11, 8, Mark.Egg));
		block.add(new Block(15, 8, Mark.Egg));
		block.add(new Block(11, 10, Mark.Egg));
		block.add(new Block(17, 9, Mark.Egg));
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Color c = g.getColor();
		g.setColor(new Color(162, 229, 98));
		// 画背景
		g.fillRect(0, 0, PlayGround.COL * PlayGround.BLOCK_SIZE, PlayGround.ROW
				* PlayGround.BLOCK_SIZE);
		// 画障碍
		for (Block b : block) {
			b.draw(g);
		}
		g.setColor(c);

	}

}
