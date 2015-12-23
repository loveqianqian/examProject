package org.marquee.exam.test;

import java.util.List;

import org.marquee.elts.entity.ExamInfo;
import org.marquee.elts.entity.QuestionInfo;
import org.marquee.exam.gui.LoginFrame;
import org.marquee.exam.gui.MenuFrame;
import org.marquee.exam.service.ClientContext;
import org.marquee.exam.service.ExamNotFinishException;
import org.marquee.exam.service.ExamOverException;
import org.marquee.exam.service.ExamService;
import org.marquee.exam.service.NameorPasswordException;
import org.marquee.exam.service.User;

/**
 * 整合测试流程
 * 
 * @author Administrator
 * 
 */

public class LoginserversTest {
	public static void main(String[] args) {
		DemoServicer ds=new DemoServicer();
		ClientContext ct=new ClientContext(ds);
		LoginFrame lf=new LoginFrame(ct);
		MenuFrame mf=new MenuFrame(ct);
		
		ct.setLoginFrame(lf);
		ct.setMenuFrame(mf);
		ct.showDemoView();
	}
}

class DemoServicer implements ExamService {

	public User login(int id, String Pwd) throws NameorPasswordException {
		if (id==1001 && Pwd.equals("dzw") ) {
			return new User(1001, "刁志伟", "dzw");
		}
		throw new NameorPasswordException("无此用户");
		
	}

	public ExamInfo start() throws ExamOverException {
		// TODO Auto-generated method stub
		return null;
	}

	public QuestionInfo getQuestion(int index) throws ExamOverException {
		// TODO Auto-generated method stub
		return null;
	}

	public void sendUserAnswers(int questionIndex, List<Integer> answers) {
		// TODO Auto-generated method stub
		
	}

	public int commit() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int result() throws ExamNotFinishException {
		// TODO Auto-generated method stub
		return 0;
	}
}