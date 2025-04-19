package com.fighterapi.fighter.controller;

import com.fighterapi.fighter.dto.FighterDTO;
import com.fighterapi.fighter.model.enums.FighterType;
import com.fighterapi.fighter.model.MatchRequest;
import com.fighterapi.fighter.service.FighterService;
import com.fighterapi.fighter.service.MatchMakingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fighterAPI/fighters")
@CrossOrigin(origins = "http://localhost:5173" )
public class FighterController {

    @Autowired
    private FighterService fighterService;

    @Autowired
    private MatchMakingService matchMakingService;

    @GetMapping("/list")
    private List<FighterDTO> listFighters(){
        return fighterService.listFighters();
    }

    @GetMapping("/list/{type}")
    public List<FighterDTO> listByType(@PathVariable (value = "type") FighterType type){
        return fighterService.listFightersByType(type);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    private FighterDTO createFighter(@Valid @RequestBody FighterDTO fighterDTO){
        return fighterService.createFighter(fighterDTO);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private FighterDTO updateFighter(@PathVariable (value = "id") int fighterId,
                                     @Valid @RequestBody FighterDTO fighterDTO){
        return fighterService.updateFighter(fighterId, fighterDTO);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private void deleteFighter(@PathVariable (value = "id") int fighterId){
        fighterService.deleteFighter(fighterId);
    }

    @GetMapping("/matchmaking/{id}")
    private List<FighterDTO> findMatch(@PathVariable (value = "id") int fighterId){
        return matchMakingService.findMatchStrategy(fighterId);
    }

    @GetMapping("/matchmaking")
    private List<FighterDTO> findMatch(@Valid @RequestBody MatchRequest matchRequest){
        return matchMakingService.findMatchByName(matchRequest);
    }
}
