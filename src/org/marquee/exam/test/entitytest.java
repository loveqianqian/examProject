package org.marquee.exam.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class entitytest {
	public static void main(String[] args) throws IOException {
		BufferedReader buf=new BufferedReader(new FileReader("users"));
		String str;
		String[] txt = null;
		while ((str=buf.readLine())!=null) {
			System.out.println(str);
			System.out.println(str.split(":")[0]);
		}
		
		
	}
}
