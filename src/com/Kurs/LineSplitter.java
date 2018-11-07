package com.Kurs;
import java.util.*;
import java.util.stream.Collectors;

public class LineSplitter {
    private Scanner scanner  =new Scanner(System.in);

    public String getInput(){
        System.out.println("Wpisz formułę do obliczenia");
        String expression = scanner.nextLine();
        return expression;
    }

    public Deque<String> split(String input){
        String[] expArray  = input.split("\\p{Punct}");
        Deque<String> expStack = new ArrayDeque<>(Arrays.asList(expArray));
        String[] operArray = input.split("\\d+");
        List<String > operList = Arrays.asList(operArray);
        List<String > splitOperList = new ArrayList<>();
        operList.stream()
                .map(s -> s.split(""))
                .forEach(strings -> splitOperList.addAll(Arrays.asList(strings)));
        operList = splitOperList.stream()
                .filter(s -> !s.equals(""))
                .collect(Collectors.toList());
        Deque<String> operDequeue = new ArrayDeque<>(operList);
        Deque<String > sortedOperList  =new ArrayDeque<>();
        int size = expStack.size();
        for (int i = 0; i <size ; i++) {
            if (expStack.peek().equals("")){
                expStack.poll();
                sortedOperList.add(operDequeue.poll());
            }else {
                sortedOperList.add(expStack.poll());
                if (operDequeue.size()>0){
                    sortedOperList.add(operDequeue.poll());
                }
            }
            if(expStack.size()==0&&operDequeue.size()>0){
                sortedOperList.add(operDequeue.poll());
            }
        }
        return sortedOperList;
    }
}
