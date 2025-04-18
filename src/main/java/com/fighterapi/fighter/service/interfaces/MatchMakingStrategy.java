package com.fighterapi.fighter.service.interfaces;

import com.fighterapi.fighter.model.Fighter;

public interface MatchMakingStrategy {
    boolean isMatch(Fighter source, Fighter candidate);
    boolean supports(Fighter source);
}
