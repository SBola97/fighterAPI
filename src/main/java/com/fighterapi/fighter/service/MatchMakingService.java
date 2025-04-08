package com.fighterapi.fighter.service;

import com.fighterapi.fighter.dto.FighterDTO;
import com.fighterapi.fighter.exceptions.FighterNotFoundException;
import com.fighterapi.fighter.mapper.FighterMapper;
import com.fighterapi.fighter.model.Fighter;
import com.fighterapi.fighter.model.MatchRequest;
import com.fighterapi.fighter.repository.FighterRepository;
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

    @Transactional(readOnly = true)
    public List<FighterDTO> findMatch(int fighterId) {
        var sourceFighter = fighterRepository.findById(fighterId)
                .orElseThrow(() -> new FighterNotFoundException("Fighter not found to matchmaking"));
        List<Fighter> candidates = fighterRepository.findAll();

        return candidates
                .stream()
                .filter(fighter -> fighter.getId() != fighterId)
                .filter(fighter -> fighter.getType().equals(sourceFighter.getType()))
                .map(fighter -> mapper.fighterToFighterDTO(fighter))
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
}

