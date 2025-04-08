package com.fighterapi.fighter.mapper;

import com.fighterapi.fighter.dto.FighterDTO;
import com.fighterapi.fighter.model.Fighter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FighterMapper {
    @Mapping(source = "fighter.wins", target = "record.wins")
    @Mapping(source = "fighter.loses", target = "record.loses")
    @Mapping(expression = "java(combineName(fighter.getName(), fighter.getLastName()))", target = "fullName")
    FighterDTO fighterToFighterDTO(Fighter fighter);
    List<FighterDTO> fightersToFighterDTO(List<Fighter> fighter);

    @Mapping(source = "record.loses", target = "loses")
    @Mapping(source = "record.wins", target = "wins")
    @Mapping(expression = "java(splitFullName(fighterDTO.getFullName())[0])", target = "name")
    @Mapping(expression = "java(splitFullName(fighterDTO.getFullName())[1])", target = "lastName")
    Fighter fighterDTOToFighter(FighterDTO fighterDTO);
    List<Fighter> fightersDTOToFighter(List<FighterDTO> fighterDTO);

    default String[] splitFullName(String fullName) {
        if (fullName == null || !fullName.contains(" ")) {
            return new String[] { fullName, "" };
        }
        String[] parts = fullName.split(" ", 2);
        return new String[] { parts[0], parts[1] };
    }

    default String combineName(String name, String lastName){
        return name + " " + lastName;
    }
}
