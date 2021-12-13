package com.withos.pandorasBox;

import com.withos.pandorasBox.entities.Simulation;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        int width = 500, height = 500;

        System.out.println("1 - Begin new Simulation");
        System.out.println("2 - Load from file");
        Scanner s = new Scanner(System.in);

        String op = s.nextLine();

        switch (op) {
            case "1":
                System.out.println("1 - Starting population without immunity");
                System.out.println("2 - Starting population with immunity");
                String op2 = s.nextLine();
                switch (op2) {
                    case "1":
                        new Simulation(width, height, false).startSimulation();
                    case "2":
                        new Simulation(width, height, true).startSimulation();
                    default: System.out.println("Option doesn't exist");
                }

            case "2" : {
                File root = new File("./states/");
                String[] contents = root.list();
                System.out.println("Files to load");
                if(contents == null) {
                    System.out.println("No files to load");
                    break;
                }
                Arrays.stream(contents).forEach(elem -> {
                    Pattern pattern = Pattern.compile("state");
                    Matcher matcher = pattern.matcher(elem);
                    boolean matchFound = matcher.find();
                    if (matchFound) {
                        System.out.println(elem);
                    }
                });
                System.out.println("Input the file name");
                String fileName = s.nextLine();
                Simulation simulation = new Simulation(width, height, fileName);
                simulation.startSimulation();

            }
            default: System.out.println("Option doesn't exist");
        }
    }
}
