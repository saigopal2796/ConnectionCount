package com.example.demo.beans;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.*;

public class Prac1 {
	
	public static void main(String[] args) {
	
	String str1 = "Father needs a cup of coffee rather than a cup";
	
    String[] strlist = str1.split(" ");
    
    List<String> list = Arrays.asList(strlist);
    
    Map<String, Long> map = list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    
    System.out.println(map);
    
    List<String> intlist = list.stream().filter(x->x.length()>4).map(x->x).collect(Collectors.toList());
    
    System.out.println(intlist);
    
     int y= 28;
	 List<Integer> numlist = new ArrayList<>();
	 for(int i=1;i<=28;i++)
	 {
		 numlist.add(i);
	 }
	 Predicate<Integer> pr = num -> (y%num)==0;
	 List<Integer> ntlist = numlist.stream().filter(pr).map(x->x).collect(Collectors.toList());
	 System.out.println(ntlist);
     List<String> str =
	          Arrays.asList("Jon", "Ajeet", "Steve",
	             "Ajeet", "Jon", "Ajeet");
	 List<Integer> list1 =Arrays.asList(13,12,3,6,3);
	}

}
