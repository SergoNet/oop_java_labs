package com.company.lab1;

import java.io.File;

public class Validity {
    public static boolean readWordsFrom(String[] args) {
        File fin = new File(args[Constants.READ_FILE]);
        File out = new File(args[Constants.FILE_TO_WRITE]);
        if(args.length != Constants.NUMBER_OF_INPUT_PARAMETERS) {
            System.out.println("wrong number of arguments\n\n\n");
            return false;
        }
        if(!fin.exists()) {
            System.out.println("input file was not opened");
            System.exit(Constants.ERROR);
        }
        if(!out.exists()) {
            System.out.println("output file was not opened");
            System.exit(Constants.ERROR);
        }
        return true;
    }
}
