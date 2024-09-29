package com.fancode.automation.model;

public class UserProfile {

    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private int totalTasks;
    private int completedTasks;

    public double calculateCompletionRate() {
        return (totalTasks > 0) ? (completedTasks / (double) totalTasks) * 100 : 0;
    }

    public boolean isFromFanCodeCity() {
        return latitude >= -40 && latitude <= 5 && longitude >= 5 && longitude <= 100;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public int getTotalTasks() { return totalTasks; }
    public void setTotalTasks(int totalTasks) { this.totalTasks = totalTasks; }

    public int getCompletedTasks() { return completedTasks; }
    public void setCompletedTasks(int completedTasks) { this.completedTasks = completedTasks; }
}
