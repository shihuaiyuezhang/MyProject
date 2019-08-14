package com.wisev.util;


import java.util.*;

public class Test{





	public static void main(String[] args)   {
        String[] strings = new String[]{"a","a","a","a","b","b","c","d"};
        HashMap<String,Integer> hashMap = new HashMap<>();
        List<String> list = Arrays.asList(strings);
        for(String str : list) {
            hashMap.put(str,hashMap.getOrDefault(str,0) + 1);
        }
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparing(hashMap::get));
        for(String key : hashMap.keySet()) {
            queue.offer(key);
            if(queue.size() > 2) {
                queue.poll();
            }
        }
        List<String> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            result.add(queue.poll());
        }
        System.out.println(result);


	}







}