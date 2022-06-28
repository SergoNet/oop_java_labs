package com.company.lab1;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Validity.readWordsFrom(args);

        String finIn = args[Constants.READ_FILE];
        HashMap<String,Integer> wordData = Scanner.count(finIn);

        CreateList ml = new CreateList();
        List<Map.Entry<String,Integer>> list = ml.creList(wordData);

        String finOut = args[Constants.FILE_TO_WRITE];
        Printer.print(finOut,list);
        System.exit(0);
    }

}
