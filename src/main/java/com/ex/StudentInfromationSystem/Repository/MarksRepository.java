package com.ex.StudentInfromationSystem.Repository;

import com.ex.StudentInfromationSystem.Entities.MarksEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<MarksEntry,Long> {
    List<MarksEntry> findByAcademicRecordIdAndExamId(
            Long academicId, Long examId);
    List<MarksEntry> findByExamId(Long examId);
}
