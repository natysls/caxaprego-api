package com.caxaprego.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GuardaVolumeDto(@NotBlank String nmLocal, @NotNull Integer numArmarios) {

}
