package com.awb.VesselFInder.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "vessel")
public class Vessel {

    // Getters and Setters
    @Setter
    @Id
    private int mmsi;
    @Setter
    private String shipName;
    @Setter
    private double latitude;
    @Setter
    private double longitude;
    @Setter
    private Integer heading; // Using Integer instead of int
    private Timestamp timeUtc;

    // Constructor
    public Vessel(int mmsi, String shipName, double latitude, double longitude, Integer heading, Timestamp timeUtc) {
        this.mmsi = mmsi;
        this.shipName = shipName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.heading = heading;
        this.timeUtc = timeUtc;
    }

    // Default constructor
    public Vessel() {
    }

    public void setTimeUtc(Timestamp timeUtc) {
        this.timeUtc = timeUtc;
    }

    @Override
    public String toString() {
        return "Vessel{" +
                "mmsi=" + mmsi +
                ", shipName='" + shipName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", heading=" + heading +
                ", timeUtc='" + timeUtc + '\'' +
                '}';
    }
}
