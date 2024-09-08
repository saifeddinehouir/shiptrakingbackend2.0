package com.awb.VesselFInder.service;

import com.awb.VesselFInder.entity.Vessel;
import com.awb.VesselFInder.repository.VesselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

@Service
public class DataInsertion {

    private final VesselRepository vesselRepository;

    @Autowired
    public DataInsertion(VesselRepository vesselRepository) {
        this.vesselRepository = vesselRepository;
    }

    public void storeVesselData(int mmsi, String shipName,
                                double latitude, double longitude, int heading, String timeUtc) {
        Timestamp sqlTimestamp = ConvertToTimestamp(timeUtc);
        Vessel existingVessel = vesselRepository.findByMmsi(mmsi);

        if (existingVessel != null) {
            existingVessel.setShipName(shipName);
            existingVessel.setLatitude(latitude);
            existingVessel.setLongitude(longitude);
            existingVessel.setHeading(heading);
            existingVessel.setTimeUtc(sqlTimestamp);
            vesselRepository.save(existingVessel);
        } else {
            Vessel newVessel = new Vessel();
            newVessel.setMmsi(mmsi);
            newVessel.setShipName(shipName);
            newVessel.setLatitude(latitude);
            newVessel.setLongitude(longitude);
            newVessel.setHeading(heading);
            newVessel.setTimeUtc(sqlTimestamp);
            vesselRepository.save(newVessel);
        }
    }

    private Timestamp ConvertToTimestamp(String timeUtc) {
        // Remove "+0000 UTC" part
        String cleanedTimeUtc = timeUtc.replace(" +0000 UTC", "");

        // Define the formatter to handle up to nanoseconds
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .optionalStart()
                .appendFraction(ChronoField.NANO_OF_SECOND, 1, 9, true)
                .optionalEnd()
                .toFormatter();

        // Parse to LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(cleanedTimeUtc, formatter);

        // Convert to SQL Timestamp
        return Timestamp.valueOf(localDateTime);
    }
}
