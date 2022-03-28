package com.company.lab1;

import java.io.*;
import java.util.*;

public class Printer {
    public static void print(String finOut, List<Map.Entry<String,Integer>> list) {
        File out = new File(finOut);
        try (FileWriter writer = new FileWriter(out, false)){
            for (Map.Entry<String,Integer> pair: list) {
                writer.write(pair.getKey() + " , " + pair.getValue() + " , " + (float)pair.getValue() / list.size() + "\n");
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}


