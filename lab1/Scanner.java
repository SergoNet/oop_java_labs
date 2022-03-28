package com.company.lab1;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Scanner  {

    public static HashMap<String,Integer> count(String finIn)  {
        String REGEX  = "[А-Яа-яЁёA-Za-z_0-9]+";
        File fin = new File(finIn);
        Pattern pattern = Pattern.compile(REGEX);
        HashMap<String,Integer> wordDataMap = new HashMap<>();
        String string;
        Matcher matcher;

        try (BufferedReader reader = new BufferedReader(new FileReader(fin))) {
            int amountOfWords = 0;
            while ((string = reader.readLine()) != null) {
                matcher = pattern.matcher(string);
                while (matcher.find()) {
                    amountOfWords++;
                    String word = string.substring(matcher.start(), matcher.end());
                    if (wordDataMap.containsKey(word)) {
                        wordDataMap.put(word,wordDataMap.get(word) + 1);
                    } else {
                        wordDataMap.put(word, 1);
                    }
                }
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return wordDataMap;
    }

}
