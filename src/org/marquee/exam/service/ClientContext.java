package org.marquee.exam.service;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.marquee.elts.entity.ExamInfo;
import org.marquee.elts.entity.QuestionInfo;
import org.marquee.exam.gui.ExamFrame;
import org.marquee.exam.gui.LoginFrame;
import org.marquee.exam.gui.MenuFrame;
import org.marquee.exam.gui.WelcomeWindow;

/**
 * GUI的核心控制器上下文
 * 
 * @author Administrator
 * 
 */
// TODO 3-1
public class ClientContext implements Serializable {

	/**
	 * 
	 */

	// TODO 3-1.a
	private static final long serialVersionUID = 10000000005L;

	private ExamService service;
	private LoginFrame loginFrame;
	private ExamFrame examFrame;
	private MenuFrame menuFrame;
	private User loginUser;
	private WelcomeWindow welcomewindow;
	//the timer user for count time 
	private Timer timer=new Timer();

	// TODO 8.1
	private ExamInfo examInfo;
	private QuestionInfo questionInfo;

	public ClientContext(ExamService service) {
		this.service = service;
	}

	// TODO 3-1.b
	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	public void setMenuFrame(MenuFrame menuFrame) {
		this.menuFrame = menuFrame;
	}

	public void setExamFrame(ExamFrame examFrame) {
		this.examFrame = examFrame;
	}

	// TODO 9
	public void setWelcomeWindow(WelcomeWindow weclomeWindow) {
		this.welcomewindow = weclomeWindow;
	}

	// TODO 3-1.c
	public void login(JFrame source) {
		try {
			int id = loginFrame.getId();
			String Pwd = loginFrame.getPwd();
			loginUser = service.login(id, Pwd);
			source.setVisible(false);
			menuFrame.updateShowView();// 更新界面
			center(menuFrame);
			menuFrame.showGUI();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(source, e.getMessage());
		}
	}

	public User getLoginUser() {
		return loginUser;
	}

	public void showDemoView() {
		center(loginFrame);
		loginFrame.showGUI();
		showWelcome();
	}

	// TODO 9
	public void showWelcome() {
		center(welcomewindow);
		welcomewindow.setVisible(true);
		new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				welcomewindow.setVisible(false);
			};
		}.start();
	}

	// TODO 5-2
	public void exit(JFrame source) {
		// YES_NO_OPTION表示只有确定，没有取消
		int val = JOptionPane.showConfirmDialog(source, "您确定离开", "离开系统",
				JOptionPane.YES_NO_OPTION);
		if (val == JOptionPane.YES_OPTION) {
			source.setVisible(false);
			System.exit(0);
		}
	}

	// TODO 8.1
	public ExamInfo getExamInfo() {
		return examInfo;
	}

	public QuestionInfo getQuestionInfo() {
		return questionInfo;
	}

	// TODO 8.1
	public void start(JFrame source) {
		try {
			examInfo = service.start();
			questionInfo = service.getQuestion(0);
			examFrame.updateView();
			source.setVisible(false);
			center(examFrame);
			examFrame.setVisible(true);
			
			//TODO 9.2 
			startTimer();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(source, e.getMessage());
		}

	}

	//TODO 9.2.1
	private void startTimer() {
		int timeLimit=examInfo.getTimeLimit();
		long start=System.currentTimeMillis();
		final long end=start+(timeLimit*60*1000);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				long now=System.currentTimeMillis();
				long limit=end-now;
				//显示时间
				showTime(limit);
				if (limit<=0) {
					//超时交卷
					timeout();
				}
			}
		},0,1000);
	}

	//TODO 9.2.1
	protected void timeout() {
		JOptionPane.showMessageDialog(examFrame, "考试结束！");
		gameOver(examFrame);
	}
	//TODO 9.2.1.1
	private void gameOver(JFrame source) {
		try {
			int idx = questionInfo.getQuestionIndex();
			List<Integer> answers = examFrame.getUserAnswers();
			service.sendUserAnswers(idx, answers);
			int score = service.commit();
			JOptionPane.showMessageDialog(source, "你的分数是：" + score);
			examFrame.setVisible(false);
			menuFrame.setVisible(true);
			timer.cancel();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(source, e.getMessage());
		}
	}

	//TODO 9.2.1
	protected void showTime(long limit) {
		int h=(int) (limit/1000/3600);
		int m=(int) (limit/1000/60%60);
		int s=(int) (limit/1000%60);
		String time =h+":"+m+":"+s;
		Color color=Color.blue;
		if (limit<2000*60) {
			color=color.red;
		}
		examFrame.updateTime(time,color);
	}

	// TODO 8.2
	public void next(JFrame source) {
		try {
			int idx = questionInfo.getQuestionIndex();
			if (idx + 1 == examInfo.getQuestionCount()) {
				return;
			}
			List<Integer> answers = examFrame.getUserAnswers();
			// questionInfo.setUserAnswers(answers);
			service.sendUserAnswers(idx, answers);
			questionInfo = service.getQuestion(idx + 1);
			examFrame.updateView();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(source, e.getMessage());
		}
	}

	// TODO 8.3
	public void pre(JFrame source) {
		try {
			int idx = questionInfo.getQuestionIndex();
			if (idx == 0) {
				return;
			}
			List<Integer> answers = examFrame.getUserAnswers();
			// questionInfo.setUserAnswers(answers);
			service.sendUserAnswers(idx, answers);
			questionInfo = service.getQuestion(idx - 1);
			examFrame.updateView();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(source, e.getMessage());
		}
	}

	// TODO 8.4
	public void sent(JFrame source) {
		int val = JOptionPane.showConfirmDialog(source, "你确定离开吗？");
		if (val != JOptionPane.YES_OPTION) {
			return;
		}
		gameOver(source);
	}
	//TODO 9.1
	/**
	 * center
	 * @param win
	 */
	//container window父类
	public void center(Container win){
		//toolkit 系统工具集
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Dimension screen=toolkit.getScreenSize();
		int x=(screen.width-win.getWidth())/2;
		int y=(screen.height-win.getHeight())/2;
		win.setLocation(x, y);
		
	}

	//TODO 10.2
	public void grade(JFrame source) {
		try{
		int score=service.result();
		JOptionPane.showMessageDialog(source, "您的分数："+score);
		}catch(ExamNotFinishException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(source, e.getMessage());
		}
	}

}
