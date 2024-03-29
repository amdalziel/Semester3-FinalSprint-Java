// WORKING! Modify toString method? 

import java.time.LocalDate;

public class MedicineReminder {
    private int id;
    private int userId;
    private String medicineName;
    private String dosage;
    private String schedule;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructor 

    public MedicineReminder(int id, int uId, String mName, String d, String sch, LocalDate sDate, LocalDate eDate)
    {
        this.id = id; 
        this.userId = uId; 
        this.medicineName = mName; 
        this.dosage = d; 
        this.schedule = sch;
        this.startDate = sDate; 
        this.endDate = eDate; 
    }


    // Getters 

    public int getId()
    {
        return this.id; 
    }

    public int getUserId()
    {
        return this.userId; 
    }

    public String getMedicineName()
    {
        return  this.medicineName; 
    }

    public String getDosage()
    {
        return this.dosage; 
    }

    public String getSchedule()
    {
        return this.schedule; 
    }

    public LocalDate getStartDate()
    {
        return this.startDate; 
    }

    public LocalDate getEndDate()
    {
        return this.endDate; 
    }


    // Setters 

    public void setId( int i)
    {
        this.id = i; 
    }

    public void setUserId( int i)
    {
        this.userId = i; 
    }

    public void setMedicineName(String m)
    {
        this.medicineName = m; 
    }

    public void setDosage(String d)
    {
        this.dosage = d; 
    }

    public void setSchedule(String s)
    {
        this.schedule = s; 
    }

    public void setStartDate(LocalDate d)
    {
        this.startDate = d; 
    }

    public void setEndDate(LocalDate d)
    {
        this.endDate = d; 
    }


    // toString Method 

    public String toString()
    {
        return ("Medicine Reminder " + this.id + "for" + this.userId + ": " + this.medicineName + ", " + this.dosage); 
    }

}
