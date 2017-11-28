package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelpDialog extends JDialog{
	private JLabel label;
	private JButton btn;
	HelpDialog(){
		label=new JLabel("<html><body>按住鼠标可以拖动图形<br>R键删除图形<br>A键伸长图形<br>S键缩短图形<br>L键细化图形<br>G键加粗图形<br></body></html>");
		btn = new JButton("关闭");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		setSize(300, 200);
		setModal(true);
		setTitle("用户指南");
		add(label,BorderLayout.CENTER);
		add(new JLabel("<html><body><br></body></html>"),BorderLayout.WEST);
		add(btn,BorderLayout.SOUTH);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
