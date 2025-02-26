package edu.frostburg.cosc310.p00;


/**
 * A class to represent a record in a database.
 */
public class CCRecord implements Record,Comparable<CCRecord> {
    private int id;
    private String firstName;
    private String lastName;
    private int age;

    public CCRecord(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getAge() {
        return String.valueOf(age);
    }

    // order by last name and then first name
    @Override
    public int compareTo(CCRecord o) {
        // this should work
        return (this.lastName + this.firstName).compareToIgnoreCase(o.lastName+o.firstName);
    }

    // override toString so we can print the record directly
    @Override
    public String toString() {
        return "id:"+id+" firstName:"+firstName+" lastName:"+lastName+" age:"+age;
    }
}
