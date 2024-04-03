public class Patient extends User {
    
    // Attributes 
    private Boolean isDoctor; 
    private int doctorId; 

    // Constructor Method - uses values from the User Class 
    public Patient(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password); 
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
    public String toString()
    {
        return (super.toString() + ", Doctor: " + this.getDoctorId()); 
    }



    
}
