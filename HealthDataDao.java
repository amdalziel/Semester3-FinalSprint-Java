// WORKING 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HealthDataDao {


     public boolean createHealthData(HealthData h) { 

      Boolean bool = false; 

         // Prepare the SQL query
         String query = "INSERT INTO public.health_data(\n" + //
                      "\tid, user_id, weight, height, steps, heart_rate, date)\n" + //
                      "\tVALUES (?, ?, ?, ?, ?, ?, ?);";
 
         // Database logic to insert data using PREPARED Statement
         try{
             Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query);
             statement.setInt(1,h.getHealthDataId());
             statement.setInt(2,h.getUserId());
             statement.setDouble(3,h.getWeight());
             statement.setDouble(4,h.getHeight());
             statement.setInt(5,h.getSteps());
             statement.setInt(6,h.getHeartRate());
             statement.setDate(7, java.sql.Date.valueOf(h.getDate()));
 
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




      public HealthData getHealthDataById(int healthId) { 

        int id = healthId;
        int user_id = 0;
        double weight = 0;
        double height = 0;
        int steps = 0;
        int heart_rate = 0;
        LocalDate date = null; 

        // Prepare the SQL query
        String query = "SELECT user_id, weight, height, steps, heart_rate, date\n" + //
                        "\tFROM public.health_data\n" + //
                        "\tWHERE id = ?;";


        // Database logic to get data by ID Using Prepared Statement

        try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                user_id = rs.getInt("user_id");
                weight = rs.getDouble("weight");
                height = rs.getDouble("height");
                steps = rs.getInt("steps");
                heart_rate = rs.getInt("heart_rate");
                date = rs.getDate("date").toLocalDate();
            }

    } catch(SQLException e)
        {
            System.out.println("Error: "); 
            e.printStackTrace();
        }

        return new HealthData(id, user_id, weight, height, steps, heart_rate, date); 
        
    }




    public List<HealthData> getHealthDataByUserId(int userId) { 

        ArrayList<HealthData> healthDataList = new ArrayList<>(); 

        int healthDataId = 0; 
        ArrayList<Integer> healthDataIds = new ArrayList<>(); 

        // Prepare the SQL query 

        String query = "SELECT id\n" + // 
       "\tFROM public.health_data\n" + //
        "\tWHERE user_id = ?; "; 


         // Database logic to get data by ID Using Prepared Statement
         try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) 
            {
                healthDataId = rs.getInt("id");
                healthDataIds.add(healthDataId); 
            }

    } catch(SQLException e)
        {
            System.out.println("Error: "); 
            e.printStackTrace();
        }

        for(int i = 0; i < healthDataIds.size(); i++) 
        {
            int currId = healthDataIds.get(i); 
            HealthData h = this.getHealthDataById(currId); 
            healthDataList.add(h);
        }

        return healthDataList; 

    
}



   public <T> boolean updateHealthData(String selection, T value, HealthData healthD) { 

    boolean bool = false; 
    int id = healthD.getHealthDataId(); 

    switch (selection) {
        case "weight":

        double weight = (double) value; 
        String queryWeight = "UPDATE public.health_data\n" + //
                        "\tSET weight=?\n" + //
                        "\tWHERE id = ?;"; 

    try {
        Connection con = DatabaseConnection.getCon();
        PreparedStatement statement = con.prepareStatement(queryWeight);

        statement.setDouble(1, weight);
        statement.setInt(2, id);
    

        int updatedRows = statement.executeUpdate();
        if (updatedRows != 0) {
            bool = true;
        }
        break; 
    } catch (SQLException e) {
        e.printStackTrace();
    } 
     
        case "height":

        double height = (double) value; 
        String queryHeight = "UPDATE public.health_data\n" + //
                         "\tSET height=?\n" + //
                        "\tWHERE id = ?;"; 

        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(queryHeight);

            statement.setDouble(1, height);
            statement.setInt(2, id);
        

            int updatedRows = statement.executeUpdate();
            if (updatedRows != 0) {
                bool = true;
            }
            break; 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        case "steps":

        int steps = (int) value; 
        String querySteps ="UPDATE public.health_data\n" + //
                        "\tSET steps= ?\n" + //
                        "\tWHERE id= ?;"; ; 

        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(querySteps);

            statement.setInt(1, steps);
            statement.setInt(2, id);
        

            int updatedRows = statement.executeUpdate();
            if (updatedRows != 0) {
                bool = true;
            }
            break; 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        case "heart_rate":

        int heart_rate = (int) value; 
        String queryHeartRate = "UPDATE public.health_data\n" + //
                            "\tSET heart_rate= ?\n" + //
                            "\tWHERE id= ?;"; 

        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(queryHeartRate);

            statement.setInt(1, heart_rate);
            statement.setInt(2, id);
        
            int updatedRows = statement.executeUpdate();
            if (updatedRows != 0) {
                bool = true;
            }
            break; 
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        case "date":

        LocalDate date = (LocalDate) value; 
        String queryDate = "UPDATE public.health_data\n" + //
                            "\tSET date= ?\n" + //
                            "\tWHERE id= ?;"; 

        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(queryDate);

            statement.setDate(1, java.sql.Date.valueOf(date));
            statement.setInt(2, id);
        
            int updatedRows = statement.executeUpdate();
            if (updatedRows != 0) {
                bool = true;
            }
            break; 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    
        default:

        System.out.println("Error: please enter one of the following values: weight, height, steps, heart_rate or date."); 
            break;
    }

    if(bool) {
        System.out.println("Update for Health Data " + healthD.getHealthDataId() + " complete.");
    } 


    return bool; 


   }



   public boolean deleteHealthData(int id) { 

    boolean bool = false; 

    // Prepare the SQL query

    String query = " DELETE FROM public.health_data\n" + //
                    "\tWHERE id=?; ";

    // Database logic 

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

        System.out.println("Success - Health Data " + id + " deleted from the database."); 
        return bool; 

   }




}
