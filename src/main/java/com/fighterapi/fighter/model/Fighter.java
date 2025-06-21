package com.fighterapi.fighter.model;

import com.fighterapi.fighter.model.enums.Belt;
import com.fighterapi.fighter.model.enums.FighterType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Fighters")
public class Fighter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fighter")
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private LocalDate birthday;

    @NotNull
    @Min(value = 30)
    @Max(value = 200)
    private float weight;

    @NotNull
    @Min(value = 130)
    @Max(value = 220)
    private float height;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FighterType type;

    private int wins;

    private int loses;

    @Enumerated(EnumType.STRING)
    private Belt belt;
}
