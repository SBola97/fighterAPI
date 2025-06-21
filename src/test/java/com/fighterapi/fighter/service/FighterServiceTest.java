package com.fighterapi.fighter.service;

import com.fighterapi.fighter.mapper.FighterMapper;
import com.fighterapi.fighter.mapper.FighterMapperImpl;
import com.fighterapi.fighter.model.enums.FighterType;
import com.fighterapi.fighter.repository.FighterRepository;
import com.fighterapi.fighter.utils.FighterTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FighterServiceTest {

    @Mock
    private FighterRepository fighterRepository;

    @Mock
    private FighterMapper mapper;

    @InjectMocks
    private FighterService underTest;

    @Test
    void shouldCreateFighter() {
        var fighterDTO = FighterTestUtils.buildFighterDTO("Carlos Benítez", FighterType.JiuJitsu);
        var fighter = FighterTestUtils.buildFighter("Carlos", "Benítez", FighterType.JiuJitsu);

        when(mapper.fighterDTOToFighter(fighterDTO)).thenReturn(fighter);
        when(mapper.fighterToFighterDTO(fighter)).thenReturn(fighterDTO);
        when(fighterRepository.save(fighter)).thenReturn(fighter);

        var result = underTest.createFighter(fighterDTO);

        assertEquals(fighterDTO, result);
        verify(fighterRepository).save(fighter);
    }

}
