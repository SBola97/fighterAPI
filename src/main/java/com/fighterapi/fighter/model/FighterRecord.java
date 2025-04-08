package com.fighterapi.fighter.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FighterRecord {

    @Min(0)
    @Max(value = 300, message = "Maximum wins allowed 300")
    private int wins;

    @Min(0)
    @Max(value = 300, message = "Maximum loses allowed 300")
    private int loses;
}
