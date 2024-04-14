package com.caxaprego.api.dtos;

import com.caxaprego.api.models.Cliente;
import com.caxaprego.api.models.GuardaVolume;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ArmarioDto(@NotNull GuardaVolume fkGuardaVolume,
                         Cliente fkCliente,
                         Date horarioCadastrado,
                         Integer tempoAluguel)  {


}
