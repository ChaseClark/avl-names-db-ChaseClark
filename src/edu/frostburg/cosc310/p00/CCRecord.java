package edu.frostburg.cosc310.p00;

public class CCRecord implements Record,Comparable<CCRecord> {
    private int id;
    private String firstName;
    private String lastName;
    private String age;

    public CCRecord(int id, String firstName, String lastName, String age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(String age) {
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
        return age;
    }

    @Override
    public int compareTo(CCRecord o) {
        // there should never be duplicate id's but I will leave this here just in case.
        if (this.id == o.id)
            return 0;
        // this id > param id
        else if (this.id > o.id)
            return 1;
        // this id < param id
        else
            return -1;
    }

}
