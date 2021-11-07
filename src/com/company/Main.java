package com.company;

import org.apache.poi.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

//Using https://howtodoinjava.com/java/library/readingwriting-excel-files-in-java-poi-tutorial/

    public static void main(String[] args) {
        Reader reader = new Reader();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Where is your file located?");

        //"/EAC_HACKATON_2021/textures.xlsx"
        String input = scanner.next();

        reader.run(input);

        double[] doubles = reader.getDoubles();
        String[] strings = reader.getStrings();

        int[] ints = new int[doubles.length];
        for (int i = 0; i < ints.length; i++){
            int value = (int)doubles[i];
            ints[i] = value;
        }

        String userPath = Paths.get(System.getProperty("user.home")).toString();
        String[] userPathSplit = userPath.split("\\\\");


        for (int i = 0; i < strings.length; i++){
            String[] filePathSplit = strings[i].split("\\\\");

            //https://stackoverflow.com/questions/5283444/convert-array-of-strings-into-a-string-in-java/5283753

            if (filePathSplit[1].equals("Users")){
                filePathSplit[2] = userPathSplit[2];

                String joinedString = StringUtils.join(new Object[]{filePathSplit[0], filePathSplit[1], filePathSplit[2],
                        filePathSplit[3], filePathSplit[4], filePathSplit[5]}, "\\");

               strings[i] = joinedString;

            }

        }

        Writer writer = new Writer(ints, strings);

        writer.run();

    }
}
