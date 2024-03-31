

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The MedicineReminderManager class should have methods to add reminders,
 * 
 *  You'll need to integrate this class with your application and database logic to
 *  1. store,
 *  2. update, and
 *  3. delete reminders as needed.
 */

public class MedicineReminderManager {

    private List<MedicineReminder> reminders;

    public MedicineReminderManager() {
        this.reminders = new ArrayList<>();
    }

    public Boolean addReminder(MedicineReminder r) {

        Boolean bool = false; 

         // Prepare the SQL query
         String query = "INSERT INTO public.medicine_reminders(\n" + //
                          "\tid, user_id, medicine_name, dosage, schedule, start_date, end_date)\n" + //
                          "\tVALUES (?, ?, ?, ?, ?, ?, ?);";
 
         // Database logic to insert data using PREPARED Statement
         try{
             Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query);
             statement.setInt(1,r.getId());
             statement.setInt(2,r.getUserId());
             statement.setString(3,r.getMedicineName());
             statement.setString(4,r.getDosage());
             statement.setString(5,r.getSchedule());
             statement.setDate(6, java.sql.Date.valueOf(r.getStartDate()));
             statement.setDate(7,java.sql.Date.valueOf(r.getEndDate()));
 
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

    public List<MedicineReminder> getRemindersForUser(int uId) {

        List<MedicineReminder> userReminders = new ArrayList<>();

        int id = 0;
        int user_id = uId;
        String medicineName = null;
        String dos = null;
        String sch = null;
        LocalDate startD = null;
        LocalDate endD = null;


      String query = "SELECT id, user_id, medicine_name, dosage, schedule, start_date, end_date\n" + //
      "\tFROM public.medicine_reminders\n" + //
      "\tWHERE user_id = ?;"; 

       // Database logic to insert data using PREPARED Statement
        try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, uId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                user_id = rs.getInt("user_id");
                medicineName = rs.getString("medicine_name");
                dos = rs.getString("dosage");
                sch = rs.getString("schedule");
                startD = rs.getDate("start_date").toLocalDate();
                endD = rs.getDate("end_date").toLocalDate();

                MedicineReminder reminder = new MedicineReminder(id, user_id, medicineName, dos, sch, startD, endD);
                userReminders.add(reminder);
            }

    } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return userReminders;
    }


    // Add enumeration - different values depending on how overdue it is?? 

    public List<MedicineReminder> getDueRemindersForUser(int uId) {

        List<MedicineReminder> dueReminders = new ArrayList<>();
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Write your logic here
        int id = 0;
        int user_id = uId;
        String medicineName = null;
        String dos = null;
        String sch = null;
        LocalDate startD = null;
        LocalDate endD = null;


      String query = "SELECT id, user_id, medicine_name, dosage, schedule, start_date, end_date\n" + //
                    "\tFROM public.medicine_reminders\n" + //
                    "\tWHERE user_id = ? AND end_date <= ?;"; 

       // Database logic to insert data using PREPARED Statement
        try{
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, uId);
            statement.setObject(2, now); // fix this bug 

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                user_id = rs.getInt("user_id");
                medicineName = rs.getString("medicine_name");
                dos = rs.getString("dosage");
                sch = rs.getString("schedule");
                startD = rs.getDate("start_date").toLocalDate();
                endD = rs.getDate("end_date").toLocalDate();

                MedicineReminder reminder = new MedicineReminder(id, user_id, medicineName, dos, sch, startD, endD);
                dueReminders.add(reminder);
            }

    } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return dueReminders;
    }
}
