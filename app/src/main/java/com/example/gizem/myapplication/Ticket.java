package com.example.gizem.myapplication;

import java.util.Date;

/**
 * Created by Gizem on 25.05.2016.
 */
public class Ticket {

    String language;
    String id;
    Flight flightInfo;

    public Ticket(String id, String langugage, String flightId, Date flightTime, String flightGate) {
        this.id = id;
        this.language = langugage;
        flightInfo = new Flight(flightId,flightTime,flightGate);
    }
    public String getId() {
        return id;

    }
    public  String getLanguage() {
        return  language;
    }

    public  Flight getFlightInfo() {
        return  flightInfo;
    }

    private class Flight {
        String flightId;
        Date flightTime;
        String flightGate;

        public Flight(String flightId, Date flightTime, String flightGate) {
            this.flightId = flightId;
            this.flightTime = flightTime;
            this.flightGate = flightGate;
        }


    }

}
