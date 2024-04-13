import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.time.LocalDate;

/**
 * Blueprint for a User Decentralized Autonomous Organization object. 
 * This class is extended by the Patient and the Doctor class.
 * @author Amy Dalziel
 * 
 */
public class UserDao {
   
    
    /** 
     * @param user
     * @return boolean
     */
    // Method for creating a user - this is overridden in both the Patient and Doctor DAO classes. 
    public <T> boolean createUser(T user) {

        boolean bool = false;
        User u = (User) user; 

        // insert user into database 
        String hashedPassword = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());

        // Prepare the SQL query
        String query = " INSERT INTO public.users(\n" + //
        "\tid, first_name, last_name, email, password, is_doctor)\n" + //
        "\tVALUES (?, ?, ?, ?, ?, ?);";

        // Database logic to insert data using PREPARED Statement
        try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,u.getId());
            statement.setString(2,u.getFirstName());
            statement.setString(3,u.getLastName());
            statement.setString(4,u.getEmail());
            statement.setString(5, hashedPassword);

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

    public User getUserById(int id) { 

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

      
        return new User(id, firstName, lastName, email, password);

    }



    public User getUserByEmail(String e) { 

        int user_id = 0;
        String firstName = null;
        String lastName = null;
        String email = null;
        String password = null;

        // Prepare the SQL query
        String query = " SELECT id, first_name, last_name, email\n" + //
                        "\tFROM public.users\n" + //
                        "\tWHERE email= ?; ";


        // Database logic to get data by email Using Prepared Statement

        try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, e);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                user_id = rs.getInt("id");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                email = rs.getString("email");
            }

    } catch(SQLException err)
        {
            err.printStackTrace();
        }


        return new User(user_id, firstName, lastName, email, password);

    }

/**
 * Update User Method: 
 * Takes in three parameters: 
 * 1. String selection (user must enter a column name from the user table - otherwise the default will display an error message)
 * 2. <T> value - the new (updated) value given by the user. 
 * Since the variable type (String, int, boolean, etc.) could vary, this has been created as a generic class
 * 3. User user - used to determine which user will be updated. 
 * 
 * @param <T>
 * @param selection
 * @param value
 * @param user
 * @return 
 */
    public <T> boolean updateUser(String selection, T value, User user) {

        Boolean bool = false; 

        switch (selection) {
            case "first_name":

            String fName = (String) value; 
            String queryFName = "UPDATE public.users\n" + //
                                "\tSET first_name= ?\n" + //
                                "\tWHERE id= ?;"; 

        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(queryFName);

            statement.setString(1, fName);
            statement.setInt(2, user.getId());
        

            int updatedRows = statement.executeUpdate();
            if (updatedRows != 0) {
                bool = true;
            }
            break; 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
         
            case "last_name":

            String lName = (String) value; 
            String queryLName = "UPDATE public.users\n" + //
                                "\tSET last_name= ?\n" + //
                                        "\tWHERE id= ?;"; ; 

            try {
                Connection con = DatabaseConnection.getCon();
                PreparedStatement statement = con.prepareStatement(queryLName);

                statement.setString(1, lName);
                statement.setInt(2, user.getId());
            

                int updatedRows = statement.executeUpdate();
                if (updatedRows != 0) {
                    bool = true;
                }
                break; 
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            
            case "email":

            String email = (String) value; 
            String queryEmail ="UPDATE public.users\n" + //
                            "\tSET email= ?\n" + //
                            "\tWHERE id= ?;"; ; 

            try {
                Connection con = DatabaseConnection.getCon();
                PreparedStatement statement = con.prepareStatement(queryEmail);

                statement.setString(1, email);
                statement.setInt(2, user.getId());
            

                int updatedRows = statement.executeUpdate();
                if (updatedRows != 0) {
                    bool = true;
                }
                break; 
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            
            case "password":

            String password = (String) value; 
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            String queryPassword = "UPDATE public.users\n" + //
                                "\tSET password= ?\n" + //
                                "\tWHERE id= ?;"; ; 

            try {
                Connection con = DatabaseConnection.getCon();
                PreparedStatement statement = con.prepareStatement(queryPassword);

                statement.setString(1, hashedPassword);
                statement.setInt(2, user.getId());
            
                int updatedRows = statement.executeUpdate();
                if (updatedRows != 0) {
                    bool = true;
                }
                break; 
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        
            default:

            System.out.println("Error: please enter one of the following values: first_name, last_name, email or password."); 
                break;
        }

        if(bool) {
            System.out.println("Update for User " + user.getId() + " complete.");
        } 

        return bool; 
    }





    public boolean deleteUser(int id) { 

        boolean bool = false; 

        // Prepare the SQL query

        String query = " DELETE FROM public.users\n" + //
                        "\tWHERE id=?; ";

        
        // Database logic to delete user

        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated != 0){
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bool; 
    }



    public boolean verifyPassword (int id, String p)
    {
        boolean bool = false;
        String query = "SELECT password\n" + //
                        "\tFROM public.users\n" + //
                        "\tWHERE id = ?;";

        //Implement logic to retrieve password using the Bcrypt
        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            String hashedPassword = null;
            while (rs.next()) {
                hashedPassword = rs.getString("password");
            }
            if (BCrypt.checkpw(p, hashedPassword)) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

    }


