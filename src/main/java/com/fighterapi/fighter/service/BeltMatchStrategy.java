package com.fighterapi.fighter.service;

import com.fighterapi.fighter.model.Fighter;
import com.fighterapi.fighter.model.enums.FighterType;
import com.fighterapi.fighter.service.interfaces.MatchMakingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class BeltMatchStrategy implements MatchMakingStrategy {

    public static final int WEIGHT_DIFFERENCE = 5;
    public static final List<FighterType> VALID_FIGHTER_TYPES = List.of(
            FighterType.Judo,
            FighterType.Karate,
            FighterType.Taekwondo,
            FighterType.JiuJitsu);

    @Override
    public boolean isMatch(Fighter source, Fighter candidate) {
        boolean isSameTypeFighter = candidate.getType().name().equalsIgnoreCase(source.getType().name());
        if(hasNoBelt(source,candidate)) return false;
        boolean isSameBelt = candidate.getBelt().name().toLowerCase().contains(source.getBelt().name().toLowerCase());
        log.info("Matching details: \nSourceType {} SourceBelt {} \nCandidateType {} CandidateBelt {}",
                source.getType().name(),
                candidate.getType().name(),
                source.getBelt(),
                candidate.getBelt());
        return isSameTypeFighter && isSameBelt && isSameWeightCategory(source, candidate);
    }

    @Override
    public boolean supports(Fighter sourceFighter) {
        return VALID_FIGHTER_TYPES.contains(sourceFighter.getType());
    }

    private static boolean isSameWeightCategory(Fighter source, Fighter candidate) {
        return Math.abs(candidate.getWeight() - source.getWeight()) <= WEIGHT_DIFFERENCE;
    }

    private boolean hasNoBelt(Fighter source, Fighter candidate){
        return Optional.ofNullable(source.getBelt()).isEmpty() || Optional.ofNullable(candidate.getBelt()).isEmpty();
    }
}
