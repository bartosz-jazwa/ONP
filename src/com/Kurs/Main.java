package com.Kurs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Scanner scanner  =new Scanner(System.in);
        String expression = scanner.nextLine();


        String[] expArray  = expression.split("\\p{Punct}");
        List<String > expList = Arrays.asList(expArray);
        Deque<String> expStack = new ArrayDeque<>();
        expList.forEach(expStack::add);
        String[] operArray = expression.split("\\d+");
        List<String > operList = Arrays.asList(operArray);
        List<String > splitOperList = new ArrayList<>();
        operList.stream()
                .map(s -> s.split(""))
                .forEach(strings -> splitOperList.addAll(Arrays.asList(strings)));
        operList = splitOperList.stream()
                .filter(s -> !s.equals(""))
                .collect(Collectors.toList());
        Deque<String> operDequeue = new ArrayDeque<>();
        operList.forEach(operDequeue::add);

        //operDequeue.poll();
        Deque<String > sortedOperList  =new ArrayDeque<>();
        int size = expStack.size();
        for (int i = 0; i <size ; i++) {
            if (expStack.peek().equals("")){
                expStack.poll();
                sortedOperList.add(operDequeue.poll());
            }else {
                sortedOperList.add(expStack.poll());
                sortedOperList.add(operDequeue.poll());
            }
            if(expStack.size()==0&&operDequeue.size()>0){
                sortedOperList.add(operDequeue.poll());
            }
        }
        for (String s: sortedOperList) {
            System.out.println(s);
        }


    }
}
