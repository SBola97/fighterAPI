package com.fighterapi.fighter.exceptions;

public class FighterNotFoundException extends RuntimeException {
    public FighterNotFoundException(String message) {
        super(message);
    }
}
