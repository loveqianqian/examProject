package org.marquee.exam.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.marquee.exam.service.ClientContext;

//TODO 1-1
public class LoginFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2757087239499436132L;
	
	//TODO 3-1.c
	private JTextField nameField;
	private JPasswordField passwordField;
	private ClientContext context;

	/**
	 * 初始化变量，绘制界面全部组件
	 */
	// TODO 1-1.2
	private void initialize() {
		setTitle("志伟版交通测试系统——登录");
		setSize(300, 200);
		setContentPane(creatContentPane());
		//TODO 5-2
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//调用windowListener（）会调用所有的windowListener方法。所以可以改为调用windowAdapter（）方法
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				context.exit(LoginFrame.this);
			}
		});
	}
	//TODO 3-1.c
	public LoginFrame(ClientContext context) {
		this();
		this.context=context;
	}

	// TODO 1-1.1
	public LoginFrame() {
		initialize();
	}

	/**
	 * 界面显示方法
	 */
	// TODO 1-1.3
	public void showGUI() {
		setVisible(true);
	}

	// TODO 1-1.1.1
	private JPanel creatContentPane() {
		JPanel panel = new JPanel(new BorderLayout(0,10));
		panel.setBorder(new EmptyBorder(14, 14, 14, 14));
		panel.add(BorderLayout.NORTH, new JLabel("信息登录界面",JLabel.CENTER));
		panel.add(BorderLayout.CENTER, creatPwdNamePanel());
		panel.add(BorderLayout.SOUTH, creatBottomPanel());
		return panel;
	}

	// TODO 1-1.1.1.1
	private JPanel creatPwdNamePanel() {
		JPanel pwdNamepanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new GridLayout(2, 1,0,6));
		pwdNamepanel.add(BorderLayout.NORTH, topPanel);
		topPanel.add(creatTopNamepanel());
		topPanel.add(creatBottomPwdpanel());
		return pwdNamepanel;
	}

	// TODO 1-1.1.1.1-1
	private JPanel creatTopNamepanel() {
		JPanel TopNamepanel = new JPanel(new BorderLayout(5,0));
		JLabel nameLable = new JLabel("编号:");
		//TODO 3-1.c
		nameField = new JTextField();
		TopNamepanel.add(BorderLayout.WEST, nameLable);
		TopNamepanel.add(BorderLayout.CENTER, nameField);
		return TopNamepanel;
	}

	// TODO 1-1.1.1.1-2
	private JPanel creatBottomPwdpanel() {
		JPanel BottomPwdpanel = new JPanel(new BorderLayout(5,0));
		JLabel nameLable = new JLabel("密码:");
		//TODO 3-1.c
		passwordField = new JPasswordField();
		//使密码输入为*
		passwordField.enableInputMethods(false);
		BottomPwdpanel.add(BorderLayout.WEST, nameLable);
		BottomPwdpanel.add(BorderLayout.CENTER, passwordField);
		return BottomPwdpanel;
	}

	// TODO 1-1.1.1.2
	private JPanel creatBottomPanel() {
		JPanel BottomPanel = new JPanel(new BorderLayout());
		BottomPanel.add(BorderLayout.EAST, creatLoadAndCanelPanel());
		return BottomPanel;
	}

	// TODO 1-1.1.1.2-1
	private JPanel creatLoadAndCanelPanel() {
		JPanel LoadAndCanelPanel = new JPanel(new FlowLayout());
		JButton login=new JButton("登录");
		//TODO 3-1.c
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				context.login(LoginFrame.this);
			}
		});
		JButton cancel=new JButton("取消");
		//TODO 5-2
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				context.exit(LoginFrame.this);
			}
		});
		LoadAndCanelPanel.add(login);
		LoadAndCanelPanel.add(cancel);
		return LoadAndCanelPanel;
	}
	
	//TODO 3-1.c
	public int getId(){
		String id=nameField.getText();
		return Integer.parseInt(id);
	}
	public String getPwd(){
		char[] Pwd=passwordField.getPassword();
		return new String(Pwd);
	}

}
