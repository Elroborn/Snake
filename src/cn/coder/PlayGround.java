package cn.coder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import cn.map.Map;
import cn.map.Map1;
import cn.map.Map2;
import cn.map.Map3;

//������
public class PlayGround extends JFrame {
	static int num = 0;
	JMenuBar menubar;
	private Map map;
	Snake snake;
	public static final int ROW = 30;// ����
	public static final int COL = 30;// ����
	public static final int BLOCK_SIZE = 15;// ÿ�������С
	boolean start = true;
	boolean pause = false;
	Image offScreenImage = null;
	FlashThread flashThread = new FlashThread(this);
	Thread flash = new Thread(flashThread);
	KeyListen keyListen = new KeyListen(this);
	int speed = 5;
	int level = 1;
	Font font = new Font("����", Font.BOLD, 18);

	// ���캯�����Գ�������һЩ��ʼ��
	public PlayGround() {
		num++;
		snake = new Snake();// ��ʼ��һ����
		this.setLocation(200, 200);// ���ô���λ��
		this.setResizable(false);// ���ڴ�С���ɱ�
		this.setSize(COL * BLOCK_SIZE, ROW * BLOCK_SIZE);// ���ڵĴ�С���������õ�

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// ���رհ�ť����
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				num--;
				System.exit(0);
			}
		});
	}

	// д��Щʵʱˢ�µĴ�����米�������ߵ�λ��
	@Override
	public void paint(Graphics g) {
		snake.eat(map);// ���Ƿ�Ե��˹���
		snake.isAlive(map);// �ػ������Ƿ����
		map.draw(g);// �ػ���ͼ
		snake.draw(g);// �ػ���
		g.setColor(Color.YELLOW);
		g.setFont(font);
		g.drawString("score :" + snake.totalscore, 10, 60);
	}

	@Override
	public void update(Graphics g) {
		// ������˸˫����
		if (offScreenImage == null) {
			offScreenImage = this.createImage(COL * BLOCK_SIZE, ROW
					* BLOCK_SIZE);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	public void whenYouDie() {
		JOptionPane.showMessageDialog(null, "                   ��սʧ��\n"
				+ "                   ����:" + snake.totalscore, "ʧ��",
				JOptionPane.CLOSED_OPTION);
	}

	public void nextLevel() {
		int tem = speed - 1;
		if (tem <= 0) {
			speed = 1;
		} else {
			speed = tem;
		}

		tem = level + 1;
		if (tem >= 4) {
			level = 1;
		} else {
			level = tem;
		}
		switch (level) {
		case 1:
			map = new Map1();
			;
			break;
		case 2:
			map = new Map2();
			;
			break;
		case 3:
			map = new Map3();
			;
			break;
		}
	}

	// ��ʼ��Ϸ��������Ϸ���
	// ҲдһЩ��ʼ���Ķ���
	public void startGame() {
		LevelChooseDialog dialog = new LevelChooseDialog(this, true);
		speed = dialog.getDiffcult();
		level = dialog.level;
		switch (level) {
		case 1:
			map = new Map1(); // ��ʼ����ͼ
			;
			break;
		case 2:
			map = new Map2();
			;
			break;
		case 3:
			map = new Map3();
			;
			break;
		}

		addMenu();

		this.setVisible(true);// ʹ������ʾ
		this.addKeyListener(keyListen);
		flash.start();
	}

	private void addMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("�˵�"), menu2 = new JMenu("�ٶ�"), menu3 = new JMenu(
				"��ͼ"), menu4 = new JMenu("����");
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menuBar.add(menu4);
		// ��ӡ��˵����ĸ��˵���
		// JMenuItem item11 = new JMenuItem("��Ϸ˫��");
		// item11.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// // TODO Auto-generated method stub
		// new PlayGround().startGame();
		//
		// }
		// });
		JMenuItem item1 = new JMenuItem("���¿�ʼ");
		item1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				whenYouDie();
				new PlayGround().startGame();
			}
		});
		JMenuItem item2 = new JMenuItem("����");
		item2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pause = false;
			}
		});
		menu1.add(item1);
		menu1.add(item2);
		// menu1.add(item11);

		// ��ӡ��ٶȡ��˵��ĸ��˵���
		JMenuItem item3 = new JMenuItem("����");
		JMenuItem item4 = new JMenuItem("�е�");
		JMenuItem item5 = new JMenuItem("��");
		item3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				speed = 3;
				pause = false;
				repaint();
			}
		});
		item4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				speed = 2;
				pause = false;
				repaint();
			}
		});
		item5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				speed = 1;
				pause = false;
				repaint();
			}
		});
		menu2.add(item3);
		menu2.add(item4);
		menu2.add(item5);

		// ��ӡ���ͼ���˵��ĸ��˵���

		JMenuItem item6 = new JMenuItem("Map1");
		JMenuItem item7 = new JMenuItem("Map2");
		JMenuItem item8 = new JMenuItem("Map3");
		item6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				map = new Map1();
				pause = false;
				repaint();
			}
		});
		item7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				map = new Map2();
				pause = false;
				repaint();
			}
		});
		item8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				map = new Map3();
				pause = false;
				repaint();
			}
		});
		menu3.add(item6);
		menu3.add(item7);
		menu3.add(item8);
		// ��ӡ����ڡ��˵��ĸ��˵���
		JMenuItem item9 = new JMenuItem("����");
		JMenuItem item10 = new JMenuItem("���ڱ���Ϸ");
		item9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new HelpDialog();
				repaint();
			}
		});
		item10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AboutDialog();
				repaint();
			}
		});

		menu4.add(item9);
		menu4.add(item10);

		this.setJMenuBar(menuBar);
	}
}

// ˢ���߳� �����ߵ�λ��
class FlashThread implements Runnable {
	PlayGround playground;

	public FlashThread(PlayGround playground) {
		super();
		this.playground = playground;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (playground.start && playground.snake.alive) {
			if (playground.pause)
				continue;
			if (playground.snake.score >= 40) {
				playground.snake.score = 0;
				playground.nextLevel();
			}
			playground.repaint();
			try {
				Thread.sleep(100 * playground.speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (playground.snake.alive == false) {
			playground.dispose();
			playground.whenYouDie();
			new PlayGround().startGame();
		}

	}
}

// ���̼���
class KeyListen extends KeyAdapter {
	PlayGround playground;

	public KeyListen(PlayGround playground) {
		this.playground = playground;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_SPACE:
			playground.pause = !playground.pause;
			break;
		case KeyEvent.VK_LEFT:
			if (playground.snake.head.dir != Dir.RIGHT)
				playground.snake.head.dir = Dir.LEFT;
			break;
		case KeyEvent.VK_UP:
			if (playground.snake.head.dir != Dir.DOWN)
				playground.snake.head.dir = Dir.UP;
			break;
		case KeyEvent.VK_RIGHT:
			if (playground.snake.head.dir != Dir.LEFT)
				playground.snake.head.dir = Dir.RIGHT;
			break;
		case KeyEvent.VK_DOWN:
			if (playground.snake.head.dir != Dir.UP)
				playground.snake.head.dir = Dir.DOWN;
			break;
		}

	}

}