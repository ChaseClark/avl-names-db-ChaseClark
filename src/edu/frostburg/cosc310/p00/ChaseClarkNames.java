package edu.frostburg.cosc310.p00;

public class ChaseClarkNames implements COSC310_P00{

    private CCNamesDB db;
    private boolean isRunning = true;

    /**
     * Runs project.  You will create an instance of your project in main
     * and then have it invoke this method to begin running.
     */
    @Override
    public void go() {
        System.out.println(getMyName() + "'s db starting...");
        // init db
        db = new CCNamesDB();
        add(2,"Billie","Jean",27);
        add(1,"Dave","Topper",50);
        add(0,"Chase","Clark",28);
        db.printTree();

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
