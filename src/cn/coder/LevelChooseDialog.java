package cn.coder;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class LevelChooseDialog extends JDialog {
	int level = 1;
	int diffcult = 1;
	String radiotext = "容易";
	RadioButtonListener r = new RadioButtonListener();

	public int getDiffcult() {
		if (radiotext.equals("容易")) {
			diffcult = 3;
		}
		if (radiotext.equals("中等")) {
			diffcult = 2;
		}
		if (radiotext.equals("难")) {
			diffcult = 1;
		}

		return diffcult;
	}

	public void setDiffcult(int diffcult) {
		this.diffcult = diffcult;
	}

	public LevelChooseDialog(JFrame owner, boolean modal) {
		super(owner, modal);
		// TODO Auto-generated constructor stub
		this.setTitle("关卡选择");
		this.setBounds(200, 200, 250, 250);
		JPanel contentPane = new JPanel();
		this.setContentPane(contentPane);

		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 10));
		JLabel label = new JLabel("地图:");
		contentPane.add(label);
		final JComboBox comboBox = new JComboBox();
		comboBox.setSize(200, 100);
		comboBox.addItem("第一关");
		comboBox.addItem("第二关");
		comboBox.addItem("第三关");
		contentPane.add(comboBox);
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (ItemEvent.SELECTED == e.getStateChange()) { // 这个判断是选择只会得到一个结果，如果没有判断，会得到两个相同的值，从而获取不到所要选中的值。。
					level = comboBox.getSelectedIndex() + 1;
				}

			}
		});

		label = new JLabel("难度:");
		contentPane.add(label);
		JRadioButton radioButton1 = new JRadioButton("容易");// 创建单选按钮
		radioButton1.addActionListener(r);
		contentPane.add(radioButton1);// 应用单选按钮
		JRadioButton radioButton2 = new JRadioButton("中等");// 创建单选按钮
		radioButton2.addActionListener(r);
		contentPane.add(radioButton2);// 应用单选按钮
		JRadioButton radioButton3 = new JRadioButton("难");// 创建单选按钮
		radioButton3.addActionListener(r);
		contentPane.add(radioButton3);// 应用单选按钮
		ButtonGroup group = new ButtonGroup();// 创建单选按钮组
		group.add(radioButton1);// 将radioButton1增加到单选按钮组中
		group.add(radioButton2);// 将radioButton2增加到单选按钮组中
		group.add(radioButton3);// 将radioButton3增加到单选按钮组中

		this.setVisible(true);
	}

	public LevelChooseDialog() {

	}

	class RadioButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JRadioButton temp = (JRadioButton) arg0.getSource();
			if (temp.isSelected()) {
				radiotext = temp.getText();
			}

		}
	}
}
