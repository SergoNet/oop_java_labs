package com.company.lab1;

import java.util.*;


public class CreateList {
    public void creList(List list) {
        list.sort(new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String,Integer> first, Map.Entry<String,Integer> second) {
                int tmp = first.getValue() - second.getValue();
                return Integer.compare(0, tmp);
            }
        });
    }
}
