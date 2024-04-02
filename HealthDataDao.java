
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class HealthDataDao {


     public boolean createHealthData(HealthData h) { 

      Boolean bool = false; 

         // Prepare the SQL query
         String query = "INSERT INTO public.health_data(\n" + //
                      "\tuser_id, weight, height, steps, heart_rate, date)\n" + //
                      "\tVALUES (?, ?, ?, ?, ?, ?);";
 
         // Database logic to insert data using PREPARED Statement
         try{
             Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query);
             statement.setInt(1,h.getUserId());
             statement.setDouble(2,h.getWeight());
             statement.setDouble(3,h.getHeight());
             statement.setInt(4,h.getSteps());
             statement.setInt(5,h.getHeartRate());
             statement.setDate(6, java.sql.Date.valueOf(h.getDate()));
 
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


//       public HealthData getHealthDataById(int id) { 
        
//     }


//     public List<HealthData> getHealthDataByUserId(int userId) { 
      
// }



  //  public boolean updateHealthData(HealthData healthData) { /* update health data in the database */ }
  //  public boolean deleteHealthData(int id) { /* delete health data from the database */ }



}
