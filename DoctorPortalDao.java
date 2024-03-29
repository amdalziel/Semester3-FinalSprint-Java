

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class DoctorPortalDao {

    private UserDao userDao;
    private HealthDataDao healthDataDao;

   // Complete all these methods and add more as needed


    public DoctorPortalDao() {
        userDao = new UserDao();
        healthDataDao = new HealthDataDao();
    }


    public boolean createDoctor(Doctor d) {

        boolean bool = false;
        
        // insert user into database 
        String hashedPassword = BCrypt.hashpw(d.getPassword(), BCrypt.gensalt());

        // Prepare the SQL query
        String query = " INSERT INTO public.users(\n" + //
                        "\tid, first_name, last_name, email, password, is_doctor, specialization, med_lic_num)\n" + //
                        "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        // Database logic to insert data using PREPARED Statement
        try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,d.getId());
            statement.setString(2,d.getFirstName());
            statement.setString(3,d.getLastName());
            statement.setString(4,d.getEmail());
            statement.setString(5, hashedPassword);
            statement.setBoolean(6,d.getIsDoctor());
            statement.setString(7, d.getSpecialization());
            statement.setString(8,d.getMedicalLicenseNumber());

            int updatedRows = statement.executeUpdate();
            if (updatedRows != 0) {
                
                String doctorPatientQuery = "INSERT INTO public.doctor_patient(doctor_id, patient_id) VALUES (?, ?)";
            PreparedStatement doctorPatientStatement = con.prepareStatement(doctorPatientQuery);
            doctorPatientStatement.setInt(1, d.getId());
            doctorPatientStatement.setInt(2, 1); 
            doctorPatientStatement.executeUpdate(); 
            
            bool = true;

        }
    } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return bool; 

    }

    public Doctor getDoctorById(int dId) {

        int user_id = 0;
        String firstName = null;
        String lastName = null;
        String email = null;
        String password = null;
        boolean is_doctor = false;
        String specialization = null;
        String medicalLicenseNumber = null; 

        // Prepare the SQL query
        String query = " SELECT id, first_name, last_name, email, is_doctor, specialization, med_lic_num\n" + //
                        "\tFROM public.users\n" + //
                        "\tWHERE id =" + dId + "; ";


        // Database logic to get data by ID Using Prepared Statement

        try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                user_id = rs.getInt("id");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                email = rs.getString("email");
                is_doctor = rs.getBoolean("is_doctor");
                specialization = rs.getString("specialization");
                medicalLicenseNumber = rs.getString("med_lic_num");
            }

    } catch(SQLException e)
        {
            e.printStackTrace();
        }


        return new Doctor(user_id, firstName, lastName, email, password, is_doctor, specialization, medicalLicenseNumber);
    }

    // public List<User> getPatientsByDoctorId(int doctorId) {
    //     // Implement this method
    // }

    // public List<HealthData> getHealthDataByPatientId(int patientId) {
    //     // Implement this method
    // }

    // Add more methods for other doctor-specific tasks
 
}

