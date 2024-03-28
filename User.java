// WORKING

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isDoctor;

    public User() {
        this.id = -1; 
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.password = null;
        this.isDoctor = false;
    }

    public User(int id, String firstName, String lastName, String email, String password, boolean isDoctor) {
        this.id = id; 
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isDoctor = isDoctor;
    }

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

    public boolean getIsDoctor() {
        return this.isDoctor;
    }

    public void setIsDoctor(boolean d) {
        isDoctor = d;
    }

    public String toString()
    {
        return ("User " + this.id + ":" + this.firstName + " " + this.lastName + ", Email: " + this.email); 
    }

   
}
