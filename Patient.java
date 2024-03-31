public class Patient extends User {
    private int doctorId; 

    public Patient(int id, String firstName, String lastName, String email, String password, boolean isDoctor) {
        super(id, firstName, lastName, email, password, isDoctor); 
    }

    public int getDoctorId() 
    {
        return this.doctorId; 
    }

    public void setDoctorId(int d)
    {
        this.doctorId = d; 
    }

    public String toString()
    {
        return (super.toString() + ", Doctor: " + this.getDoctorId()); 
    }



    
}
