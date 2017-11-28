package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import command.Command;
import main.MainFrame;

public class ColorButton extends JButton{
	private Color color;
	ColorButton(Color color){
		this.color = color;
		setPreferredSize(new Dimension(46,35));
		setOpaque(true);
		setBackground(this.color);
		setBorderPainted(false);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.printPanel.setColor(color);
				if(MainFrame.printPanel.getCommand()==Command.SELECT){
					MainFrame.printPanel.changeColor();
				};					
			}
		});
	}
}
