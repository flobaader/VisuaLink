package com.eehack.visualink.LinkData;

import com.eehack.visualink.Basics.Weekday;

import java.util.Date;
import java.util.List;


/**
 * Created by Florian on 06.03.2017.
 */

public class ScheduleEntity{
    private Date startTime;
    private Date endTime;
    private List<Weekday> days;
    private String roomName;
    private double temperature;

    public ScheduleEntity(Date startTime, Date endTime, List<Weekday> days, String roomName, double temperature) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
        this.roomName = roomName;
        this.temperature = temperature;
    }

    public int getDurationInMinutes(){
        //getTime() in Milliseconds
        return (int) (endTime.getHours() * 60  + endTime.getMinutes() - startTime.getHours() * 60 - startTime.getMinutes());
    }

    public String getRoomName() {
        return roomName;
    }

    public double getTemperature() {
        return temperature;
    }

    public List<Weekday> getDays() {
        return days;
    }
}