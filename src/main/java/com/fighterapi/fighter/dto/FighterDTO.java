package com.fighterapi.fighter.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fighterapi.fighter.model.enums.Belt;
import com.fighterapi.fighter.model.FighterRecord;
import com.fighterapi.fighter.model.enums.FighterType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FighterDTO {

    private int id;

    @NotNull(message = "FullName is required")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ]+(?: [A-Za-zÀ-ÿ]+)*$", message = "Full name must contain only letters and single spaces")
    private String fullName;

    @NotNull(message = "Birthday is required")
    private LocalDate birthday;

    @NotNull(message = "Weight is required")
    @Min(value = 30, message = "Minimum weight allowed 30kg")
    @Max(value = 200, message = "Maximum weight allowed 200kg")
    private float weight;

    @NotNull(message = "Height is required")
    @Min(value = 130, message = "Minimum height allowed 130cm")
    @Max(value = 220, message = "Maximum height allowed 220cm")
    private float height;

    @NotNull(message = "FighterType is required")
    @Enumerated(EnumType.STRING)
    private FighterType type;

    @JsonIgnore
    private int years;

    private FighterRecord record;

    @Enumerated(EnumType.STRING)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Belt belt;
}
