package com.fighterapi.fighter.repository;

import com.fighterapi.fighter.dto.FighterDTO;
import com.fighterapi.fighter.model.Fighter;
import com.fighterapi.fighter.model.enums.FighterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FighterRepository extends JpaRepository<Fighter,Integer> {
    List<Fighter> findByType(FighterType type);

}
