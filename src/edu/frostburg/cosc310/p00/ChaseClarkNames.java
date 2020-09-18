package edu.frostburg.cosc310.p00;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ChaseClarkNames implements COSC310_P00{

    private CCNamesDB db;
    private boolean isRunning = true;
    private int count = 0;
    private final String path = "src/edu/frostburg/cosc310/p00/names.txt";

    // questions to ask
    // count using traversal or global var
    // empty find method from interface?
    // how to check if avl tree is working properly?


    /**
     * Runs project.  You will create an instance of your project in main
     * and then have it invoke this method to begin running.
     */
    @Override
    public void go() {
        System.out.println(getMyName() + "'s db starting...");
        // init db
        db = new CCNamesDB();
//        add(2,"Chase","Clark",27);
//        add(0,"Chase","Clark",28);
//        add(1,"Aiden","Clark",50);
//        db.printTree();
        insertRecordsFromFile(path);
        System.out.println("Please enter a command or type '?' for help");
        Scanner in = new Scanner(System.in);
        // main input loop using a switch case for the custom commands
        while(isRunning)
        {
            String input = in.nextLine();
            // convert input into array with 1 or more elements
            String[] args = input.trim().split(" ");
            // switch over the first argument
            switch (args[0]) {
                case "":
                    break;
                case "find":
                    // check for 1 or 2 args
                    break;
                case "add":
                    // ensure 4 args
                    break;
                case "count":
                    System.out.println(count());
                    break;
                case "who?":
                    System.out.println(getMyName());
                    break;
                case "?":
                    help();
                    break;
                case "exit":
                    System.out.println("CCNamesDB is shutting down...goodbye");
                    in.close();
                    exit();
                    break;
                case "print": // this method is just added for debugging
                    db.printTree();
                    break;
                default:
                    System.out.printf("Command not recognized, type ? for help");
                    break;
            }


        }
        // no longer running
        System.exit(0);
    }

    private void insertRecordsFromFile(String path) {
        try {
            List<String> allLines = Files.readAllLines(Path.of(path));
            for (String line: allLines) {
                if (!line.startsWith("#"))
                {
                    String[] lineArr = line.split(" ");
                    // we only want to parse lines with exactly 4 values
                    if (lineArr.length == 4)
                        add(Integer.parseInt(lineArr[0]),lineArr[1],lineArr[2],Integer.parseInt(lineArr[3]));
                }
            }
            System.out.println(count+" records inserted into CCNamesDB");
        } catch (IOException e) {
            System.out.println("error finding file at: "+ path);
            e.printStackTrace();
        }
    }

    /**
     * Returns your name
     **/
    @Override
    public String getMyName() {
        return "Chase Clark";
    }

    /**
     * The following methods should be implemented for this assignment.
     */
    @Override
    public void find() {

    }

    @Override
    public Record find(String name) {
        return null;
    }

    @Override
    public Record find(String fname, String lname) {
        return null;
    }

    @Override
    public boolean add(int id, String fname, String lname, int age) {
        // check for bad values
        if(id < 0 || fname == null || lname == null || age < 0)
            return false;
        var newRecord = new CCRecord(id,fname,lname,age);
        db.insert(newRecord);
        count++;
        return true;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public void who() {
        System.out.println(getMyName());
    }

    @Override
    public void help() {
        System.out.println("-----------------------------------");
        System.out.println("Available commands");
        System.out.println("find [name] - Searches the tree for the first person with that last name and prints out " +
                "their record if present.");
        System.out.println("find [fname][lname] - Searches the tree for the person specified and prints their record" +
                " if present.");
        System.out.println("add [id][fname][lname][age] - Inserts a new record with this info or generate an error " +
                "message if invalid.");
        System.out.println("count - Print the number of names in the database.");
        System.out.println("who? = Print the creator's name.");
        System.out.println("? - Print this list of commands.");
        System.out.println("exit - Quit the program.");
        System.out.println("-----------------------------------");
    }

    @Override
    public void exit() {
        System.out.println("Shutting down...goodbye");
        isRunning = false;
    }
}
