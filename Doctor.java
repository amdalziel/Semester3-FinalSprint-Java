
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

public class Doctor extends User{

    // Attributes 
    Boolean isDoctor; 
    private String medicalLicenseNumber;
    private String specialization;
    private ArrayList<Patient> patientList; 

    // Constructor Method - uses values from the User Class 
    public Doctor(int id, String firstName, String lastName, String email, String password, String spec, String medLN) {
        
        super(id, firstName, lastName, email, password);
        this.specialization = spec;
        this.medicalLicenseNumber = medLN;
        this.isDoctor = true; 
    }

    // Getters and Setters 
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

    public void addPatient(Patient p)
    {
        patientList.add(p); 
    }

    // toString Method 
    @Override
    public String toString()
    {
        return ("Doctor " + this.getId() + ":" + this.getFirstName() + " " + this.getLastName() + ", Email: " + this.getEmail() + ", Specialization: " + this.specialization + ", Medical License Number: " + this.medicalLicenseNumber); 
    }


}

