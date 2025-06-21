package com.fighterapi.fighter.utils;

import com.fighterapi.fighter.dto.FighterDTO;
import com.fighterapi.fighter.model.Fighter;
import com.fighterapi.fighter.model.enums.FighterType;

import java.time.LocalDate;

public class FighterTestUtils {

    public static FighterDTO buildFighterDTO(String fullName, FighterType type) {
        return FighterDTO.builder()
                .fullName(fullName)
                .height(150)
                .weight(90)
                .birthday(LocalDate.of(2000,10,10))
                .type(type)
                .build();
    }

    public static Fighter buildFighter(String name, String lastName, FighterType type){
        return Fighter.builder()
                .name(name)
                .lastName(lastName)
                .height(150)
                .weight(90)
                .birthday(LocalDate.of(2000,10,10))
                .type(type)
                .build();
    }
}