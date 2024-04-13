
/**
 * Blueprint for the Patient object. 
 * Extends the User class. 
 * @author Amy Dalziel 
 */
public class Patient extends User {
    
    // Attributes 
    private Boolean isDoctor; 
    private int doctorId; 


    // Constructor Method - uses values from the User Class 
    public Patient(int id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password); 
        isDoctor = false; 
    }


    // Getters and Setters 
    public int getDoctorId() 
    {
        return this.doctorId; 
    }

    public void setDoctorId(int d)
    {
        this.doctorId = d; 
    }

    // toString Method 
    @Override
   /**
     * Method - toString
     * @return User ID # : FirstName XX LastName XX, Email: XX@XX.XX, Doctor: XX 
     */
    public String toString()
    {
        return (super.toString() + ", Doctor: " + this.getDoctorId()); 
    }

    
}
