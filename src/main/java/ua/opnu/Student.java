package ua.opnu;

import java.util.Arrays;

public class Student {
    private String firstName;
    private String lastName;
    private String group;
    private int[] marks;

    public Student(String firstName, String lastName, String group, int[] marks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.marks = marks;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getGroup() {
        return group;
    }

    public int[] getMarks() {
        return marks;
    }

    public String getInfo() {
        return firstName + " " + lastName + ", " + group + ": " + Arrays.toString(getMarks());
    }

}
