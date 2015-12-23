package org.marquee.exam.test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

import org.marquee.exam.server.Request;
import org.marquee.exam.server.Response;

public class ServerDemo {
	public static void main(String[] args) throws Exception{
		ServerSocket ss=new ServerSocket(9091);
		Socket s=ss.accept();
		ObjectInputStream in=new ObjectInputStream(s.getInputStream());
		ObjectOutputStream out=new ObjectOutputStream(s.getOutputStream());
		
		Request r=(Request)in.readObject();//��ȡrequest����
		System.out.println("Request:"+r);
		UUID uuid=UUID.randomUUID();
		//����ҵ�񷽷�
		Response res=new Response(uuid.toString(),"HI");
		
		res.setSessionID(uuid.toString());
		out.writeObject(res);
		out.flush();
		s.close();
		
	}
}
