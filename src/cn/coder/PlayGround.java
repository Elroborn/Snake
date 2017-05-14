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

//场地类
public class PlayGround extends JFrame {
	static int num = 0;
	JMenuBar menubar;
	private Map map;
	Snake snake;
	public static final int ROW = 30;// 行数
	public static final int COL = 30;// 列数
	public static final int BLOCK_SIZE = 15;// 每个网格大小
	boolean start = true;
	boolean pause = false;
	Image offScreenImage = null;
	FlashThread flashThread = new FlashThread(this);
	Thread flash = new Thread(flashThread);
	KeyListen keyListen = new KeyListen(this);
	int speed = 5;
	int level = 1;
	Font font = new Font("宋体", Font.BOLD, 18);

	// 构造函数，对场地类做一些初始化
	public PlayGround() {
		num++;
		snake = new Snake();// 初始化一条蛇
		this.setLocation(200, 200);// 设置窗口位置
		this.setResizable(false);// 窗口大小不可变
		this.setSize(COL * BLOCK_SIZE, ROW * BLOCK_SIZE);// 窗口的大小由网格计算得到

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 将关闭按钮开启
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				num--;
				System.exit(0);
			}
		});
	}

	// 写那些实时刷新的代码比如背景。。蛇的位置
	@Override
	public void paint(Graphics g) {
		snake.eat(map);// 蛇是否吃到了果子
		snake.isAlive(map);// 重画后蛇是否活着
		map.draw(g);// 重画地图
		snake.draw(g);// 重画蛇
		g.setColor(Color.YELLOW);
		g.setFont(font);
		g.drawString("score :" + snake.totalscore, 10, 60);
	}

	@Override
	public void update(Graphics g) {
		// 避免闪烁双缓冲
		if (offScreenImage == null) {
			offScreenImage = this.createImage(COL * BLOCK_SIZE, ROW
					* BLOCK_SIZE);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	public void whenYouDie() {
		JOptionPane.showMessageDialog(null, "                   挑战失败\n"
				+ "                   分数:" + snake.totalscore, "失败",
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

	// 开始游戏，整个游戏入口
	// 也写一些初始化的东西
	public void startGame() {
		LevelChooseDialog dialog = new LevelChooseDialog(this, true);
		speed = dialog.getDiffcult();
		level = dialog.level;
		switch (level) {
		case 1:
			map = new Map1(); // 初始化地图
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

		this.setVisible(true);// 使窗口显示
		this.addKeyListener(keyListen);
		flash.start();
	}

	private void addMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("菜单"), menu2 = new JMenu("速度"), menu3 = new JMenu(
				"地图"), menu4 = new JMenu("关于");
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menuBar.add(menu4);
		// 添加“菜单”的各菜单项
		// JMenuItem item11 = new JMenuItem("游戏双开");
		// item11.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// // TODO Auto-generated method stub
		// new PlayGround().startGame();
		//
		// }
		// });
		JMenuItem item1 = new JMenuItem("重新开始");
		item1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				whenYouDie();
				new PlayGround().startGame();
			}
		});
		JMenuItem item2 = new JMenuItem("继续");
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

		// 添加“速度”菜单的各菜单项
		JMenuItem item3 = new JMenuItem("容易");
		JMenuItem item4 = new JMenuItem("中等");
		JMenuItem item5 = new JMenuItem("难");
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

		// 添加“地图”菜单的各菜单项

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
		// 添加“关于”菜单的各菜单项
		JMenuItem item9 = new JMenuItem("帮助");
		JMenuItem item10 = new JMenuItem("关于本游戏");
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

// 刷新线程 更新蛇的位置
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

// 键盘监听
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