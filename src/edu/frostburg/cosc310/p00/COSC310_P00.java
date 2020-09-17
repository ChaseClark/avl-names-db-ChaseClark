/*
 * Interface for P00.  Create your own concrete class that implements this
    interface.  Please do not modify the interface.
 */
package edu.frostburg.cosc310.p00;

/**
 * Public interface for AVL Project.
 * @author Steve Kennedy
 */
public interface COSC310_P00 {    
    
    /** Runs project.  You will create an instance of your project in main
     *      and then have it invoke this method to begin running.
     */
    public void go();
    
    /** Returns your name **/
    public String getMyName();
    
    /** The following methods should be implemented for this assignment.  */
    public void find();
    
    /* The following methods are commands that the user can run */
    public Record find(String name);
    public Record find(String fname, String lname);
    public boolean add(int id, String fname, String lname, int age);
    public int count();
    public void who();
    public void help();
    public void exit();
    
}
