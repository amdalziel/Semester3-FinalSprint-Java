public class Patient extends User {
    Boolean isDoctor; 

    private int doctorId; 

    public Patient(int id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password); 
        isDoctor = false; 
    }


    public boolean getIsDoctor() {
        return this.isDoctor;
    }

    public void setIsDoctor(boolean d) {
        isDoctor = d;
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
