package org.marquee.exam.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.marquee.elts.entity.ExamInfo;
import org.marquee.elts.entity.QuestionInfo;
import org.marquee.exam.service.ClientContext;


public class ExamFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	
	private ClientContext context;
	private JLabel infoLabel;
	private JTextArea questionTextArea;
	private JLabel numLabel;
	private Option[] options=new Option[4];
	
	private JButton pre;
	private JButton next;
	private JButton sent;
	private JLabel time;
	
	public ExamFrame(ClientContext context){
		this();
		this.context=context;
	}

	// TODO 1-3.1
	public ExamFrame() {
		initialize();
	}

	// TODO 1-3.2
	private void initialize() {
		setTitle("在线考试系统");
//		setPreferredSize(new Dimension(600,500));
		setSize(500,400);
		setContentPane(creatExamFrame());
		//TODO 8.6
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				context.sent(ExamFrame.this);
			}
		});
	}

	// TODO 1-3.2.1
	private JPanel creatExamFrame() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(0, 0, 15, 0));
		ImageIcon titleIcon = new ImageIcon(
				ExamFrame.class.getResource("titlebar.png"));
		panel.add(BorderLayout.NORTH, new JLabel(titleIcon, JLabel.CENTER));
		panel.add(BorderLayout.CENTER, creatTextAreaAndLabelPanel());
		panel.add(BorderLayout.SOUTH, creatinfoPanel());
		return panel;
	}

	// TODO 1-3.2.1.1
	private JPanel creatTextAreaAndLabelPanel() {
		JPanel TextAreaAndLabelPanel = new JPanel(new BorderLayout());
		TextAreaAndLabelPanel.setBorder(new EmptyBorder(0, 10, 0,10));
		infoLabel=new JLabel("姓名：刁志伟"
				+ "	编号：1001	考试时间：4分钟	科目：挖掘机	    题目数量：20", JLabel.CENTER);
		TextAreaAndLabelPanel.add(BorderLayout.NORTH,infoLabel );
		TextAreaAndLabelPanel.add(BorderLayout.CENTER, creatTextAreaPanel());
		return TextAreaAndLabelPanel;
	}

	// TODO 1-3.2.1.1-1
	private JScrollPane creatTextAreaPanel() {
		JScrollPane slider=new JScrollPane();
		slider.setBorder(new TitledBorder("题目"));
		slider.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTextArea textarea = new JTextArea();
		textarea.setText("问题:\nA.\nb.\nb.");
		textarea.setLineWrap(true);
		questionTextArea=textarea;
		slider.getViewport().add(textarea);

		return slider;
	}

	// TODO 1-3.2.1.2
	private JPanel creatinfoPanel() {
		JPanel infopanel = new JPanel(new BorderLayout());
		infopanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		infopanel.add(BorderLayout.NORTH, creatOptionPanel());
		infopanel.add(BorderLayout.CENTER, creatbottomPanel());
		return infopanel;
	}

	// TODO 1-3.2.1.2-1
	private JPanel creatOptionPanel() {
		JPanel OptionPanel=new JPanel();
		//TODO 8.1
		Option a=new Option(0,"A");
		Option b=new Option(1,"B");
		Option c=new Option(2,"C");
		Option d=new Option(3,"D");
		
		options[0]=a;
		options[1]=b;
		options[2]=c;
		options[3]=d;
		
		OptionPanel.add(a);
		OptionPanel.add(b);
		OptionPanel.add(c);
		OptionPanel.add(d);
		
		return OptionPanel;
	}

	// TODO 1-3.2.1.2-2
	private JPanel creatbottomPanel() {
		JPanel bottompanel=new JPanel(new BorderLayout());
		//TODO 8.1
		numLabel=new JLabel("题目：20题的第一题");
		time=new JLabel("剩余时间：20秒");
		bottompanel.add(BorderLayout.WEST,numLabel);
		bottompanel.add(BorderLayout.CENTER,creatchoicButtonPanel());
		bottompanel.add(BorderLayout.EAST,time);
		return bottompanel;
	}
	// TODO 1-3.2.1.2-2-1
	private JPanel creatchoicButtonPanel() {
		JPanel choicButtonPanel=new JPanel(new FlowLayout());
		pre=new JButton("<前一个");
		//TODO 8.3
		pre.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				context.pre(ExamFrame.this);
			}
		});
		
		next=new JButton("后一个>");
		//TODO 8.2
		next.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				context.next(ExamFrame.this);
			}
		});
		sent=new JButton("交卷");
		//TODO 8.4
		sent.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				context.sent(ExamFrame.this);
			}
		});
		choicButtonPanel.add(pre);
		choicButtonPanel.add(next);
		choicButtonPanel.add(sent);
		return choicButtonPanel;
	}

	// TODO 1-3.3
	public void showGUI() {
		setVisible(true);
	}
	//TODO 8.1
	public void updateView() {
		ExamInfo examInfo=context.getExamInfo();
		QuestionInfo questionInfo=context.getQuestionInfo();
		infoLabel.setText(examInfo.toString());
		questionTextArea.setText(questionInfo.getQuestion().toString());
		updateQuestionNumber(examInfo,questionInfo);
		updateOptions(questionInfo);
		//TODO 8.5
		updateButton();
	}
	
	//TODO 8.5
	private void updateButton() {
		int idx=context.getQuestionInfo().getQuestionIndex();
		int max=context.getExamInfo().getQuestionCount();
		next.setEnabled(idx<(max-1));
		pre.setEnabled(idx>0);
	}

	private void updateOptions(QuestionInfo questionInfo) {
		List<Integer> userAnswers=questionInfo.getUserAnswers();
		for (Option o : options) {
//			o.setSelected(false);
//			if (userAnswers.contains(o.val)) {
//				o.setSelected(true);
//			}
			o.setSelected(userAnswers.contains(o.val));
		}
	}

	private void updateQuestionNumber(ExamInfo examInfo,
			QuestionInfo questionInfo) {
		String str="题号："+(questionInfo.getQuestionIndex()+1)+"/"+examInfo.getQuestionCount();
		numLabel.setText(str);
	}
	class Option extends JCheckBox{
		int val;
		public Option(int val,String txt) {
			super(txt);
			this.val=val;
		}
		
	}
	//TODO 8.2
	public List<Integer> getUserAnswers() {
		List<Integer> list=new ArrayList<Integer>();
		for (Option o : options) {
			if (o.isSelected()) {
				list.add(o.val);
			}
		}
		return list;
	}
	//TODO 9.2.1.1
	public void updateTime(String time, Color color) {
		this.time.setForeground(color);
		this.time.setText("剩余时间："+time);
	}
	
	
}
