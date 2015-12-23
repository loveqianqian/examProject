package org.marquee.exam.test;

import org.marquee.util.Config;

public class noPointException {
	private Config config;
	private static int tl;
	private static String t;
	
	public noPointException(Config config){
		this.config=config;
		tl=config.getInt("TimeLimit");
		t=config.getString("PaperTitle");
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(tl+t);
		
	}
}
