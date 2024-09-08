package com.awb.VesselFInder.repository;


import com.awb.VesselFInder.entity.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VesselRepository extends JpaRepository<Vessel, Long> {
    Vessel findByMmsi(int mmsi);
}