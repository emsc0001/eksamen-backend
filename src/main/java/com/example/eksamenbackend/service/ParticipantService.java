import com.example.eksamenbackend.entity.Participant;
import com.example.eksamenbackend.entity.Discipline;
import com.example.eksamenbackend.repository.ParticipantRepository;
import com.example.eksamenbackend.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    public Participant createParticipant(Participant participant) {
        Set<Discipline> disciplines = participant.getDisciplines().stream()
                .map(disciplineId -> disciplineRepository.findById(disciplineId).orElseThrow())
                .collect(Collectors.toSet());
        participant.setDisciplines(disciplines);
        return participantRepository.save(participant);
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    public Participant updateParticipant(Long id, Participant participantDetails) {
        Participant participant = participantRepository.findById(id).orElseThrow();
        participant.setName(participantDetails.getName());
        participant.setGender(participantDetails.getGender());
        participant.setAge(participantDetails.getAge());
        participant.setClub(participantDetails.getClub());

        // Update disciplines
        Set<Discipline> disciplines = participantDetails.getDisciplines().stream()
                .map(disciplineId -> disciplineRepository.findById(disciplineId).orElseThrow())
                .collect(Collectors.toSet());
        participant.setDisciplines(disciplines);

        return participantRepository.save(participant);
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }

    public List<Participant> filterParticipants(String gender, String ageGroup, String club, Long disciplineId) {
        List<Participant> participants = participantRepository.findAll();
        List<Participant> filteredParticipants = new ArrayList<>();
        for (Participant participant : participants) {
            boolean matches = true;
            if (gender != null && !gender.isEmpty() && !participant.getGender().equalsIgnoreCase(gender)) {
                matches = false;
            }
            if (ageGroup != null && !ageGroup.isEmpty() && !getAgeGroup(participant.getAge()).equalsIgnoreCase(ageGroup)) {
                matches = false;
            }
            if (club != null && !club.isEmpty() && !participant.getClub().equalsIgnoreCase(club)) {
                matches = false;
            }
            if (disciplineId != null && !participant.getDisciplines().stream().anyMatch(discipline -> discipline.getId().equals(disciplineId))) {
                matches = false;
            }
            if (matches) {
                filteredParticipants.add(participant);
            }
        }
        return filteredParticipants;
    }

    public List<Participant> searchParticipants(String query) {
        List<Participant> participants = participantRepository.findAll();
        List<Participant> searchResults = new ArrayList<>();
        for (Participant participant : participants) {
            if (participant.getName().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(participant);
            }
        }
        return searchResults;
    }

    private String getAgeGroup(int age) {
        if (age >= 6 && age <= 9) return "Children";
        if (age >= 10 && age <= 13) return "Youth";
        if (age >= 14 && age <= 22) return "Junior";
        if (age >= 23 && age <= 40) return "Adult";
        if (age >= 41) return "Senior";
        return "";
    }
}
