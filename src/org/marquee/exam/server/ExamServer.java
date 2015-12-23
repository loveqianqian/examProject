package org.marquee.exam.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.naming.Context;

import org.marquee.elts.client.ExamServiceAgentImpl;
import org.marquee.elts.entity.EntityContext;
import org.marquee.exam.service.BaseException;
import org.marquee.exam.service.ExamService;
import org.marquee.exam.service.ExamServiceImpl;
import org.marquee.util.Config;

//TODO 10.3
public class ExamServer {
	private Config config;
	private EntityContext context;
	//ȫ����ҵ��ʵ����ÿ���ͻ�sessionID��Ӧ��һ����
	private Map<String, ExamService> serviceMap=new HashMap<String, ExamService>();
	
	public ExamServer(Config config) {
		this.config = config;
	}
	public ExamServer(EntityContext context,Config config) {
		this.config = config;
		this.context=context;
	}
	
	/**
	 * �����������
	 */
	public void listen() throws Exception{
		int port=config.getInt("ServerPort");
		ServerSocket ss=new ServerSocket(port);	
		while (true) {
			System.out.println("��½�ͻ�������..");
			Socket s=ss.accept();
			new Service(s).start();
		}
	}
	class Service extends Thread{
		Socket socket;
		public Service(Socket s){
			socket=s;
		}
		public void run() {
			try{
				ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
				processRequest(in,out);
				socket.close();
			}catch(IOException e){
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		private void processRequest(ObjectInputStream in, ObjectOutputStream out) 
				throws Exception{
			Request req=(Request) in.readObject();
			String sid=req.getSessionID();
			//��һ�η���
			if (sid==null) {
				sid=UUID.randomUUID().toString();
				ExamService service=new ExamServiceImpl(context, config);
				serviceMap.put(sid, service);
			}
			ExamService service=serviceMap.get(sid);
			Class cls=service.getClass();
			//�ҵ���Ҫ��ִ�е�ҵ�񷽷�
			Method method=cls.getDeclaredMethod(req.getMethod(), req.getArgsTypes());
			//ִ��ҵ�񷽷�
			Response res;
			try{//ҵ��ִ���ڼ䷽��
				Object val=method.invoke(service, req.getArgs());
				res=new Response(sid,val);
			}catch(InvocationTargetException e){
				e.printStackTrace();
				BaseException ex=(BaseException) e.getTargetException();
				res=new Response(sid,ex);
			}catch(Exception e){
				e.printStackTrace();
				res=new Response(sid,e);
			}
			out.writeObject(res);
			out.flush();
		}
	}
}
