package main;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import command.Command;
import panel.PaintPanel;

public class MainFrame extends JFrame {
	
	public static PaintPanel printPanel;
	private JPanel btnPanel; 
	private JButton newButton;
	private JButton saveButton;
	private JButton openButton;
	private JButton selectButton;
	private JButton lineButton;
	private JButton rectButton;
	private JButton circleButton;
	private JButton inputButton;
	private JButton helpButton;
	private JPanel colorButton;
	public static MainFrame mainFrame;
	
	public static void main(String[] args) {
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
	
	public MainFrame(){
		super();
		this.setSize(1200,860);
		this.setTitle("Computer-Aided Design");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		ShowGui();
	}

	private void ShowGui() {
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				exitWindowClosing(evt);
			}
		});
		
		printPanel = new PaintPanel();
		printPanel.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				KeyActionPerformed(evt);
			}
		});
		getContentPane().add(printPanel, BorderLayout.CENTER);
		printPanel.setPreferredSize(new Dimension(1000, 860));
		
		btnPanel = new JPanel();
		getContentPane().add(btnPanel, BorderLayout.EAST);
		btnPanel.setPreferredSize(new Dimension(200, 860));
		
		newButton = new JButton();
		btnPanel.add(newButton);
		newButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("new.png")));
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				newButtonActionPerformed(evt);
			}
		});
		
		saveButton = new JButton();
		btnPanel.add(saveButton);
		saveButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("save.png")));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				saveButtonActionPerformed(evt);
			}
		});
		
		openButton = new JButton();
		btnPanel.add(openButton);
		openButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("open.png")));
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				openButtonActionPerformed(evt);
			}
		});
		
		selectButton = new JButton();
		btnPanel.add(selectButton);
		selectButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("select.png")));
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				selectButtonActionPerformed(evt);
			}
		});
		
		lineButton = new JButton();
		btnPanel.add(lineButton);
		lineButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("line.png")));
		lineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				lineButtonActionPerformed(evt);
			}
		});
		
		rectButton = new JButton();
		btnPanel.add(rectButton);
		rectButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("rect.png")));
		rectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rectButtonActionPerformed(evt);
			}
		});
		
		circleButton = new JButton();
		btnPanel.add(circleButton);
		circleButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("circle.png")));
		circleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				circleButtonActionPerformed(evt);
			}
		});
		
		inputButton = new JButton();
		btnPanel.add(inputButton);
		inputButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("input.png")));
		inputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				inputButtonActionPerformed(evt);
			}
		});
		
		helpButton = new JButton();
		btnPanel.add(helpButton);
		helpButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("help.png")));
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				helpButtonActionPerformed(evt);
			}
		});
		
		colorButton = new JPanel(new GridLayout(3,4));
		btnPanel.add(colorButton);
		colorButton.add(new ColorButton(Color.black));
		colorButton.add(new ColorButton(Color.blue));
		colorButton.add(new ColorButton(Color.cyan));
		colorButton.add(new ColorButton(Color.darkGray));
		colorButton.add(new ColorButton(Color.gray));
		colorButton.add(new ColorButton(Color.green));
		colorButton.add(new ColorButton(Color.lightGray));
		colorButton.add(new ColorButton(Color.magenta));
		colorButton.add(new ColorButton(Color.pink));
		colorButton.add(new ColorButton(Color.red));
		colorButton.add(new ColorButton(Color.white));
		colorButton.add(new ColorButton(Color.yellow));
	}
	
	private void exitWindowClosing(WindowEvent evt) {
		this.printPanel.exitPaintPanel();
	}
	
	private void newButtonActionPerformed(ActionEvent evt) {
		this.printPanel.newPaintPanel();
	}
	private void saveButtonActionPerformed(ActionEvent evt) {
		this.printPanel.savePaintPanel();
	}
	private void openButtonActionPerformed(ActionEvent evt) {
		this.printPanel.openPaintPanel();
	}
	private void selectButtonActionPerformed(ActionEvent evt) {
		this.printPanel.setCommand(Command.SELECT);
		this.printPanel.repaint();
	}
	private void lineButtonActionPerformed(ActionEvent evt) {
		this.printPanel.setCommand(Command.LINE);
		this.printPanel.repaint();
	}
	private void rectButtonActionPerformed(ActionEvent evt) {
		this.printPanel.setCommand(Command.RECTANGLE);
		this.printPanel.repaint();
	}
	private void circleButtonActionPerformed(ActionEvent evt) {
		this.printPanel.setCommand(Command.CIRCLE);
		this.printPanel.repaint();
	}
	private void inputButtonActionPerformed(ActionEvent evt) {
		String inputString = JOptionPane.showInputDialog("Please input a value:");
		this.printPanel.setFontText(inputString);
		this.printPanel.setCommand(Command.FONT);
		this.printPanel.repaint();
	}
	private void helpButtonActionPerformed(ActionEvent evt) {
		new HelpDialog();
	}
	private void KeyActionPerformed(KeyEvent evt) {
		int code = evt.getKeyCode();
		boolean sign = false;
		if(printPanel.getCommand()==Command.SELECT) sign = true;
        if(sign && code == KeyEvent.VK_R){
        		this.printPanel.deleteShape();
        }
        if(sign && code == KeyEvent.VK_A){
    			this.printPanel.changeSize();
        }
        if(sign && code == KeyEvent.VK_S){
        		this.printPanel.changeSize();
        }
        if(sign && code == KeyEvent.VK_L){
    			this.printPanel.deleteShape();
        }
        if(sign && code == KeyEvent.VK_G){
    			this.printPanel.deleteShape();
        }
	}
}
