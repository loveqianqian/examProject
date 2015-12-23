package org.marquee.exam.test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHarnessV5 {
	public static void main(String[] args) {
		Scanner myscanner=new Scanner(System.in);
		while (true) {
			System.out.printf("%nEnter your Regex:");
			Pattern pattern=Pattern.compile(myscanner.nextLine());
			System.out.printf("Enter your input string to search:");
			Matcher matcher=pattern.matcher(myscanner.nextLine());
			boolean Found=false;
			while (matcher.find()) {
				System.out.printf("Found \"%s\"starting index %d ending index %d.%n",
						matcher.group(),matcher.start(),matcher.end());
				Found=true;
				if (!Found) {
					System.out.printf("No match Found!!%n");
				}
			}
		}
		
	}
}
