

// import com.DataBaseConnection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 


public class HealthMonitoringApp {

    private static UserDao userDao = new UserDao();

    public static void main(String[] args) {
       DatabaseConnection databaseConnection = new DatabaseConnection();


        // Test 1: Register a new user
        testRegisterUser(); 

        // Test 2: Log in a user 
        // testLoginUser(); 

        // Test 3: Add Health Data 
          // testAddHealthData(); 

        // Test 4: Generate Recommendations 
        // testGenerateRecommendations(); 

        // Test 5: Add a Medicine Reminder 
        // Test 6: Get Reminders for a Specific User 
        // Test 7: Get DUE Reminders for a Specific User
        // testAddMedicineReminder(); 

        // Test 8: Test the Doctor Portal 
        // testDoctorPortal(); 




        // Add a medicine reminder
        // Get reminders for a specific user
        // Get due reminders for a specific user
        //test doctor portal (call testDoctorPortal() here)







    }


    public static void testRegisterUser(){


      DoctorPortalDao doctorDao = new DoctorPortalDao(); 
      PatientPortalDao patientDao = new PatientPortalDao(); 

      List<Patient> patientList = new ArrayList<>();
      List<Doctor> doctorList = new ArrayList<>();

      Patient user1 = new Patient(2,"Luke", "McDonald","luke@gmail.com", "luke");
      Patient user2 = new Patient(3,"Josh", "MacIntosh","josh@gmail.com", "josh");
      Patient user3 = new Patient(4,"Amy", "Young","amy@gmail.com", "amy");
      patientList.add(user1);
      patientList.add(user2);
      patientList.add(user3);

      Doctor user4 = new Doctor(5,"Karin", "Neville","karin@gmail.com", "karinDoc", "OBGYN", "990000");
      Doctor user5 = new Doctor(6,"Kyle", "Dawe","kyle@gmail.com", "kyleDoc", "Plastic Surgeon", "990286");
      Doctor user6 = new Doctor(7,"Ada", "Adams","ada@gmail.com", "adaDoc", "Dermatologist", "990287");
      doctorList.add(user4);
      doctorList.add(user5);
      doctorList.add(user6);

      for (Patient patients : patientList) {
          patientDao.createUser(patients);
       }

        for(Doctor doctors : doctorList) {
        doctorDao.createUser(doctors); 
      }

      patientDao.updateUser("email", "newemail@gmail.com", user2); 


    }


    public static boolean testLoginUser() {
        
      String email; 
      String password; 

      Scanner input = new Scanner(System.in); 
      System.out.println("Enter your email: "); 
      email = input.nextLine(); 

      User user = userDao.getUserByEmail(email);

      if (user.getEmail() != null) {

        System.out.println("Enter your password: "); 
        password = input.nextLine(); 

        int uId = user.getId(); 

        Boolean testPassword = userDao.verifyPassword(uId, password); 

        if(testPassword) {
          System.out.println("User " + user.getId() + " has successfully logged in."); 
          return true; 

        } else {
          System.out.println("Error - password not accepted."); 
          return false; 
        }
    
      } else {
        System.out.println("Error - email is not in our database."); 

      }

        return false;

    }


    public static void testAddHealthData() {

      HealthDataDao healthDataDao = new HealthDataDao(); 

      List<HealthData> healthDataList = new ArrayList<>(); 

      HealthData healthData1 = new HealthData(1, 2, 61, 1.65, 105000, 80, LocalDate.of(2024, 4, 8)); 
      HealthData healthData2 = new HealthData(2, 3, 80, 1.5, 6000, 95, LocalDate.of(2024, 4, 5)); 
      HealthData healthData3 = new HealthData(3, 4, 65, 1.80, 4500, 80, LocalDate.of(2024, 4, 1)); 
      healthDataList.add(healthData1); 
      healthDataList.add(healthData2); 
      healthDataList.add(healthData3); 


      for(HealthData data : healthDataList) {
        healthDataDao.createHealthData(data); 
      }

    }


