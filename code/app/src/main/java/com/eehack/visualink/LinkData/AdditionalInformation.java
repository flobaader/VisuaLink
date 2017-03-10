package com.eehack.visualink.LinkData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian on 06.03.2017.
 */

public class AdditionalInformation {


    public static double getDefaultTemperature() {
        return 21.00;
    }

    public List<String> getRoomNames() {
        List<String> roomNames = new ArrayList<String>();

        roomNames.add("Living Room");
        roomNames.add("Bathroom");
        roomNames.add("Bedroom");

        return roomNames;
    }

}