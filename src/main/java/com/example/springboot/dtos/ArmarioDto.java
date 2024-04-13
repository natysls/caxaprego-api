package com.example.springboot.dtos;

import com.example.springboot.models.Cliente;
import com.example.springboot.models.GuardaVolume;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ArmarioDto(@NotNull GuardaVolume fkGuardaVolume,
                         Cliente fkCliente,
                         Date horarioCadastrado,
                         Integer tempoAluguel)  {


}
