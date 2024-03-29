

// import com.DataBaseConnection;
import java.sql.Date;
import java.sql.SQLException;
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

        Doctor doc3 = new Doctor(8, "Ameliaa", "Dalziel", "aaaamelia@gmai.com", "aaa", true, "OBGYN", "66677728"); 

        docDao.createDoctor(doc3); 

        PatientPortalDao patDao = new PatientPortalDao(); 

        Patient p = new Patient(10, "Chris", "McPhee", "chris@email.com", "chris", false); 

        patDao.createPatient(p); 


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
