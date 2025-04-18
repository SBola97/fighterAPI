package com.fighterapi.fighter.service.interfaces;

import com.fighterapi.fighter.model.Fighter;
import com.fighterapi.fighter.model.enums.FighterType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeltMatchStrategy implements MatchMakingStrategy {

    public static final int WEIGHT_DIFFERENCE = 5;
    public static final List<FighterType> VALID_FIGHTER_TYPES = List.of(
            FighterType.Judo,
            FighterType.Karate,
            FighterType.Taekwondo);

    @Override
    public boolean isMatch(Fighter source, Fighter candidate) {
        return true;
    }

    @Override
    public boolean supports(Fighter sourceFighter) {
        return VALID_FIGHTER_TYPES.contains(sourceFighter.getType());
    }

    private static boolean isSameWeightCategory(Fighter source, Fighter candidate) {
        return Math.abs(candidate.getWeight() - source.getWeight()) <= WEIGHT_DIFFERENCE;
    }
}
