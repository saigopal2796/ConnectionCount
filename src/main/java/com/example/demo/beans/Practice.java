package com.example.demo.beans;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.*;

public class Practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	 String str1 = "Father needs a cup of coffee rather than a cup";
	 String[] list1 = str1.split(" ");
	 List<String> arraylist = Arrays.asList(list1);
	 Map<String, Long> mapfinallist = arraylist.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	 arraylist.stream().map(x->x).forEach(x->System.out.println(x));
	 System.out.println(mapfinallist);
	 
	 int y= 28;
	 List<Integer> numlist = new ArrayList<>();
	 for(int i=1;i<=28;i++)
	 {
		 numlist.add(i);
	 }
	 Predicate<Integer> pi = num -> y%num ==0 ;
	 List<Integer> fnumlist = numlist.stream().map(x->x).filter(pi).collect(Collectors.toList());
	 System.out.println(fnumlist);
	 
	 List<String> names = new ArrayList();
		names.add("Ajeet");
		names.add("Negan");
		names.add("Aditya");
		names.add("Steve");
		
		
		//using foreach loop
		names.forEach((n)->System.out.println(n));
		
		
		//by count
		long count = names.stream().filter(p->p.length()<6).count();
		System.out.println(count);
		List<String> str =
		          Arrays.asList("Jon", "Ajeet", "Steve",
		             "Ajeet", "Jon", "Ajeet");
		List<Integer> list =Arrays.asList(13,12,3,6,3);
		Optional<Integer> maxnum = list.stream().max(Comparator.naturalOrder());
		System.out.println("max " +maxnum.get());
		Optional<Integer> minnum = list.stream().min(Comparator.naturalOrder());
		System.out.println("minnum " +minnum.get());
		
		
		//find sum
		int sum = list.stream().filter(i -> i > 10).mapToInt(i -> i).sum();
		System.out.println("sum " +sum);
		long c = str.stream().filter(i -> i.equalsIgnoreCase("Ajeet")).count();
		System.out.println("number count " +c);
		
		
		//convert list to array
		 Integer[] arr = list.toArray(new Integer[0]);
		 for (Integer x : arr) {
	            System.out.print(x + " ");
		 }
		  List<Integer> sort = list.stream().sorted().collect(Collectors.toList());
		 
		  
		  //find duplicates
		  Set<Integer> items = list.stream().filter(i -> Collections.frequency(list, i) > 1).collect(Collectors.toSet());
		  System.out.println("dupli " +items);
		  System.out.println("sorting " +sort);
		 
		  
		  //remove duplicates
		 List<Integer> distinct = list.stream().distinct().collect(Collectors.toList());
		 System.out.println("distinct " +distinct);
		
		 
		 //seperate even or odd numbers
		List<Integer> nums = Arrays.asList(1,2,3,4,5);
		Map<Boolean, List<Integer>> resp = nums.stream().collect(Collectors.partitioningBy(n->n%2==0));
		System.out.println("even nd odd numbers " +resp);
		System.out.println(resp.get(true));
		
		
		//group wise count
		Map<String, Long> map = str.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(map);
		

	}

}
