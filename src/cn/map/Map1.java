package cn.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import cn.coder.PlayGround;

public class Map1 extends Map {

	// ������� ��y�� �� x�� ��Ϊ l
	public void addRow(int y, int x, int l) {
		for (int k = 0; k < l; k++) {
			// ��ש��ʱ ש���x�ڱ���Ϊ�Ǽӵ��У������в����е���
			block.add(new Block(x + k, y));
		}

	}

	// ����� ��y�� �� x�� ��Ϊ l
	public void addCol(int y, int x, int l) {
		for (int k = 0; k < l; k++) {
			// ��ש��ʱ ש���y�ڱ���Ϊ�Ǽӵ��У������в����е���
			block.add(new Block(x, y + k));
		}
	}

	// ��ʼ��ʱ�������Block
	public Map1() {

		// ����ϰ��� i �е� j �� �� l
		block = new ArrayList<Block>();
		addRow(16, 1, 13);
		addRow(16, 17, 14);
		addCol(3, 15, 8);
		addCol(22, 15, 9);
		addCol(17, 7, 5);
		addCol(23, 7, 6);
		// ��ӵ�
		block.add(new Block(8, 4, Mark.Egg));
		block.add(new Block(14, 13, Mark.Egg));
		block.add(new Block(5, 24, Mark.Egg));
		block.add(new Block(22, 7, Mark.Egg));
	}

	// ����ͼ����
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(new Color(41, 52, 74));

		// ������
		g.fillRect(0, 0, PlayGround.COL * PlayGround.BLOCK_SIZE, PlayGround.ROW
				* PlayGround.BLOCK_SIZE);
		// ���ϰ�
		for (Block b : block) {
			b.draw(g);
		}
		g.setColor(c);
	}
}
