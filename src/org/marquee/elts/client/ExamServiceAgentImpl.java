package org.marquee.elts.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.swing.RepaintManager;

import org.marquee.elts.entity.ExamInfo;
import org.marquee.elts.entity.QuestionInfo;
import org.marquee.exam.server.Request;
import org.marquee.exam.server.Response;
import org.marquee.exam.service.ExamNotFinishException;
import org.marquee.exam.service.ExamOverException;
import org.marquee.exam.service.ExamService;
import org.marquee.exam.service.NameorPasswordException;
import org.marquee.exam.service.User;
import org.marquee.exam.service.consts;
import org.marquee.util.Config;

//TODO 10.2
public class ExamServiceAgentImpl implements ExamService{

	private String sessionID;
	private Config config;
	
	public ExamServiceAgentImpl(Config config){
		this.config=config;
	}
	
	public User login(int id, String Pwd) throws NameorPasswordException {
		Response res=sendRequest("login",new Class[]{int.class,String.class},
				new Object[]{id,Pwd});
		if(res.isSuccess())
			return (User) res.getValue();
		if(res.getState()==consts.Err_Name_Or_Password){
			throw new NameorPasswordException(res.getMessage());
		}
		throw new RuntimeException(res.getMessage());
	}


	public ExamInfo start() throws ExamOverException {
		Response res=sendRequest("start",new Class[]{},new Object[]{});
		if(res.isSuccess())
			return (ExamInfo) res.getValue();
		if(res.getState()==consts.Err_Exam_Over){
			throw new ExamOverException(res.getMessage());
		}
		throw new RuntimeException(res.getMessage());
	}

	public QuestionInfo getQuestion(int index) throws ExamOverException {
		Response res=sendRequest("getQuestion",new Class[]{int.class},new Object[]{index});
		if(res.isSuccess())
			return (QuestionInfo) res.getValue();
		if(res.getState()==consts.Err_Exam_Over){
			throw new ExamOverException(res.getMessage());
		}
		throw new RuntimeException(res.getMessage());
	}

	public void sendUserAnswers(int questionIndex, List<Integer> answers) {
		Response res=sendRequest("sendUserAnswers",new Class[]{int.class,List.class},new Object[]{questionIndex,answers});
		if(res.isSuccess())
			return;
		throw new RuntimeException(res.getMessage());
	}

	public int commit() {
		Response res=sendRequest("commit",new Class[]{},new Object[]{});
		if(res.isSuccess())
			return (Integer) res.getValue();
		throw new RuntimeException(res.getMessage());
	}

	public int result() throws ExamNotFinishException {
		Response res=sendRequest("result",new Class[]{},new Object[]{});
		if(res.isSuccess())
			return (Integer) res.getValue();
		if(res.getState()==consts.Err_Exam_Not_Finish){
			throw new ExamNotFinishException(res.getMessage());
		}
		throw new RuntimeException(res.getMessage());
	}
	/**
	 * 向指定的服务器发送请求，得到返回值
	 * @param string
	 * @param classes
	 * @param objects
	 * @return
	 */
	
	//TODO 10.2.1
	private Response sendRequest(String method, Class[] argTypes, Object[] args) {
		String host=config.getString("ServerIP");
		int port=config.getInt("ServerPort");
		try {
			Socket socket=new Socket(host, port);
			ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
			//创建服务器请求
			Request request=new Request(sessionID,method,argTypes,args);
			//发送请求到服务器
			out.writeObject(request);
			out.flush();
			//接受服务器的反馈
			Response r=(Response)in.readObject();
			System.out.println("response:"+r);
			//将服务器返回的sessionID 保存起来
			sessionID=r.getSessionID();
			socket.close();//通信关闭
			
			return r;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			throw new RuntimeException("网络连接故障",e);//异常的在抛出
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("网络连接故障",e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("网络故障",e);
		}
	}
//	public static void main(String[] args) throws Exception {
//		Config config=new Config("client.properties");
//		ExamServiceAgentImpl server=new ExamServiceAgentImpl(config);
//		User u=server.login(1001, "dzw123");
//		System.out.println(u);
//	}

}
