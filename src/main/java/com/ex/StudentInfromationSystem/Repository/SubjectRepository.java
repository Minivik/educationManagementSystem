package com.ex.StudentInfromationSystem.Repository;

import com.ex.StudentInfromationSystem.Entities.Student;
import com.ex.StudentInfromationSystem.Entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository  extends JpaRepository<Subject,Long> {
}
