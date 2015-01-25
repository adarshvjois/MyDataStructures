package com.adarsh.ds.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.Scanner;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.adarsh.ds.HashTable;
import com.adarsh.util.Queue;

public class HashTableTest {
	public static final String PATH = "/home/adarsh/Desktop/InterviewShit/MyDatastructures/MyDataStructures/Files/";
	private String[] firstNWords;
	private static final int N = 10;
	private HashTable<String, Integer> ht;

	@Before
	public void initialize() {
		Scanner s = null;
		firstNWords = new String[N];
		ht = new HashTable<String, Integer>();
		try {
			s = new Scanner(new FileReader(PATH.concat("dictionary.txt")));
		} catch (FileNotFoundException e) {
			fail("File could not be opened");
			e.printStackTrace();
		}
		if (s != null) {
			System.out.println("File Opened!");
			for (int i = 0; i < N; i++) {
				String word = s.nextLine();
				ht.put(word, i);
				firstNWords[i] = word;
			}
			s.close();
		}
	}

	@Test
	public void testSize() {
		assertEquals("Size test:", N, ht.size());
	}

	@Test
	public void testIsEmpty() {
		assertEquals("isEmpty, negetive Test: ", false, ht.isEmpty());
		HashTable<String, Integer> empty = new HashTable<String, Integer>();
		assertEquals("isEmpty, positive Test:", true, empty.isEmpty());
	}

	@Test
	public void testGet() {
		for (int i = 0; i < N; i++) {
			assertEquals("Value test: ", new Integer(i), ht.get(firstNWords[i]));
		}
	}

	@Test
	public void testContains() {
		for (int i = 0; i < N; i++) {
			assertEquals("Contains, positive test: ", true,
					ht.contains(firstNWords[i]));
		}
		assertEquals("Contains, negative test: ", false,
				ht.contains("sawwhaaa"));

	}

	@Test
	public void testPut() {
		String newEntry = "NEW_ENTRY";
		ht.put(newEntry, N+1);
		assertEquals("Test adding entries: ", true,
				ht.contains(newEntry));
	}

	@Test
	public void testDelete() {
		String toDelete = "TO_DELETE";
		ht.put(toDelete, N+100);
		ht.delete(toDelete);
		assertEquals("Test deletion: ",false,ht.contains(toDelete));
	}

	@Test
	public void testKeys() {
		Queue<String> k = (Queue<String>) ht.keys();
		for(String s: k){
			assertEquals(" Test if keys exist: ",true,ht.contains(s));
		}
	}

}
