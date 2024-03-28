// WORKING

public class Doctor extends User{
    private String medicalLicenseNumber;
    private String specialization;

    public Doctor(int id, String firstName, String lastName, String email, String password, boolean isDoctor, String medLN, String spec) {
        super(id, firstName, lastName, email, password, isDoctor);
        this.medicalLicenseNumber = medLN;
        this.specialization = spec;
    }

    public String getMedicalLicenseNumber()
    {
        return this.medicalLicenseNumber; 
    }

    public void setMedicalLicenseNumber(String medLN)
    {
        this.medicalLicenseNumber = medLN; 
    }

    public String getSpecialization()
    {
        return this.specialization; 
    }

    public void setSpecialization(String s)
    {
        this.specialization = s; 
    }

    public String toString()
    {
        return ("Doctor " + this.getId() + ":" + this.getFirstName() + " " + this.getLastName() + ", Email: " + this.getEmail()); 
    }


}

