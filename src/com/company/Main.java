package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        String data = "";
        char previousDir = 0;
        int horizontal = 0;
        int orizontal = 0;
        int mark = 0;
        int total = 1;
        String directionMarker= "";

        try {
            File myObj = new File("day1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                  data = myReader.nextLine();
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String[] parts = data.split(", ");
        for (String str : parts) {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(str);
            while (m.find()) {
                if (mark == 1) {
                    if((str.charAt(0) == 'R' && directionMarker.equals("East"))||
                            (str.charAt(0) == 'L' && directionMarker.equals("West"))){
                        horizontal -=Integer.parseInt(m.group());
                        directionMarker = "South";
                    }
                    else if((str.charAt(0) == 'R' && directionMarker.equals("West")) ||
                            (str.charAt(0) == 'L' && directionMarker.equals("East"))){
                        horizontal +=Integer.parseInt(m.group());
                        directionMarker = "North";
                    }
                    mark = 2;
                }
                 else if(mark == 2){
                    if((str.charAt(0) == 'R' && directionMarker.equals("South"))||
                            (str.charAt(0) == 'L' && directionMarker.equals("North"))){
                        orizontal -= Integer.parseInt(m.group());
                        directionMarker = "West";
                    }
                    else if((str.charAt(0) == 'R' && directionMarker.equals("North")) ||
                            (str.charAt(0) == 'L' && directionMarker.equals("South"))){
                        orizontal += Integer.parseInt(m.group());
                        directionMarker = "East";
                    }
                    mark = 1;
                }
                if(mark == 0){
                    if(str.charAt(0) == 'R'){
                        directionMarker="East";
                        orizontal += Integer.parseInt(m.group());
                    }
                    else if(str.charAt(0) == 'L'){
                        directionMarker="West";
                        orizontal -= Integer.parseInt(m.group());
                    }
                    mark = 1;
                }
            }
        }
        System.out.println(orizontal);
        System.out.println(horizontal);
    }
}
