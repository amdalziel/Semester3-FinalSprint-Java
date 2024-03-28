// WORKING

import java.time.LocalDate;

public class HealthData {
    private int healthDataId;
    private int userId;
    private double weight;
    private double height;
    private int steps;
    private int heartRate;
    private LocalDate date; 

    // Constructor Method 

    public HealthData(int hId, int uId, double w, double h, int st, int hrate, LocalDate d)
    {
        this.healthDataId = hId;
        this.userId = uId; 
        this.weight = w;
        this.height = h;
        this.steps = st;
        this.heartRate = hrate; 
        this.date = d; 
    }


    // Getters 

    public int getHealthDataId()
    {
        return this.healthDataId; 
    }

    public int getUserId()
    {
        return this.userId; 
    }

    public double getWeight()
    {
        return this.weight; 
    }

    public double getHeight()
    {
        return this.height; 
    }

    public int getSteps()
    {
        return this.steps; 
    }

    public int getHeartRate()
    {
        return this.heartRate; 
    }

    public LocalDate getDate()
    {
        return this.date; 
    }
    

    // Setters 

    public void setHealthDataId(int hId)
    {
        this.healthDataId = hId; 
    }

    public void setUserId(int uId)
    {
        this.userId = uId; 
    }

    public void setWeight(double w)
    {
        this.weight = w; 
    }

    public void setHeight(double h)
    {
        this.height = h; 
    }

    public void setSteps(int s)
    {
        this.steps = s;  
    }

    public void setHeartRate(int hr)
    {
        this.heartRate = hr; 
    }

    public void setDate(LocalDate d)
    {
        this.date = d; 
    }


    public String toString()
    {
        return ("Health Data " + this.healthDataId ); 
    }
}
