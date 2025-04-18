package com.fighterapi.fighter.service;

import com.fighterapi.fighter.dto.FighterDTO;
import com.fighterapi.fighter.exceptions.FighterNotFoundException;
import com.fighterapi.fighter.exceptions.StrategyNotFoundException;
import com.fighterapi.fighter.mapper.FighterMapper;
import com.fighterapi.fighter.model.Fighter;
import com.fighterapi.fighter.model.MatchRequest;
import com.fighterapi.fighter.repository.FighterRepository;
import com.fighterapi.fighter.service.interfaces.MatchMakingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class MatchMakingService {

    @Autowired
    private FighterRepository fighterRepository;

    @Autowired
    private FighterMapper mapper;

    @Autowired
    private List<MatchMakingStrategy> strategies;

    @Transactional(readOnly = true)
    public List<FighterDTO> findMatchStrategy(int fighterId){
        var sourceFighter = fighterRepository.findById(fighterId)
                .orElseThrow(() -> new FighterNotFoundException("Fighter not found to matchmaking"));

        List<Fighter> candidates = fighterRepository.findAll();

        var fighterStrategy = validateFighterStrategy(sourceFighter);

        return candidates.stream()
                .filter(candidate -> candidate.getId() != sourceFighter.getId())
                .filter(candidate -> fighterStrategy.isMatch(sourceFighter, candidate))
                .map(candidate -> mapper.fighterToFighterDTO(candidate))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<FighterDTO> findMatch(MatchRequest matchRequest) {
        var candidates = fighterRepository.findAll();

        return candidates.stream()
                .filter(fighter -> fighter.getType().equals(matchRequest.getType()))
                .filter(fighter -> !fighter.getName().equals(matchRequest.getName()))
                .filter(fighter -> !fighter.getLastName().equals(matchRequest.getLastName()))
                .map(fighter -> mapper.fighterToFighterDTO(fighter))
                .toList();
    }

    private MatchMakingStrategy validateFighterStrategy(Fighter sourceFighter) {
        return strategies.stream()
                .filter(strategy -> strategy.supports(sourceFighter))
                .findFirst()
                .orElseThrow(() -> new StrategyNotFoundException("Matchmaking not found for this fighter"));
    }

}

