package cn.coder;

import java.awt.Graphics;
import java.awt.Rectangle;

import cn.map.Block;
import cn.map.Map;
import cn.map.Mark;

public class Snake {
	// ��ͷ����Ĭ��λ����Ĭ�Ϸ���
	Node head = new Node(20, 15, Dir.LEFT);
	// ��ʼ��β����ͷ��һ��
	Node tail = head;
	int size = 1;
	int score = 0;
	// �ߵ�����״̬
	boolean alive = true;
	int totalscore = 0;

	// �ߵ��˶���Ҫ��ɾβ ��ͷ
	private void deleteTail() {
		tail = tail.pre;
		size--;
		tail.next = null;
	}

	// ��ͷ��λ�ü�һ���ڵ�
	private void addHead() {
		Node n = null;
		// ����ͷ�ķ���ȷ��λ��
		switch (head.dir) {
		case LEFT:
			n = new Node(head.x - 1, head.y, head.dir);
			;
			break;
		case UP:
			n = new Node(head.x, head.y - 1, head.dir);
			break;
		case RIGHT:
			n = new Node(head.x + 1, head.y, head.dir);
			;
			break;
		case DOWN:
			n = new Node(head.x, head.y + 1, head.dir);
			;
			break;
		}
		// n��Ϊ��ͷ
		n.next = head;
		head.pre = n;
		// ���¸�ͷ��ֵ
		head = n;
		size++;
	}

	public void snakeMove() {
		addHead();
		deleteTail();
	}

	// ������ʾ����
	public void draw(Graphics g) {
		if (size <= 0)
			return;
		snakeMove();
		// ��ʾ���нڵ�
		for (Node n = head; n != null; n = n.next) {
			n.draw(g);
		}

	}

	public Rectangle getHeadLocation() {
		return new Rectangle(head.x * PlayGround.BLOCK_SIZE, head.y
				* PlayGround.BLOCK_SIZE, PlayGround.BLOCK_SIZE,
				PlayGround.BLOCK_SIZE);
	}

	public void isAlive(Map m) {
		for (Block b1 : m.block) {
			if (this.getHeadLocation().intersects(b1.getBlockLocation())) {
				alive = false;
			}
		}
		Node n = head.next;
		while (n != null) {
			if (n.getNodeLocation().intersects(this.getHeadLocation())) {
				alive = false;
			}
			n = n.next;
		}

	}

	public void eat(Map m) {
		// m.block.remove(0);
		for (int i = 0; i < m.block.size(); i++) {
			Block b = m.block.get(i);
			if (b.getMark() == Mark.Egg
					&& this.getHeadLocation().intersects(b.getBlockLocation())) {
				m.block.set(i, new Block(b.getX(), b.getY(), Mark.Block));
				this.score += 10;
				totalscore += 10;
				this.addHead();
			}
		}

	}
}
