package com.example.springboot.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="tb_armario")
public class Armario extends RepresentationModel<Armario> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private GuardaVolume fkGuardaVolume;
    private Cliente fkCliente;
    private Date horarioCadastrado;
    private Integer tempoAluguel;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public GuardaVolume getFkGuardaVolume() {
        return fkGuardaVolume;
    }

    public void setFkGuardaVolume(GuardaVolume fkGuardaVolume) {
        this.fkGuardaVolume = fkGuardaVolume;
    }

    public Cliente getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Cliente fkCliente) {
        this.fkCliente = fkCliente;
    }

    public Date getHorarioCadastrado() {
        return horarioCadastrado;
    }

    public void setHorarioCadastrado(Date horarioCadastrado) {
        this.horarioCadastrado = horarioCadastrado;
    }

    public Integer getTempoAluguel() {
        return tempoAluguel;
    }

    public void setTempoAluguel(Integer tempoAluguel) {
        this.tempoAluguel = tempoAluguel;
    }
}
