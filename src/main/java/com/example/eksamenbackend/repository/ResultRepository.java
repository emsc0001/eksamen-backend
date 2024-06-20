import com.example.eksamenbackend.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByDisciplineIdOrderByResultValueAsc(Long disciplineId);
    List<Result> findByDisciplineIdAndParticipantGenderOrderByResultValueAsc(Long disciplineId, String gender);
    List<Result> findByDisciplineIdAndParticipantAgeBetweenOrderByResultValueAsc(Long disciplineId, int startAge, int endAge);
}
