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
		this.setTitle("����");
		this.setBounds(350, 300, 400, 400);
		StringBuffer sb = new StringBuffer();
		sb.append("����Ϸ����Ϊ̰����\n");
		sb.append("��Ϸ�������������ҿ����ߵ��˶�\n");
		sb.append("��Ϸ�˵����ڵ�ͼ���ٶ��п��Ը�����ͼ���ٶ�\n");
		sb.append("�ո��Ϊ��ͣ\n");
		sb.append("�ڴ򿪲˵�ǰ����Ҫ����ͣ��Ϸ������˵�û������\n");
		sb.append("������ʾ��̰�����ڳ�����Ӻ���ӻ��Ϊ�ϰ���\n");
		text.setText(sb.toString());

		text.setEditable(false);
		this.add(text);
		this.setVisible(true);
	}

}
