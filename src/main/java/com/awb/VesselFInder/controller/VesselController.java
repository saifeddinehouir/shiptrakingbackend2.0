package com.awb.VesselFInder.controller;


import com.awb.VesselFInder.entity.Vessel;
import com.awb.VesselFInder.service.VesselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vessel")
public class VesselController {

    @Autowired
    private VesselService vesselService;

    @GetMapping
    public List<Vessel> getAllVessels() {
        return vesselService.getAllVessels();
    }

    @GetMapping("/{mmsi}")
    public Vessel getVesselByMmsi(@PathVariable int mmsi) {
        return vesselService.getVesselByMmsi(mmsi);
    }
}
