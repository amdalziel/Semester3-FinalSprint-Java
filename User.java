// WORKING

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    // Attributes 
    protected int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    // Constructor Method 
    public User() {
        this.id = (Integer) null; 
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.password = null;
    }

    // Constructor Method (takes in values from the user)
    public User(int id, String firstName, String lastName, String email, String password) {

        this.id = id; 
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters 
    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String fn) {
        this.firstName = fn;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String ln) {
        this.lastName = ln;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String e) {
        this.email = e;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String p) {
        this.password = p;
    }

    // toString Method 
    public String toString()
    {
        return ("User " + this.id + ":" + this.firstName + " " + this.lastName + ", Email: " + this.email); 
    }

   
}
