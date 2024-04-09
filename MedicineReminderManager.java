
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


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


    public <T> Boolean updateReminder(String selection, T value, MedicineReminder rem) {

        Boolean bool = false; 
         

        switch (selection) {
            case "medicine_name":

            String medName = (String) value; 
            String queryMedName = "UPDATE public.medicine_reminders\n" + //
                                "\tSET medicine_name=?\n" + //
                                "\tWHERE id=?;"; 

        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(queryMedName);

            statement.setString(1, medName);
            statement.setInt(2, rem.getId());
        

            int updatedRows = statement.executeUpdate();
            if (updatedRows != 0) {
                bool = true;
            }
            break; 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
         
            case "dosage":

            String dos = (String) value; 
            String queryDos = "UPDATE public.medicine_reminders\n" + //
                                "\tSET dosage=?\n" + //
                                "\tWHERE id=?;"; 

            try {
                Connection con = DatabaseConnection.getCon();
                PreparedStatement statement = con.prepareStatement(queryDos);

                statement.setString(1, dos);
                statement.setInt(2, rem.getId());
            

                int updatedRows = statement.executeUpdate();
                if (updatedRows != 0) {
                    bool = true;
                }
                break; 
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            
            case "schedule":

            String sch = (String) value; 
            String querySch = "UPDATE public.medicine_reminders\n" + //
                                "\tSET schedule=?\n" + //
                                "\tWHERE id=?;"; 

            try {
                Connection con = DatabaseConnection.getCon();
                PreparedStatement statement = con.prepareStatement(querySch);

                statement.setString(1, sch);
                statement.setInt(2, rem.getId());
            

                int updatedRows = statement.executeUpdate();
                if (updatedRows != 0) {
                    bool = true;
                }
                break; 
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            
            case "start_date":

            LocalDate startD = (LocalDate) value; 
            String queryStartD = "UPDATE public.medicine_reminders\n" + //
                                "\tSET start_date=?\n" + //
                                "\tWHERE id=?;"; 

            try {
                Connection con = DatabaseConnection.getCon();
                PreparedStatement statement = con.prepareStatement(queryStartD);

                statement.setDate(1, java.sql.Date.valueOf(startD));
                statement.setInt(2, rem.getId());
            
                int updatedRows = statement.executeUpdate();
                if (updatedRows != 0) {
                    bool = true;
                }
                break; 
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        
            case "end_date":

            LocalDate endD = (LocalDate) value; 
            String queryEndD = "UPDATE public.medicine_reminders\n" + //
                                "\tSET end_date=?\n" + //
                                "\tWHERE id=?;"; 

            try {
                Connection con = DatabaseConnection.getCon();
                PreparedStatement statement = con.prepareStatement(queryEndD);

                statement.setDate(1, java.sql.Date.valueOf(endD));
                statement.setInt(2, rem.getId());
            
                int updatedRows = statement.executeUpdate();
                if (updatedRows != 0) {
                    bool = true;
                }
                break; 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
            default:

            System.out.println("Error: please enter one of the following values: medication_name, dosage, schedule, start_date or end_date."); 
                break;
        }

        if(bool) {
            System.out.println("Update for Medicine Reminder " + rem.getId() + " complete.");
        } 

        return bool; 

    } 







    public boolean deleteReminder(int id) { 

        boolean bool = false; 

        // Prepare the SQL query

        String query = "DELETE FROM public.medicine_reminders\n" + //
                        "\tWHERE id=?;";

        
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


}
