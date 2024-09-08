package com.awb.VesselFInder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "port")
public class Port {

    @Setter
    @Id
    private int id;

    @Setter
    private String portName;

    @Setter
    private String unlocode;

    @Setter
    private int vesselinPort;

    @Setter
    private int departure;

    @Setter
    private int arrival;

    @Setter
    private int expectedArrival;

    @Setter
    private Timestamp localTime;

    @Setter
    private String relatedAnchorage;

    @Setter
    private String areaGlobal;

    @Setter
    private String areaLocal;

    @Setter
    private double longitude;  // New field for longitude

    @Setter
    private double latitude;   // New field for latitude

    // Constructor with all fields
    public Port(int id, String portName, String unlocode, int vesselinPort, int departure,
                int arrival, int expectedArrival, Timestamp localTime, String relatedAnchorage,
                String areaGlobal, String areaLocal, double longitude, double latitude) {
        this.id = id;
        this.portName = portName;
        this.unlocode = unlocode;
        this.vesselinPort = vesselinPort;
        this.departure = departure;
        this.arrival = arrival;
        this.expectedArrival = expectedArrival;
        this.localTime = localTime;
        this.relatedAnchorage = relatedAnchorage;
        this.areaGlobal = areaGlobal;
        this.areaLocal = areaLocal;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // Default constructor for JPA
    public Port() {
    }
}
