package org.marquee.exam.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import org.marquee.exam.service.ClientContext;
import org.marquee.exam.service.User;

public class MenuFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private JLabel info;
	private ClientContext context;
	
	//TODO 3-1.c
	public MenuFrame(ClientContext context) {
		this();//initialize();is ok!!
		this.context=context;
	}

	/**
	 * @param args
	 */
	// TODO 1-2.1
	public MenuFrame() {
		initialize();
	}

	// TODO 1-2.2
	private void initialize() {
		setTitle("在线测评系统");
		setSize(400, 300);
		setContentPane(creatMenuPanel());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//TODO 5-2
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				context.exit(MenuFrame.this);
			}
		});
		
	}

	// TODO 1-2.2.1
	private JPanel creatMenuPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		ImageIcon icon = new ImageIcon(getClass().getResource("welcome.png"));
		panel.add(BorderLayout.NORTH, new JLabel(icon));
		panel.add(BorderLayout.CENTER, creatMenuCenterPanel());
		panel.add(BorderLayout.SOUTH, new JLabel("志伟版————测评系统，盗版必究",
				JLabel.RIGHT));
		return panel;
	}

	// TODO 1-2.2.1.1
	private JPanel creatMenuCenterPanel() {
		JPanel menuCentPanel = new JPanel(new BorderLayout());
		info=new JLabel("欢迎，刁志伟考生参加考试!!",JLabel.CENTER);
		menuCentPanel.add(BorderLayout.NORTH, info);
		menuCentPanel.add(BorderLayout.CENTER, creatChoicePanel());
		return menuCentPanel;
	}

	// TODO 1-2.2.1.1-1
	private JPanel creatChoicePanel() {
		JPanel ChoicePanel = new JPanel(new FlowLayout());
		ImageIcon starticon=new ImageIcon(getClass().getResource("start.png"));
		ImageIcon gradeicon=new ImageIcon(getClass().getResource("grade.png"));
		ImageIcon ruleicon=new ImageIcon(getClass().getResource("rule.png"));
		ImageIcon exiticon=new ImageIcon(getClass().getResource("exit.png"));
		
		JButton startButton=new JButton("开始",starticon);
		//TODO 8.1
		startButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				context.start(MenuFrame.this);
			}
		});
		JButton gradeButton=new JButton("分数",gradeicon);
		//TODO 10.2
		gradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				context.grade(MenuFrame.this);
			}
		});
		JButton ruleButton=new JButton("考试规则",ruleicon);
		JButton exitButton=new JButton("离开",exiticon);
		//TODO 5-2
		exitButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				context.exit(MenuFrame.this);
			}
		});
		
		startButton.setVerticalTextPosition(JButton.BOTTOM);
		startButton.setHorizontalTextPosition(JButton.CENTER);
		
		gradeButton.setVerticalTextPosition(JButton.BOTTOM);
		gradeButton.setHorizontalTextPosition(JButton.CENTER);
		
		ruleButton.setVerticalTextPosition(JButton.BOTTOM);
		ruleButton.setHorizontalTextPosition(JButton.CENTER);
		
		exitButton.setVerticalTextPosition(JButton.BOTTOM);
		exitButton.setHorizontalTextPosition(JButton.CENTER);
		
		ChoicePanel.add(startButton);
		ChoicePanel.add(gradeButton);
		ChoicePanel.add(ruleButton);
		ChoicePanel.add(exitButton);
		return ChoicePanel;
	}

	// TODO 1-2.3
	public void showGUI() {
		setVisible(true);
	}
	//TODO 3-1.c
	public  void updateShowView(){
		User u=context.getLoginUser();
		String str="您好，欢迎"+u.getName()+"进入考试系统";
		info.setText(str);
	}
}
