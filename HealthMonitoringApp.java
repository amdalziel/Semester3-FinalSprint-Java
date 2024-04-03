

// import com.DataBaseConnection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class HealthMonitoringApp {

    private static UserDao userDao = new UserDao();
    /**
     * Test the following functionalities within the Main Application
     *  1. Register a new user
     *  2. Log in the user
     *  3. Add health data
     *  4. Generate recommendations
     *  5. Add a medicine reminder
     *  6. Get reminders for a specific user
     *  7. Get due reminders for a specific user
     *  8. test doctor portal
     */
    public static void main(String[] args) {
       DatabaseConnection databaseConnection = new DatabaseConnection();
        UserDao userDao = new UserDao();
        // test register a new user
        // test Login user (call testLoginUser() here)
        // Add health data
        // Generate recommendations
        // Add a medicine reminder
        // Get reminders for a specific user
        // Get due reminders for a specific user
        //test doctor portal (call testDoctorPortal() here)


        // List<User> userList = new ArrayList<>();

        // User user1 = new User(3,"Luke", "Tobin","luke@gmail.com", "luke", false);
        // userList.add(user1);

        // User user2 = new User(5,"Amelia", "Tobin","a@gmail.com", "aaa", true);
        // userList.add(user2);

        // for (User users : userList) {
        //     userDao.createUser(users);
        // }

        // System.out.println(userDao.getUserById(3)); 

        // System.out.println(); 

        // System.out.println(userDao.getUserByEmail("amy@gmail.com")); 

        // System.out.println(userDao.deleteUser(2)); 

        DoctorPortalDao docDao = new DoctorPortalDao(); 

        System.out.println(docDao.getPatientsByDoctorId(3)); 

        HealthDataDao healthD = new HealthDataDao(); 


        System.out.println(healthD.getHealthDataByUserId(3));

        // HealthData hdata22 = new HealthData(5, 10, 120.00, 6.1, 9000, 130, LocalDate.of(2024, 3, 10));

        // healthD.createHealthData(hdata22); 

        // System.out.println(healthD.getHealthDataById(5)); 

        // healthD.updateHealthData("height", 6.5, hdata22); 

        // System.out.println(healthD.getHealthDataById(5)); 

        System.out.println(docDao.getHealthDataByPatientId(10));
    

    



        // Doctor doc3 = new Doctor(8, "Ameliaa", "Dalziel", "aaaamelia@gmai.com", "aaa", true, "OBGYN", "66677728"); 

        // docDao.createDoctor(doc3); 

        // PatientPortalDao patDao = new PatientPortalDao(); 

        // Patient p = new Patient(10, "Chris", "McPhee", "chris@email.com", "chris", false); 

        // patDao.createPatient(p); 

        // docDao.addPatientToDoctorList(doc3, p); 

        // UserDao userD = new UserDao();

        // DoctorPortalDao docPD = new DoctorPortalDao(); 

        // PatientPortalDao pDao = new PatientPortalDao(); 

        // Patient p1 = new Patient(13, "Amy", "Dalziel", "aaa@email.com", "amyy"); 

        // pDao.createPatient((p1)); 

        // System.out.println(pDao.getUserById(13)); 



        // System.out.println(docPD.getUserDao()); 





      MedicineReminderManager mrManager = new MedicineReminderManager(); 

    //   System.out.println(mrManager.getRemindersForUser(3)); 

    //   System.out.println(mrManager.getDueRemindersForUser(3)); 

    //   MedicineReminder m1 = new MedicineReminder(5, 3, "Med1", "500grams", "2x day", LocalDate.of(2024, 01, 01), LocalDate.of(2024, 02, 05)); 

    //   mrManager.addReminder(m1); 

    //   System.out.println(mrManager.getDueRemindersForUser(3)); 


    //   System.out.println(mrManager.getRemindersForUser(8)); 

    //   mrManager.deleteReminder(1); 

    //   mrManager.updateReminder("start_date", LocalDate.of(2025, 1, 01), m1); 

    //   HealthDataDao hdDao = new HealthDataDao(); 

    // HealthData hdata1 = new HealthData(2, 8, 120, 55, 8700, 86,  LocalDate.of(2025, 1, 01)); 

    //   hdDao.createHealthData(hdata1); 







        // System.out.print(docDao.getDoctorById(5)); 
    }


    // public static boolean loginUser(String email, String password) {
    //     //implement method to login user.
    //     User user = userDao.getUserByEmail(email);

    //     if (user != null) {
    //         // Compare the stored hashed password with the given password and return result
    //     }

    //     return false;

    // }


    /**
     * To test the Doctor Portal in your Health Monitoring System, provide a simple test code method that you can add
     * to your main application class.
     * In this method, we'll test the following functionalities:
     * 1. Fetching a doctor by ID
     * 2. Fetching patients associated with a doctor
     * 3. Fetching health data for a specific patient
    //   */
    // public static void testDoctorPortal() {
    //     // Replace the doctorId with a valid ID from your database
    //     int doctorId = 1;

    //     // Add code to Fetch the doctor by ID

    //     // Add code to Fetch patients associated with the doctor

    //     // Add code to Fetch health data for the patient

    // }


    /**
     * To test the login user functionality in your Health Monitoring System, you can
     * add a test method to your main application class
    //  */
    // public static void testLoginUser() {
    //     // Replace the email and password with valid credentials from your database
    //     String userEmail = "john@example.com";
    //     String userPassword = "password";

    //     boolean loginSuccess = loginUser(userEmail, userPassword);

    //     if (loginSuccess) {
    //         // Print to console, "Login Successful"
    //     } else {
    //         // Print to console, "Incorrect email or password. Please try again.");
    //         // Show an error message and prompt the user to re-enter their credentials
    //     }
    // }

}
