package cn.coder;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HelpDialog extends JFrame {
	JTextArea text = new JTextArea();

	public HelpDialog() {
		this.setTitle("帮助");
		this.setBounds(350, 300, 400, 400);
		StringBuffer sb = new StringBuffer();
		sb.append("本游戏名字为贪吃蛇\n");
		sb.append("游戏操作：上下左右控制蛇的运动\n");
		sb.append("游戏菜单：在地图和速度中可以更换地图与速度\n");
		sb.append("空格键为暂停\n");
		sb.append("在打开菜单前，需要先暂停游戏，否则菜单没有作用\n");
		sb.append("友情提示：贪吃蛇在吃完果子后果子会变为障碍物\n");
		text.setText(sb.toString());

		text.setEditable(false);
		this.add(text);
		this.setVisible(true);
	}

}
