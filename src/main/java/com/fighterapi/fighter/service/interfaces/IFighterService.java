package com.fighterapi.fighter.service.interfaces;

import com.fighterapi.fighter.dto.FighterDTO;
import com.fighterapi.fighter.model.FighterType;

import java.util.List;

public interface IFighterService {

    List<FighterDTO> listFighters();

    List<FighterDTO> listFightersByType(FighterType type);

    FighterDTO createFighter(FighterDTO fighter);

    FighterDTO updateFighter(int fighterId, FighterDTO fighter);

    void deleteFighter(int id);

}
