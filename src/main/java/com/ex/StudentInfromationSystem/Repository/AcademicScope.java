package com.ex.StudentInfromationSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicScope extends JpaRepository<AcademicScope,Long> {
}
