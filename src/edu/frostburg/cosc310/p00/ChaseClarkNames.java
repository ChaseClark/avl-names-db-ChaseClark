package edu.frostburg.cosc310.p00;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ChaseClarkNames implements COSC310_P00{

    private CCNamesDB db;
    private boolean isRunning = true;
    private int count = 0;
    private final String path = "src/edu/frostburg/cosc310/p00/names.txt";

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
        return 0;
    }

    @Override
    public void who() {

    }

    @Override
    public void help() {

    }

    @Override
    public void exit() {
        isRunning = false;
    }
}
