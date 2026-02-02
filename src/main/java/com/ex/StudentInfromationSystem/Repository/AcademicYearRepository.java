package com.ex.StudentInfromationSystem.Repository;

import com.ex.StudentInfromationSystem.Entities.AcademicYear;

import java.util.Optional;


public interface AcademicYearRepository {
    Optional<AcademicYear> findById(Long academicYearId);
}
