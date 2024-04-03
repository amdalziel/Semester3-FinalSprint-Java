

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RecommendationSystem {
    private static final int MIN_HEART_RATE = 60;
    private static final int MAX_HEART_RATE = 100;
    private static final int MIN_STEPS = 10000;
    private static final double OVERWEIGHT_BMI = 25.00; 
    private static final double UNDERWEIGHT_BMI = 18.5; 

    public List<String> generateRecommendations(HealthData healthData) {
        List<String> recommendations = new ArrayList<>();

        int id = 7; 
        LocalDate now = LocalDate.now(); 

        // Analyze BMI 

        double weight = healthData.getWeight(); 
        double height = healthData.getHeight(); 

        double dataBMI = weight / (height * height); 
        // System.out.println(dataBMI);

        if(dataBMI > OVERWEIGHT_BMI) {
                recommendations.add("Your BMI is in the overweight category." + 
                "Consider taking steps to increase your physical activity and maintain a more healthy & balanced diet.");   
        }

        if(dataBMI < UNDERWEIGHT_BMI) {
                recommendations.add("Your BMI is in the undertweight category." + 
                "Consider adjusting your diet to increase your caloric intake."); 
        }
        

        // Analyze heart rate
        int heartRate = healthData.getHeartRate();
        if (heartRate < MIN_HEART_RATE) {
            recommendations.add("Your heart rate is lower than the recommended range. " +
                    "Consider increasing your physical activity to improve your cardiovascular health.");
        }

        if (heartRate > MAX_HEART_RATE) {
                recommendations.add("Your heart rate is higher than the recommended range. " +
                        "Be sure to maintain a healthy diet, stay hydrated and include physical activity in your daily lifestyle.");
            }


        // Analyze steps
        int steps = healthData.getSteps();
        if (steps < MIN_STEPS) {
            recommendations.add("You're not reaching the recommended daily step count. " +
                    "Try to incorporate more walking or other physical activities into your daily routine.");
        }

        String[] recArray = recommendations.toArray(new String[recommendations.size()]); 

        // Add more health data analysis and recommendations as needed

        String query = "INSERT INTO public.recommendations(\n" + //
                "id, user_id, recommendation_text, date)\n" + //
                "VALUES (?, ?, ?, ?);"; 

        
        // Database logic
        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            statement.setInt(2, healthData.getUserId());
            statement.setArray(3, con.createArrayOf("text", recArray));
            statement.setDate(4, java.sql.Date.valueOf(now));
            
            int updatedRows = statement.executeUpdate();
            if (updatedRows != 0) {
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recommendations;
    }
}
