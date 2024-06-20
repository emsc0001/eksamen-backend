import com.example.eksamenbackend.entity.Result;
import com.example.eksamenbackend.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Result getResultById(Long id) {
        return resultRepository.findById(id).orElseThrow(() -> new RuntimeException("Result not found"));
    }

    public Result createResult(Result result) {
        return resultRepository.save(result);
    }

    public Result updateResult(Long id, Result result) {
        Result existingResult = resultRepository.findById(id).orElseThrow(() -> new RuntimeException("Result not found"));
        existingResult.setDate(result.getDate());
        existingResult.setResultValue(result.getResultValue());
        existingResult.setDiscipline(result.getDiscipline());
        existingResult.setParticipant(result.getParticipant());
        existingResult.setResultType(result.getResultType());
        return resultRepository.save(existingResult);
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }

    public List<Result> getResultsByDiscipline(Long disciplineId) {
        return resultRepository.findByDisciplineIdOrderByResultValueAsc(disciplineId);
    }

    public List<Result> getResultsByDisciplineAndGender(Long disciplineId, String gender) {
        return resultRepository.findByDisciplineIdAndParticipantGenderOrderByResultValueAsc(disciplineId, gender);
    }

    public List<Result> getResultsByDisciplineAndAgeGroup(Long disciplineId, int startAge, int endAge) {
        return resultRepository.findByDisciplineIdAndParticipantAgeBetweenOrderByResultValueAsc(disciplineId, startAge, endAge);
    }
}
