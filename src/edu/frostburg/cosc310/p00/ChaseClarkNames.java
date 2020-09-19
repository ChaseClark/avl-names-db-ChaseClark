package edu.frostburg.cosc310.p00;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class ChaseClarkNames implements COSC310_P00{

    private CCNamesDB db;
    private boolean isRunning = true;
    private int count = 0;
    private final String path = "./names.txt";

    // questions to ask
    // count using traversal or global var
    // empty find method from interface?
    // how to check if avl tree is working properly?


    /**
     * Sets the database up and then runs the main input loop.
     */
    @Override
    public void go() {
        System.out.println(getMyName() + "'s db starting...");
        // init db
        db = new CCNamesDB();
        insertRecordsFromFile(path);
        System.out.println("Please enter a command or type '?' for help");
        Scanner in = new Scanner(System.in);
        // main input loop using a switch case for the custom commands
        while(isRunning)
        {
            String input = in.nextLine();
            // convert input into array with 1 or more elements
            String[] args = input.trim().toLowerCase().split(" ");
            // switch over the first argument
            switch (args[0]) {
                case "":
                    break;
                case "find":
                    // check for 2 or 3 args to determine which find to use
                    if(args.length == 2) // find last name
                    {
                        CCRecord result = (CCRecord)find(args[1]);
                        if(result != null)
                        {
                            System.out.println(result);
                        } else {
                            System.out.println("Record not found");
                        }
                    }
                    else if(args.length == 3) // find full name
                    {
                        CCRecord result = (CCRecord)find(args[1],args[2]);
                        if(result != null)
                        {
                            System.out.println(result);
                        } else {
                            System.out.println("Record not found");
                        }
                    }
                    else // wrong amount of args
                        System.out.println("invalid arguments, type ? for help");
                    break;
                case "add":
                    // ensure 5 args add + 4 values
                    if(args.length == 5)
                    {
                        int newId,newAge;
                        String newFirst = args[2];
                        String newLast = args[3];
                        // handle cases of bad inputs for id and age
                        try{
                            newId = Integer.parseInt(args[1]);
                            newAge = Integer.parseInt(args[4]);
                        } catch (NumberFormatException e) { // if either newId or newAge are not ints then make them -1
                            newId = -1;
                            newAge = -1;
                        }
                        if (add(newId,newFirst,newLast,newAge)) {
                            System.out.println("Record successfully added.");
                        }
                        else
                            System.out.println("Error adding record, please check the values.");
                    } else
                        System.out.println("Add needs to have 4 parameters");
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
                    System.out.println("Command not recognized, type ? for help");
                    break;
            }


        }
        // no longer running
        System.exit(0);
    }

    /**
     * parses text file and adds records to db.
     * @param path of text file
     */
    private void insertRecordsFromFile(String path) {
        try {
            List<String> allLines = Files.readAllLines(Path.of(path));
            for (String line: allLines) {
                if (!line.startsWith("#"))
                {
                    String[] lineArr = line.split(" ");
                    // we only want to parse lines with exactly 4 values
                    if (lineArr.length == 4)
                        // add id, first name, last name, age
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
     * Returns my name
     **/
    @Override
    public String getMyName() {
        return "Chase Clark";
    }

    @Override
    public void find() {

    }

    /**
     * Queries the database for a record with a matching last name.
     * @param lname last name
     * @return the first matching record found, or null
     */
    @Override
    public Record find(String lname) {
        return  db.containsLastName(lname);
    }

    /**
     * Queries the database for a record with a matching first and last name.
     * @param fname first name
     * @param lname last name
     * @return matching record found, or null
     */
    @Override
    public Record find(String fname, String lname) {
       return db.containsFullName(fname,lname);
    }

    // adds record to the database
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

    // prints my name to console
    @Override
    public void who() {
        System.out.println(getMyName());
    }

    // prints a list of commands
    @Override
    public void help() {
        System.out.println("-----------------------------------");
        System.out.println("Available commands");
        System.out.println();

        System.out.println("find [name] - Searches the tree for the first person with that last name and prints out " +
                "their record if present.");
        System.out.println();

        System.out.println("find [fname] [lname] - Searches the tree for the person specified and prints their record" +
                " if present.");
        System.out.println();

        System.out.println("add [id] [fname] [lname] [age] - Inserts a new record with this info or generate an error " +
                "message if invalid.");
        System.out.println();

        System.out.println("count - Print the number of names in the database.");
        System.out.println();

        System.out.println("who? = Print the creator's name.");
        System.out.println();

        System.out.println("? - Print this list of commands.");
        System.out.println();

        System.out.println("exit - Quit the program.");
        System.out.println("-----------------------------------");
    }

    // ends the program by killing the input loop
    @Override
    public void exit() {
        System.out.println("Shutting down...goodbye");
        isRunning = false;
    }
}
