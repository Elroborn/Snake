package cn.map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import cn.coder.PlayGround;

public class Map3 extends Map {

	// 添加列行 第y行 第 x列 长为 l
	public void addRow(int y, int x, int l) {
		for (int k = 0; k < l; k++) {
			// 加砖块时 砖块的x在变因为是加的行，所以行不变列递增
			block.add(new Block(x + k, y));
		}

	}

	// 添加列 第y行 第 x列 长为 l
	public void addCol(int y, int x, int l) {
		for (int k = 0; k < l; k++) {
			// 加砖块时 砖块的y在变因为是加的列，所以列不变行递增
			block.add(new Block(x, y + k));
		}
	}

	// 初始化时添加所有Block
	public Map3() {

		// 添加障碍物 i 行第 j 列 长 l
		block = new ArrayList<Block>();
		addCol(5, 15, 13);
		addCol(22, 15, 9);
		addCol(17, 7, 5);
		addCol(23, 7, 6);
		// 添加蛋
		block.add(new Block(8, 4, Mark.Egg));
		block.add(new Block(14, 13, Mark.Egg));
		block.add(new Block(5, 24, Mark.Egg));
		block.add(new Block(22, 7, Mark.Egg));
	}

	// 将地图画出
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(new Color(216, 124, 21));
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
