// REFERENCE ONLY - DELETE LATER 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo {
    DatabaseConnection dbConnection = new DatabaseConnection();
    public static void main(String[] args) {
        String fname;
        String lname;
        String email; 
        String password; 
        // Boolean is_doctor; 
        int option = 0;
        Scanner in = new Scanner(System.in);
        Scanner boolIn = new Scanner(System.in);

        while(option !=-1)
        {
            System.out.println("Enter First Name");
            fname = in.nextLine();
            System.out.println("Enter Last Name");
            lname = in.nextLine();
            System.out.println("Enter email");
            email = in.nextLine();
            System.out.println("Enter password");
            password = in.nextLine();
            System.out.println("Are you a doctor (yes or no)");
            Scanner sc = new Scanner(System.in);  
            boolean is_doctor = sc.nextBoolean(); 
            
            int id = 0; 

            User u = new User(id, fname, lname, email, password, is_doctor);
            InsertRecord(u);
            System.out.println("Enter -1 to exit else press any key to continue: ");
            option = in.nextInt();
            in.nextLine();
        }

        ReadDB();
      
    }

    public static void ReadDB()
    {
        ArrayList<User> UserList = new ArrayList<>();
        String query = "SELECT * FROM public.\"users\";";
        try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setIsDoctor(rs.getBoolean("is_doctor"));
                UserList.add(u);
            }
        }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

            for(int i = 0;i<UserList.size();i++)
            {
                System.out.println(UserList.get(i).toString());
            }

    }
    public static void InsertRecord(User u)
    {
        String query = " INSERT INTO public.users(\n" + //
                        "\tid, first_name, last_name, email, password, is_doctor)\n" + //
                        "\tVALUES (?, ?, ?, ?, ?, ?);";

        try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,u.getId());
            statement.setString(2,u.getFirstName());
            statement.setString(3,u.getLastName());
            statement.setString(4,u.getEmail());
            statement.setString(5,u.getPassword());
            statement.setBoolean(6,u.getIsDoctor());
            int updateRow = statement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

    }
    
}

