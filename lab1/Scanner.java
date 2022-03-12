package com.company.lab1;


import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Scanner  {
    private static int amountOfWords;
    private static final Map<String,Integer> wordDataMap = new HashMap<>();

    public static boolean readWordsFrom(String[] args) {
        if(args.length != Constants.NUMBER_OF_INPUT_PARAMETERS) {
            System.out.println("wrong number of arguments\n\n\n");
            return false;
        }
        return true;
    }

    public static void count(File fin)  {
        String REGEX  = "[А-Яа-яЁёA-Za-z_0-9]+";
        Pattern pattern = Pattern.compile(REGEX);
        String string;
        Matcher matcher;

        try (BufferedReader reader = new BufferedReader(new FileReader(fin))) {
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
    }
    public static Map<String, Integer> getWordDataMap() {
        return wordDataMap;
    }
    public static int getAmountOfWords() {
        return amountOfWords;
    }

}