    public static void testGenerateRecommendations(){

      RecommendationSystem recSystem = new RecommendationSystem(); 

      HealthDataDao healthDataDao = new HealthDataDao(); 
      HealthData h1 = healthDataDao.getHealthDataById(1); 
      HealthData h2 = healthDataDao.getHealthDataById(2); 
      HealthData h3 = healthDataDao.getHealthDataById(3); 


      List<String> rec1 = recSystem.generateRecommendations(h1); 
      List<String> rec2 = recSystem.generateRecommendations(h2); 
      List<String> rec3 = recSystem.generateRecommendations(h3); 
      

      System.out.println("Recommendation for Health Data ID 1: "); 
      if (rec1.size() == 0) {
        System.out.println("No recommendations recorded."); 
      } else {
        for(String recommendation : rec1) 
        {
          System.out.println(recommendation); 
        }
        
      }
    
      
      System.out.println("Recommendation for Health Data ID 2: "); 
      if (rec2.size() == 0) {
        System.out.println("No recommendations recorded."); 
      } else {
        for(String recommendation : rec2) 
        {
          System.out.println(recommendation); 
        }
        
      }


      System.out.println("Recommendation for Health Data ID 3: "); 
      if (rec3.size() == 0) {
        System.out.println("No recommendations recorded."); 
      } else {
        for(String recommendation : rec3) 
        {
          System.out.println(recommendation); 
        }
        
      }

    }


    public static void testAddMedicineReminder(){

      MedicineReminderManager medRemManager = new MedicineReminderManager(); 

      MedicineReminder medRem1 = new MedicineReminder(1, 2, "Tylenol", "20ml", "1x daily", LocalDate.of(2024, 2, 1), LocalDate.of(2024, 9, 5)); 
      MedicineReminder medRem2 = new MedicineReminder(2, 2, "Eye Drops", "2 drops", "1x morning, 1x evening", LocalDate.of(2024, 3, 10), LocalDate.of(2024, 5, 5)); 
      MedicineReminder medRem3 = new MedicineReminder(3, 2, "Elidel", "Fingertip amount", "2x daily", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 8)); 
      MedicineReminder medRem4 = new MedicineReminder(4, 3, "CereVe Eczema Cream", "Fingertip amount", "1x daily", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 8)); 

      medRemManager.addReminder(medRem1); 
      medRemManager.addReminder(medRem2);
      medRemManager.addReminder(medRem3);
      medRemManager.addReminder(medRem4);
      
    
      List<MedicineReminder> allRem = medRemManager.getRemindersForUser(2); 

      List<MedicineReminder> dueRem = medRemManager.getDueRemindersForUser(2); 

      System.out.println("All reminders: "); 
      if (allRem.size() == 0) {
        System.out.println("No reminders recorded."); 
      } else {
        for(MedicineReminder reminder : allRem) 
        {
          System.out.println(reminder); 
        }
      }


      System.out.println("DUE reminders: "); 
      if (dueRem.size() == 0) {
        System.out.println("No reminders recorded."); 
      } else {
        for(MedicineReminder reminder : dueRem) 
        {
          System.out.println(reminder); 
        }
      }

    }



    public static void testDoctorPortal() {
     
        DoctorPortalDao doctorDao = new DoctorPortalDao(); 
        PatientPortalDao patientDao = new PatientPortalDao(); 

        int doctorId = 7;

        Doctor doc7 = doctorDao.getDoctorById(doctorId); 
        Patient pat2 = patientDao.getPatientById(2); 
        Patient pat3 = patientDao.getPatientById(3); 

        System.out.println(doctorDao.getDoctorById(doctorId)); 

        doctorDao.addPatientToDoctorList(doc7, pat2); 
        doctorDao.addPatientToDoctorList(doc7, pat3); 


     List<User> listPat = doctorDao.getPatientsByDoctorId(doctorId); 


      System.out.println("Patients for Doctor with ID 7: "); 
      if (listPat.size() == 0) {
        System.out.println("No patients recorded."); 
      } else {
        for(User patient : listPat) 
        {
          System.out.println(patient); 
        }
      }

      List<HealthData> listHealthD = doctorDao.getHealthDataByPatientId(2); 

      System.out.println("Health Data for Patients of Doctor with ID 7: "); 
      if (listHealthD.size() == 0) {
        System.out.println("No data recorded."); 
      } else {
        for(HealthData healthD : listHealthD) 
        {
          System.out.println(healthD); 
        }
      }

        

    }



}
