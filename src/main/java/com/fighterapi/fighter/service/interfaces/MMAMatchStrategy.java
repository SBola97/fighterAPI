package com.fighterapi.fighter.service.interfaces;

import com.fighterapi.fighter.model.Fighter;
import com.fighterapi.fighter.model.enums.FighterType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MMAMatchStrategy implements MatchMakingStrategy {

    public static final int WEIGHT_DIFFERENCE_ALLOWED = 5;

    @Override
    public boolean isMatch(Fighter source, Fighter candidate) {
        boolean sameTypeFighter = candidate.getType().name().equalsIgnoreCase(source.getType().name());
        log.info("Type of fighters: SourceType {} CandidateType {}",
                source.getType().name(),
                candidate.getType().name());
        return sameTypeFighter
                && isSameWeightCategory(source, candidate);
    }

    @Override
    public boolean supports(Fighter sourceFighter) {
        return sourceFighter.getType().name().equalsIgnoreCase(FighterType.MMA.name());
    }

    private static boolean isSameWeightCategory(Fighter source, Fighter candidate) {
        var weightDiff = Math.abs(candidate.getWeight() - source.getWeight());
        log.info("Weight difference between fighters {}", weightDiff);
        return weightDiff <= WEIGHT_DIFFERENCE_ALLOWED;
    }
}
