import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDao {
   
    public boolean createUser(User u) {

        boolean bool = false;

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
        boolean is_doctor = false;

        // Prepare the SQL query
        String query = " SELECT id, first_name, last_name, email, is_doctor\n" + //
                        "\tFROM public.users\n" + //
                        "\tWHERE id= ?; ";


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
            }

    } catch(SQLException e)
        {
            e.printStackTrace();
        }


        return new User(user_id, firstName, lastName, email, password);

    }



    public User getUserByEmail(String e) { 

        int user_id = 0;
        String firstName = null;
        String lastName = null;
        String email = null;
        String password = null;
        boolean is_doctor = false;

        // Prepare the SQL query
        String query = " SELECT id, first_name, last_name, email, is_doctor\n" + //
                        "\tFROM public.users\n" + //
                        "\tWHERE email= ?; ";


        // Database logic to get data by email Using Prepared Statement

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
            }

    } catch(SQLException err)
        {
            err.printStackTrace();
        }


        return new User(user_id, firstName, lastName, email, password);

    }


    // public boolean updateUser(User user) {
    //     // Prepare the SQL query
    //     // Database logic to get update user Using Prepared Statement
    // }


    public boolean deleteUser(int id) { 

        boolean bool = false; 

        // Prepare the SQL query

        String query = " DELETE FROM public.users\n" + //
                        "\tWHERE id=" + id +"; ";

        
        // Database logic to delete user

        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);

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


