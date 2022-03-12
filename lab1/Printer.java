package com.company.lab1;

import java.io.*;
import java.util.*;

public class Printer {
    public static void print(File out, List<Map.Entry<String,Integer>> list,int amountOfWords) {
        try (FileWriter writer = new FileWriter(out, false)){
            for (Map.Entry<String,Integer> pair: list) {
                writer.write(pair.getKey() + " , " + pair.getValue() + " , " + (float)pair.getValue() / amountOfWords + "\n");
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}


