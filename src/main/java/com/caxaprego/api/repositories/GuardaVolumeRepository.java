package com.caxaprego.api.repositories;

import com.caxaprego.api.models.GuardaVolume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GuardaVolumeRepository extends JpaRepository<GuardaVolume, UUID> {

}
