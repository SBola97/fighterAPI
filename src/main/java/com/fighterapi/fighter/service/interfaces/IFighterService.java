package com.fighterapi.fighter.service.interfaces;

import com.fighterapi.fighter.dto.FighterDTO;
import com.fighterapi.fighter.model.enums.Belt;
import com.fighterapi.fighter.model.enums.FighterType;

import java.util.List;

public interface IFighterService {

    List<FighterDTO> listFighters();

    List<FighterDTO> listFightersByType(FighterType type);

    List<FighterDTO> listFightersByBelt(Belt belt);

    FighterDTO createFighter(FighterDTO fighter);

    FighterDTO updateFighter(int fighterId, FighterDTO fighter);

    void deleteFighter(int id);

}
