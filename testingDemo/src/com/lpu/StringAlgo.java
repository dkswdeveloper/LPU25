package com.lpu;

import java.util.InputMismatchException;

public class StringAlgo {
	
	public String shift2Chars(String str)
	{
		// write algo to return hello => llohe
		// test for normal programming => ogrammingpr
		// good  => odgo
		// TEst 2 : for boundary values "ab" 
		// Test 3 : Exception conditions InputMismatchException what to do if str.length() < 2
		if(str.length() < 2)
			throw new InputMismatchException("the length of string is < 2");
		String sub = str.substring(0,2);
		String end = str.substring(2);
		return end + sub;
	}
	public static void main(String[] args) {
		StringAlgo obj = new StringAlgo();
		System.out.println(obj.shift2Chars("Hello"));
		System.out.println(obj.shift2Chars("ab"));
		System.out.println(obj.shift2Chars("a"));
	}

}
