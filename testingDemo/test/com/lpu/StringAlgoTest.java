package com.lpu;

import static org.junit.jupiter.api.Assertions.*;

import java.util.InputMismatchException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringAlgoTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("runs before all test cases : set up db connection, n/w connection, websocket");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("runs after all test cases : CLOSE db connection, n/w connection, websocket");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Provide same environment before all test cases, insert dummy data, set up arrays");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Provide same environment before all test cases, removes  dummy data, removes arrays");
	}

	@Test
	void testShift2Chars() {
		StringAlgo obj = new StringAlgo();
		String acutal = obj.shift2Chars("hello");
		String expected = "llohe";
		assertEquals(expected,acutal);
		System.out.println("hello passed");
	}
	@Test
	void testShift2CharsBoundary() {
		StringAlgo obj = new StringAlgo();
		String acutal = obj.shift2Chars("ab");
		String expected = "ab";
		assertEquals(expected,acutal);
		System.out.println("ab passed");
	}
	@Test
	void testShift2CharsException() {
		StringAlgo obj = new StringAlgo();
		assertThrows(InputMismatchException.class,() -> {
			String acutal = obj.shift2Chars("a"); });
		System.out.println("a passed");
	}

}
