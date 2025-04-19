package com.fighterapi.fighter.service;

import com.fighterapi.fighter.exceptions.InvalidBeltException;
import com.fighterapi.fighter.model.enums.Belt;
import com.fighterapi.fighter.model.enums.FighterType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class BeltValidator {
    private static final Map<FighterType, Set<Belt>> allowedBelts = Map.of(
            FighterType.Judo, Set.of(Belt.White, Belt.Yellow, Belt.Orange, Belt.Green, Belt.Blue, Belt.Brown, Belt.Black),
            FighterType.Karate, Set.of(Belt.White, Belt.Yellow, Belt.Orange, Belt.Green, Belt.Blue, Belt.Brown, Belt.Black),
            FighterType.JiuJitsu, Set.of(Belt.White, Belt.Blue, Belt.Purple, Belt.Brown, Belt.Black),
            FighterType.Taekwondo, Set.of(Belt.White, Belt.WhiteYellow, Belt.Yellow, Belt.YellowGreen, Belt.Green,
                    Belt.GreenBlue, Belt.Blue, Belt.BlueRed, Belt.Red, Belt.RedBlack, Belt.Black)
    );

    private boolean isBeltValid(FighterType fighterType, Belt belt){
        try {
            return allowedBelts.get(fighterType).contains(belt);
        } catch (RuntimeException exception) {
            throw new InvalidBeltException("There is no belt for " + fighterType);
        }
    }

    public void validateOrThrow(FighterType fighterType, Belt belt){
        if(!isBeltValid(fighterType, belt)){
            throw new InvalidBeltException("Belt " + belt + " is not valid for "+ fighterType);
        }
    }
}
