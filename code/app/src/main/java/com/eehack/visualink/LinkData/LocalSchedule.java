package com.eehack.visualink.LinkData;

import com.eehack.visualink.Basics.Weekday;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Date;

/**
 * Created by Florian on 06.03.2017.
 */

public class LocalSchedule extends ScheduleInterface {
    @Override
    public List<ScheduleEntity> getAllEntities(String room) {
        List<ScheduleEntity> entities = new ArrayList<ScheduleEntity>();

        List<Weekday> workWeek = new ArrayList<Weekday>();
        workWeek.add(Weekday.Monday);
        workWeek.add(Weekday.Tuesday);
        workWeek.add(Weekday.Wednesday);
        workWeek.add(Weekday.Thursday);
        workWeek.add(Weekday.Friday);

        List<Weekday> weekend = new ArrayList<Weekday>();
        weekend.add(Weekday.Saturday);
        weekend.add(Weekday.Sunday);

        List<Weekday> week = new ArrayList<Weekday>();
        week.addAll(workWeek);
        week.addAll(weekend);

        SimpleDateFormat ft = new SimpleDateFormat ("HH:MM");

        try{

            //Workdays
            ScheduleEntity morningWork = new ScheduleEntity(ft.parse("06:00"), ft.parse("8:00"), workWeek, "Bedroom", 21);
            ScheduleEntity work = new ScheduleEntity(ft.parse("08:00"), ft.parse("17:00"), workWeek, "Bedroom", 18);
            ScheduleEntity afternoonWork = new ScheduleEntity(ft.parse("17:00"), ft.parse("22:00"), workWeek, "Bedroom", 21);
            ScheduleEntity night = new ScheduleEntity(ft.parse("22:00"), ft.parse("06:00"), workWeek, "Bedroom", 18);

            //Weekend
            ScheduleEntity day = new ScheduleEntity(ft.parse("09:00"), ft.parse("22:00"), workWeek, "Bedroom", 21);
            ScheduleEntity nightWE = new ScheduleEntity(ft.parse("22:00"), ft.parse("09:00"), workWeek, "Bedroom", 18);

        }catch(ParseException e){

        }







        entities.add(new ScheduleEntity(new Date(), new Date(), new ArrayList<Weekday>(), room, 18));



        return entities;
    }
}
