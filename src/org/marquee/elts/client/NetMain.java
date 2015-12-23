package org.marquee.elts.client;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.marquee.elts.entity.EntityContext;
import org.marquee.exam.gui.ExamFrame;
import org.marquee.exam.gui.LoginFrame;
import org.marquee.exam.gui.MenuFrame;
import org.marquee.exam.gui.WelcomeWindow;
import org.marquee.exam.service.ClientContext;
import org.marquee.exam.service.ExamServiceImpl;
import org.marquee.util.Config;

public class NetMain {
	public static void main(String[] args) {
		try {
		Config config = new Config("client.properties");
		EntityContext entity=new EntityContext(config);
		//ExamServiceImpl service=new ExamServiceImpl(entity,config);
		ExamServiceAgentImpl service=new ExamServiceAgentImpl(config);
		
		ClientContext context=new ClientContext(service);
		LoginFrame loginFrame=new LoginFrame(context);
		MenuFrame menuFrame=new MenuFrame(context);
		ExamFrame examframe=new ExamFrame(context);
		
		context.setExamFrame(examframe);
		context.setLoginFrame(loginFrame);
		context.setMenuFrame(menuFrame);
		
		//TODO 9
		WelcomeWindow weclomeWindows=new WelcomeWindow();
		context.setWelcomeWindow(weclomeWindows);
		
		context.showDemoView();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
