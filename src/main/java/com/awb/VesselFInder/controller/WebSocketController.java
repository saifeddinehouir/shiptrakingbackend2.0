package com.awb.VesselFInder.controller;

import com.awb.VesselFInder.service.WebSocketClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebSocketController {

    @Autowired
    private WebSocketClientService webSocketClientService;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        webSocketClientService.sendMessage(message);
        return "Message sent: " + message;
    }

    @GetMapping("/close")
    public String closeConnection() {
        webSocketClientService.closeConnection();
        return "WebSocket connection closed";
    }
}
