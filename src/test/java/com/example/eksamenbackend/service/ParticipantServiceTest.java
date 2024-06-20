package com.example.eksamenbackend.service;

import com.example.eksamenbackend.entity.Participant;
import com.example.eksamenbackend.repository.ParticipantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ParticipantServiceTest {

    @InjectMocks
    private ParticipantService participantService;

    @Mock
    private ParticipantRepository participantRepository;

    public ParticipantServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateParticipant() {
        Participant participant = new Participant();
        participant.setName("John Doe");
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);

        Participant created = participantService.createParticipant(participant);

        assertEquals("John Doe", created.getName());
        verify(participantRepository, times(1)).save(participant);
    }

    @Test
    public void testGetParticipantById() {
        Participant participant = new Participant();
        participant.setId(1L);
        participant.setName("John Doe");
        when(participantRepository.findById(1L)).thenReturn(Optional.of(participant));

        Optional<Participant> found = participantService.getParticipantById(1L);

        assertTrue(found.isPresent());
        assertEquals("John Doe", found.get().getName());
    }

    @Test
    public void testUpdateParticipant() {
        Participant participant = new Participant();
        participant.setId(1L);
        participant.setName("John Doe");
        when(participantRepository.findById(1L)).thenReturn(Optional.of(participant));
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);

        Participant updated = participantService.updateParticipant(1L, participant);

        assertEquals("John Doe", updated.getName());
        verify(participantRepository, times(1)).save(participant);
    }

    @Test
    public void testDeleteParticipant() {
        Participant participant = new Participant();
        participant.setId(1L);
        when(participantRepository.findById(1L)).thenReturn(Optional.of(participant));
        doNothing().when(participantRepository).deleteById(1L);

        participantService.deleteParticipant(1L);

        verify(participantRepository, times(1)).deleteById(1L);
    }
}
