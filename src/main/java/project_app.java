//package com.example;
public class project_app {
    public static void main(String[] args)
    {
        System.out.print("Enter Firstname: ");
        //reading a string from the user
        String firstname = System.console().readLine();
        //String firstname = "Khai";
        System.out.print("Enter Lastname: ");
        //reading a string from the user
        String lastname = System.console().readLine();
        //String lastname = "Hoang";
        String strhello = "Welcome and Hello " +firstname + " " + lastname;
        strhello = strhello + " to DEVOPS team.";
        System.out.println(strhello);
    }

    public String getStatus() {
        return "OK";
    }
}
