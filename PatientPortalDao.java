import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class PatientPortalDao {

    private UserDao userDao;


   // Complete all these methods and add more as needed


    public PatientPortalDao() {
        userDao = new UserDao();

    }


    public boolean createPatient(Patient p) {

        boolean bool = false;
        
        // insert user into database 
        String hashedPassword = BCrypt.hashpw(p.getPassword(), BCrypt.gensalt());

        // Prepare the SQL query
        String query = " INSERT INTO public.users(\n" + //
                        "\tid, first_name, last_name, email, password, is_doctor, doctor_id)\n" + //
                        "\tVALUES (?, ?, ?, ?, ?, ?, ?);";

        // Database logic to insert data using PREPARED Statement
        try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,p.getId());
            statement.setString(2,p.getFirstName());
            statement.setString(3,p.getLastName());
            statement.setString(4,p.getEmail());
            statement.setString(5, hashedPassword);
            statement.setBoolean(6,p.getIsDoctor());
            statement.setInt(7, p.getDoctorId()); 
    

            int updatedRows = statement.executeUpdate();
            if (updatedRows != 0) {
     
            bool = true;

        }
    } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return bool; 

    }

    
}
