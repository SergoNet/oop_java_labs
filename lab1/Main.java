package com.company.lab1;


import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        if(!Scanner.readWordsFrom(args)) {
            System.exit(-1);
        }
        File fin = new File("src"+ File.separator + args[Constants.READ_FILE]);
        Scanner.count(fin);
        Map<String,Integer> wordData = Scanner.getWordDataMap();

        int amountOfWords = Scanner.getAmountOfWords();
        List<Map.Entry<String,Integer>> list = new ArrayList<>(wordData.entrySet());
        CreateList ml = new CreateList();
        ml.creList(list);

        File out = new File(args[Constants.FILE_TO_WRITE]);
        Printer.print(out,list,amountOfWords);
        System.exit(0);
    }

}
