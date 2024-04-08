import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class PatientPortalDao extends UserDao {

    // Attributes 
    private UserDao userDao;


    // Constructor Method 
    public PatientPortalDao() 
    {
        userDao = new UserDao();
    }

    // Getters and Setters 
    public UserDao getUserDao()
    {
        return this.userDao; 
    }

    public void setUserDao(UserDao u)
        {
            this.userDao = u; 
        }


    @Override
    public <T> boolean createUser(T user) {

        boolean bool = false;

        Patient p = (Patient) user; 
        
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
            statement.setBoolean(6,false);
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




    public Patient getPatientById(int id) { 

        int user_id = 0;
        String firstName = null;
        String lastName = null;
        String email = null;
        String password = null;

        // Prepare the SQL query
        String query = " SELECT id, first_name, last_name, email\n" + //
                        "\tFROM public.users\n" + //
                        "\tWHERE id= ?; ";


        // Database logic to get data by ID Using Prepared Statement

        try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                user_id = rs.getInt("id");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                email = rs.getString("email");
            }

    } catch(SQLException e)
        {
            e.printStackTrace();
        }

      
        return new Patient(id, firstName, lastName, email, password);

    }


    
}
