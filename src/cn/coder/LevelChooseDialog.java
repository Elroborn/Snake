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
	String radiotext = "����";
	RadioButtonListener r = new RadioButtonListener();

	public int getDiffcult() {
		if (radiotext.equals("����")) {
			diffcult = 3;
		}
		if (radiotext.equals("�е�")) {
			diffcult = 2;
		}
		if (radiotext.equals("��")) {
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
		this.setTitle("�ؿ�ѡ��");
		this.setBounds(200, 200, 250, 250);
		JPanel contentPane = new JPanel();
		this.setContentPane(contentPane);

		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 10));
		JLabel label = new JLabel("��ͼ:");
		contentPane.add(label);
		final JComboBox comboBox = new JComboBox();
		comboBox.setSize(200, 100);
		comboBox.addItem("��һ��");
		comboBox.addItem("�ڶ���");
		comboBox.addItem("������");
		contentPane.add(comboBox);
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (ItemEvent.SELECTED == e.getStateChange()) { // ����ж���ѡ��ֻ��õ�һ����������û���жϣ���õ�������ͬ��ֵ���Ӷ���ȡ������Ҫѡ�е�ֵ����
					level = comboBox.getSelectedIndex() + 1;
				}

			}
		});

		label = new JLabel("�Ѷ�:");
		contentPane.add(label);
		JRadioButton radioButton1 = new JRadioButton("����");// ������ѡ��ť
		radioButton1.addActionListener(r);
		contentPane.add(radioButton1);// Ӧ�õ�ѡ��ť
		JRadioButton radioButton2 = new JRadioButton("�е�");// ������ѡ��ť
		radioButton2.addActionListener(r);
		contentPane.add(radioButton2);// Ӧ�õ�ѡ��ť
		JRadioButton radioButton3 = new JRadioButton("��");// ������ѡ��ť
		radioButton3.addActionListener(r);
		contentPane.add(radioButton3);// Ӧ�õ�ѡ��ť
		ButtonGroup group = new ButtonGroup();// ������ѡ��ť��
		group.add(radioButton1);// ��radioButton1���ӵ���ѡ��ť����
		group.add(radioButton2);// ��radioButton2���ӵ���ѡ��ť����
		group.add(radioButton3);// ��radioButton3���ӵ���ѡ��ť����

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
