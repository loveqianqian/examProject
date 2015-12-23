package org.marquee.exam.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

//TODO 9
public class WelcomeWindow extends JWindow{
	/**
	 * 
	 */
	private static final long serialVersionUID = 100545L;

	/** 408*216*/
	/** …¡∆µΩÁ√Ê*/
	public WelcomeWindow() {
		initialize();
	}

	private void initialize() {
		setSize(408, 216);
		JPanel panel=new JPanel(new BorderLayout());
		ImageIcon ico=new ImageIcon(getClass().getResource("welcomeWindows.png"));
		JLabel label=new JLabel(ico);
		panel.add(BorderLayout.CENTER,label);
		panel.setBorder(new LineBorder(Color.GRAY));
		setContentPane(panel);
	}
	
	public void showGui(){
		setVisible(true);
	}
	
}
