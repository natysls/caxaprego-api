package com.example.springboot.repositories;

import com.example.springboot.models.Armario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArmarioRepository extends JpaRepository<Armario, UUID> {

}
