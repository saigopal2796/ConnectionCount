package com.example.demo;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter number of inputs");
		int num = in.nextInt();
		ArrayList<String> arr = new ArrayList<String>();
		for(int i=0; i<num; i++)
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter input String");
			String name = sc.nextLine();
			arr.add(name);
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(String a: arr)
		{
			if(a.matches("[0-9]+"))
			{
				result.add(0);
				
			}else {
				
				result.add(a.length());
			}
		}
		
		System.out.println("The output is:" + result);
		

	}

}
