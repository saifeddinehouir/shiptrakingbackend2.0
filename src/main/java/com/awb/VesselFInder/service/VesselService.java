package com.awb.VesselFInder.service;

import com.awb.VesselFInder.entity.Vessel;
import com.awb.VesselFInder.repository.VesselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VesselService {

    @Autowired
    private VesselRepository vesselRepository;

    public List<Vessel> getAllVessels() {
        return vesselRepository.findAll();
    }

    public Vessel getVesselByMmsi(int mmsi) {
        return vesselRepository.findByMmsi(mmsi);
    }}
