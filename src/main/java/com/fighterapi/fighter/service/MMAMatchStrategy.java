package com.fighterapi.fighter.service;

import com.fighterapi.fighter.model.Fighter;
import com.fighterapi.fighter.model.enums.FighterType;
import com.fighterapi.fighter.service.interfaces.MatchMakingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MMAMatchStrategy implements MatchMakingStrategy {

    private static final int MAX_WEIGHT_DIFFERENCE = 1;
    private static final int MAX_FIGHTS_DIFFERENCE = 12;
    public static final double MAX_WIN_RATE_DIFFERENCE = 0.35;

    @Override
    public boolean isMatch(Fighter source, Fighter candidate) {
        boolean isSameTypeFighter = candidate.getType().name().equalsIgnoreCase(source.getType().name());
        log.info("Type of fighters: SourceType {} CandidateType {}",
                source.getType().name(),
                candidate.getType().name());
        return isSameTypeFighter && isSameWeightCategory(source, candidate) && isCompetitive(source,candidate);
    }

    @Override
    public boolean supports(Fighter sourceFighter) {
        return sourceFighter.getType().name().equalsIgnoreCase(FighterType.MMA.name());
    }

    private static boolean isSameWeightCategory(Fighter source, Fighter candidate) {
        var weightDiff = Math.abs(candidate.getWeight() - source.getWeight());
        log.info("Weight difference between fighters {}", weightDiff);
        return weightDiff <= MAX_WEIGHT_DIFFERENCE;
    }

    private static boolean isCompetitive(Fighter source, Fighter candidate){
        var sourceFights = source.getWins() + source.getLoses();
        var candidateFights = candidate.getWins() + candidate.getLoses();

        var hasSimilarRecord = Math.abs(sourceFights -  candidateFights) <= MAX_FIGHTS_DIFFERENCE;

        var winRateDiff = Math.abs(getWinRate(source) - getWinRate(candidate));
        boolean isSimilarWinRate = winRateDiff <= MAX_WIN_RATE_DIFFERENCE;

        return hasSimilarRecord && isSimilarWinRate;
    }

    private static double getWinRate(Fighter fighter){
        var totalFights = fighter.getWins() + fighter.getLoses();
        var winRate = totalFights == 0
                ? 0
                : (double) fighter.getWins() / totalFights;
        log.info("Win rate for fighter {} is {}", fighter.getName() + " " + fighter.getLastName(), winRate);
        return winRate;
    }
}
