package org.marquee.exam.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.marquee.util.Config;

public class forTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
//		List<Integer> i = new ArrayList<Integer>();
//		String[] data = { "1", "2", "3" };
//		for (String s : data) {
////			i.add(new Integer(s));
//			i.add(Integer.parseInt(s));
//
//		}
//		System.out.println(i);
		Config config=new Config("client.properties");
		String str=config.getString("QuestionFile");
		System.out.println(str);
		BufferedReader in=new BufferedReader(new FileReader(str));
		String s;
		while ((s=in.readLine())!=null) {
			System.out.println(s);
		}
		
		
	}
}
