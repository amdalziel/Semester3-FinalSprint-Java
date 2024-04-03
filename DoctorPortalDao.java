

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class DoctorPortalDao extends UserDao {

    // private UserDao userDao;
    private HealthDataDao healthDataDao;


    public DoctorPortalDao() 
    {
        // userDao = new UserDao();
        healthDataDao = new HealthDataDao();
    }


    public HealthDataDao getHealthDataDao()
    {
        return this.healthDataDao; 
    }


    public void setHealthDataDao(HealthDataDao h) 
    {
        this.healthDataDao = h; 
    }


    @Override
    public <T> boolean createUser(T user) {

        boolean bool = false;

        Doctor d = (Doctor) user; 
        
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
            statement.setBoolean(6,true);
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
        String specialization = null;
        String medicalLicenseNumber = null; 

        // Prepare the SQL query
        String query = " SELECT id, first_name, last_name, email, specialization, med_lic_num\n" + //
                        "\tFROM public.users\n" + //
                        "\tWHERE id = ?; ";


        // Database logic to get data by ID Using Prepared Statement

        try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,dId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                user_id = rs.getInt("id");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                email = rs.getString("email");
                specialization = rs.getString("specialization");
                medicalLicenseNumber = rs.getString("med_lic_num");
            }

    } catch(SQLException e)
        {
            System.out.println("Error: "); 
            e.printStackTrace();
        }

        System.out.println("Success: "); 
        return new Doctor(user_id, firstName, lastName, email, password, specialization, medicalLicenseNumber);
    }


    public List<User> getPatientsByDoctorId(int doctorId) {

        ArrayList<User> patientList = new ArrayList(); 

        int patient_id = 0; 
        ArrayList<Integer> patientIds = new ArrayList<>(); 
        
        // Prepare the SQL query
        String query = "SELECT patient_id\n" + //
        "\tFROM public.doctor_patient\n" + //
        "\tWHERE doctor_id = ?;"; 


         // Database logic to get data by ID Using Prepared Statement
         try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,doctorId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) 
            {
                patient_id = rs.getInt("patient_id");
                patientIds.add(patient_id); 
            }

    } catch(SQLException e)
        {
            System.out.println("Error: "); 
            e.printStackTrace();
        }

        for(int i = 0; i < patientIds.size(); i++) 
        {
            int currId = patientIds.get(i); 
            User p = this.getUserById(currId); 
            patientList.add(p);

        }

        return patientList; 
    }

    // public List<HealthData> getHealthDataByPatientId(int patientId) {
    //     // Implement this method
    // }


    public Boolean addPatientToDoctorList(Doctor d, Patient p)
    {
        Boolean bool = false; 


        // Prepare the SQL query
        String query = " INSERT INTO public.doctor_patient(doctor_id, patient_id) VALUES (?, ?)";

        // Database logic to insert data using PREPARED Statement
        try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(1,d.getId());
            statement.setInt(2,p.getId());
  

            int updatedRows = statement.executeUpdate();
            if (updatedRows != 0) {

            String userQuery = "UPDATE public.users\n" + //
                                "\tSET doctor_id= ?\n" + //
                                "\tWHERE id= ?;";
            PreparedStatement userUpdateStatement = con.prepareStatement(userQuery);
            userUpdateStatement.setInt(1, d.getId());
            userUpdateStatement.setInt(2, p.getId()); 
            userUpdateStatement.executeUpdate(); 

            bool = true;
        }
    } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return bool; 
    }

    public Boolean deletePatientFromDoctorList(Doctor d, Patient p)
    {
        Boolean bool = false; 

          // Prepare the SQL query
        String query = "DELETE FROM public.doctor_patient WHERE doctor_id = ? AND patient_id = ?;";
        
         // Database logic to insert data using PREPARED Statement
         try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(1,d.getId());
            statement.setInt(2,p.getId());
  

            int updatedRows = statement.executeUpdate();
            if (updatedRows != 0) {

            System.out.println("Success - Patient " + p.getId() + "deleted from the system."); 
            bool = true;
        }
    } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return bool; 
    }

 
}

