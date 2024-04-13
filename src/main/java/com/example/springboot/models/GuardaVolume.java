package com.example.springboot.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="tb_guarda_volume")
public class GuardaVolume extends RepresentationModel<GuardaVolume> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID id;
    private String nmLocal;
    private Integer numArmarios;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNmLocal() {
        return nmLocal;
    }

    public void setNmLocal(String nmLocal) {
        this.nmLocal = nmLocal;
    }

    public Integer getNumArmarios() {
        return numArmarios;
    }

    public void setNumArmarios(Integer numArmarios) {
        this.numArmarios = numArmarios;
    }
}
