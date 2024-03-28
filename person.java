// REFERENCE ONLY - DELETE LATER

public class person {

    private String firstName;
    private String lastName; 

    public person()
    {
        this.firstName = null;
        this.lastName = null; 
    }

    public person(String f, String l)
    {
        this.firstName = f;
        this.lastName = l; 
    }


    public String getFirstName()
    {
        return this.firstName; 
    }

    public String getLastName()
    {
        return this.lastName; 
    }

    public void setFirstName(String f)
    {
        this.firstName = f; 
    }

    public void setLastName(String l)
    {
        this.lastName = l; 
    }

    public String toString()
    {
        return (this.firstName + this.lastName);
    }


    
}
