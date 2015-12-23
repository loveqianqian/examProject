package org.marquee.exam.service;

import org.marquee.elts.entity.EntityContext;
import org.marquee.exam.server.ExamServer;
import org.marquee.exam.test.entitytest;
import org.marquee.util.Config;

public class Main {
	public static void main(String[] args) throws Exception{
		Config config=new Config("client.properties");
		EntityContext context=new EntityContext(config);
		ExamServer server=new ExamServer(context, config);
		server.listen();
	}
}
