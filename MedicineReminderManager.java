

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
 * The MedicineReminderManager class should have methods to add reminders, get reminders
 *  1. for a specific user, and
 *  2. get reminders that are DUE for a specific user.
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

    // public void addReminder(MedicineReminder r) {

    // //     INSERT INTO public.health_data(
	// // user_id, weight, height, steps, heart_rate, date)
	// // VALUES (3, 24.5, 55.00, 2500, 135, TO_DATE('2024-05-10', 'YYYY-MM-DD'));

    //     reminders.add(r);
    // }

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

    public List<MedicineReminder> getDueReminders(int userId) {
        List<MedicineReminder> dueReminders = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Write your logic here

        return dueReminders;
    }
}
