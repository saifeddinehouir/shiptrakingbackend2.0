package com.awb.VesselFInder.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageHandler {

    private final DataInsertion dataInsertion;

    @Autowired
    public MessageHandler(DataInsertion dataInsertion) {
        this.dataInsertion = dataInsertion;
    }

    public void parseMessage(String jsonString) throws JSONException {
        // Parse the JSON string
        JSONObject jsonObject = new JSONObject(jsonString);

        // Check if the MessageType is "PositionReport"
        String messageType = jsonObject.getString("MessageType");
        if ("PositionReport".equals(messageType)) {
            JSONObject positionReport = jsonObject.getJSONObject("Message").getJSONObject("PositionReport");
            // Extract the MetaData object
            JSONObject metaData = jsonObject.getJSONObject("MetaData");

            // Extract individual fields from the MetaData object
            int mmsi = metaData.getInt("MMSI");
            String shipName = metaData.getString("ShipName").trim();
            double latitude = metaData.getDouble("latitude");
            double longitude = metaData.getDouble("longitude");
            int heading = positionReport.getInt("TrueHeading");
            String timeUtc = metaData.getString("time_utc");

            // Print the extracted data
            System.out.println("MMSI: " + mmsi);
            System.out.println("Ship Name: " + shipName);
            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);
            System.out.println("TrueHeading: " + heading);
            System.out.println("Time UTC: " + timeUtc);

            // Store data in the database
            dataInsertion.storeVesselData(mmsi, shipName, latitude, longitude, heading, timeUtc);
        } else {
            System.out.println("Message type is not PositionReport.");
        }
    }
}
