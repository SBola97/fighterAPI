package com.fighterapi.fighter.service;

import com.fighterapi.fighter.dto.FighterDTO;
import com.fighterapi.fighter.exceptions.FighterNotFoundException;
import com.fighterapi.fighter.mapper.FighterMapper;
import com.fighterapi.fighter.model.enums.Belt;
import com.fighterapi.fighter.model.enums.FighterType;
import com.fighterapi.fighter.repository.FighterRepository;
import com.fighterapi.fighter.service.interfaces.IFighterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FighterService implements IFighterService {

    public static final String NO_FIGHTERS_EXCEPTION_MESSAGE = "There was an error fetching the list of fighters ";

    private final FighterRepository fighterRepository;

    private final BeltValidator beltValidator;

    private final FighterMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<FighterDTO> listFighters() {
        try {
            return mapper.fightersToFighterDTO(fighterRepository.findAll());
        }
        catch (RuntimeException exception){
            throw new RuntimeException(NO_FIGHTERS_EXCEPTION_MESSAGE, exception);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<FighterDTO> listFightersByType(FighterType type) {
        try {
            return mapper.fightersToFighterDTO(fighterRepository.findByType(type));
        }
        catch (RuntimeException exception) {
            throw new RuntimeException(NO_FIGHTERS_EXCEPTION_MESSAGE + type, exception);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<FighterDTO> listFightersByBelt(Belt belt) {
        try {
            return mapper.fightersToFighterDTO(fighterRepository.findByBelt(belt));
        }
        catch (RuntimeException exception) {
            throw new RuntimeException(NO_FIGHTERS_EXCEPTION_MESSAGE + belt, exception);
        }
    }


    @Override
    @Transactional
    public FighterDTO createFighter(FighterDTO fighterDTO) {
        var optionalBelt = Optional.ofNullable(fighterDTO.getBelt());
        if(optionalBelt.isPresent()){
            beltValidator.validateOrThrow(fighterDTO.getType(), fighterDTO.getBelt());
        }
        var fighter = mapper.fighterDTOToFighter(fighterDTO);
        fighterRepository.save(fighter);
        log.info("Fighter {} created successfully", fighterDTO.getFullName());

        var resultDTO = mapper.fighterToFighterDTO(fighter);
        getFighterAge(resultDTO);
        return resultDTO;
    }

    @Override
    @Transactional
    public FighterDTO updateFighter(int fighterId, FighterDTO fighter) {
        var existFighter = fighterRepository.existsById(fighterId);
        if(existFighter) {
            fighter.setId(fighterId);
            return createFighter(fighter);
        }
        else {
            log.info("Fighter with id {} does not exist", fighterId);
            throw new FighterNotFoundException("Fighter not found");
        }
    }

    @Override
    @Transactional
    public void deleteFighter(int fighterId) {
        var fighter = fighterRepository.findById(fighterId)
                .orElseThrow(() -> new FighterNotFoundException("Fighter not found"));
        fighterRepository.delete(fighter);
        log.info("Fighter deleted successfully");
    }

    private int calculateAge(LocalDate birthday) {
        var currentDate = LocalDate.now();
        return Period.between(birthday,currentDate).getYears();
    }

    private void getFighterAge(FighterDTO resultDTO) {
        var fighterAge = calculateAge(resultDTO.getBirthday());
        resultDTO.setYears(fighterAge);
        resultDTO.setFullName(resultDTO.getFullName());
        log.info("The fighter {} is {} years old", resultDTO.getFullName(), resultDTO.getYears());
    }

}
