package com.Kurs;

import java.util.Deque;

public class Main {

    public static void main(String[] args) {

        LineSplitter splitter = new LineSplitter();
        Deque<String> formulaQueue = splitter.split(splitter.getInput());
        //System.out.println(formulaQueue.poll());
        CalcONP calulator = new CalcONP();
        System.out.println(calulator.calc(calulator.formToOnp(formulaQueue)));
    }
}
