package com.fighterapi.fighter.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchRequest {

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Lastname is required")
    private String lastName;

    @NotNull(message = "Weight is required")
    @Min(value = 30, message = "Minimum weight allowed 30kg")
    @Max(value = 200, message = "Maximum weight allowed 200kg")
    private float weight;

    @NotNull(message = "FighterType is required")
    @Enumerated(EnumType.STRING)
    private FighterType type;

    @NotNull(message = "Record is required")
    private FighterRecord record;
}
