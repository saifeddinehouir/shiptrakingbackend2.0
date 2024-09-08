package com.awb.VesselFInder.service;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Service
public class WebSocketClientService {

    private WebSocketClient webSocketClient;

    @Autowired
    private MessageHandler messageHandler;

    private final String apiKey = "97bc2d0359b033cb9e1b46df5dcf78e4885dcd78"; // Move this to config or environment variable for security

    // No-argument constructor for Spring
    public WebSocketClientService() throws URISyntaxException {
        // Define your WebSocket URI here
        URI uri = new URI("wss://stream.aisstream.io/v0/stream");

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                System.out.println("WebSocket connection opened");
                // Send subscription message with API key upon connection
                String subscriptionMessage = "{\"APIKey\":\"" + apiKey + "\",\"BoundingBoxes\":[[[-90,-180],[90,180]]]}";
                send(subscriptionMessage);
            }

            @Override
            public void onMessage(ByteBuffer message) {
                // Convert the ByteBuffer to a String (UTF-8) for further processing
                String jsonString = StandardCharsets.UTF_8.decode(message).toString();
                try {
                    messageHandler.parseMessage(jsonString); // Process message with MessageHandler
                } catch (Exception e) {
                    System.err.println("Error processing message: " + e.getMessage());
                    System.err.println("Original message: " + jsonString);
                }
            }

            @Override
            public void onMessage(String message) {
                // This is unused because aisstream.io sends ByteBuffer messages
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("WebSocket connection closed with reason: " + reason);
            }

            @Override
            public void onError(Exception ex) {
                System.err.println("WebSocket error: " + ex.getMessage());
            }
        };

        // Connect the WebSocket client
        webSocketClient.connect();
    }

    public void sendMessage(String message) {
        if (webSocketClient != null && webSocketClient.isOpen()) {
            webSocketClient.send(message);
        } else {
            System.out.println("WebSocket is not open, message not sent");
        }
    }

    public void closeConnection() {
        if (webSocketClient != null && webSocketClient.isOpen()) {
            webSocketClient.close();
        }
    }
}
