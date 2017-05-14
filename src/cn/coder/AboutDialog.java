package cn.coder;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutDialog extends JDialog {
	public AboutDialog() {
		this.setTitle("πÿ”⁄");
		this.setBounds(350, 300, 200, 100);
		JPanel contentPane = new JPanel();
		this.setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 10));
		JLabel label = new JLabel("01410251Y13 ÕÙ—«∫Ω");
		contentPane.add(label);
		this.setVisible(true);
	}

}
