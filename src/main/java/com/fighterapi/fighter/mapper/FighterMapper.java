package com.fighterapi.fighter.mapper;

import com.fighterapi.fighter.dto.FighterDTO;
import com.fighterapi.fighter.model.Fighter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FighterMapper {
    FighterDTO fighterToFighterDTO(Fighter fighter);
    List<FighterDTO> fightersToFighterDTO(List<Fighter> fighter);

    Fighter fighterDTOToFighter(FighterDTO fighterDTO);
    List<Fighter> fightersDTOToFighter(List<FighterDTO> fighterDTO);
}
