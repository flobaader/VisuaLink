package com.eehack.visualink.LinkData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian on 06.03.2017.
 */

public abstract class ScheduleInterface {

    public abstract List<ScheduleEntity> getAllEntities(String room);

    public double getSavingInPercent(String room){
        double savingPercent = 0;

        //Formula: Energy = temperature² * duration

        double defaultEnergy = AdditionalInformation.getDefaultTemperature()
                * AdditionalInformation.getDefaultTemperature()
                * 60 * 24;

        double currentEnergy = 0;

        for (ScheduleEntity e : getAllEntities(room)){
            if(e.getRoomName() == room){
                currentEnergy += e.getTemperature() * e.getTemperature() * e.getDurationInMinutes();
            }
        }
        savingPercent = 100 - (currentEnergy / defaultEnergy);

        return savingPercent;
    }

}

