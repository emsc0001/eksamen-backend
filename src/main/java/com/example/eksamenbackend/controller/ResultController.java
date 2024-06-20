import com.example.eksamenbackend.entity.Result;
import com.example.eksamenbackend.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/{id}")
    public Result getResultById(@PathVariable Long id) {
        return resultService.getResultById(id);
    }

    @PostMapping
    public ResponseEntity<Result> createResult(@RequestBody Result result) {
        Result savedResult = resultService.createResult(result);
        return ResponseEntity.ok(savedResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable Long id, @RequestBody Result result) {
        Result updatedResult = resultService.updateResult(id, result);
        return ResponseEntity.ok(updatedResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/discipline/{disciplineId}")
    public List<Result> getResultsByDiscipline(@PathVariable Long disciplineId) {
        return resultService.getResultsByDiscipline(disciplineId);
    }

    @GetMapping("/discipline/{disciplineId}/gender/{gender}")
    public List<Result> getResultsByDisciplineAndGender(@PathVariable Long disciplineId, @PathVariable String gender) {
        return resultService.getResultsByDisciplineAndGender(disciplineId, gender);
    }

    @GetMapping("/discipline/{disciplineId}/ageGroup")
    public List<Result> getResultsByDisciplineAndAgeGroup(@PathVariable Long disciplineId, @RequestParam int startAge, @RequestParam int endAge) {
        return resultService.getResultsByDisciplineAndAgeGroup(disciplineId, startAge, endAge);
    }
}
