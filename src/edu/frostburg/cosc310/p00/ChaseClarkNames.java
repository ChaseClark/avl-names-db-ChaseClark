package edu.frostburg.cosc310.p00;

public class ChaseClarkNames implements COSC310_P00{


    /**
     * Runs project.  You will create an instance of your project in main
     * and then have it invoke this method to begin running.
     */
    @Override
    public void go() {
        System.out.println(getMyName() + "'s db starting...");

        // TODO: create an instance of CCNamesDB and insert some test nodes.
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
        return false;
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

    }
}
