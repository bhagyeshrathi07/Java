package com.practice.java.popularQuestionsNotOnLeetcode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DeleteRepeatedElementsInList {

    public static List<Integer> removeRepeatedElements(List<Integer> list){
        HashSet<Integer> elements = new HashSet<>();
        List<Integer> uniqueList = new ArrayList<>();
        for(int num : list) {
            if(!elements.contains(num)) {
                uniqueList.add(num);
                elements.add(num);
            }
        }
        return uniqueList;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(4);
        list.add(4);
        list.add(5);
        list.add(2);
        list.add(3);
        List<Integer> uniqueList = removeRepeatedElements(list);
        for(int i : uniqueList) {
            System.out.println(i);
        }
    }
}



