package com.ex.StudentInfromationSystem.Repository;

import com.ex.StudentInfromationSystem.Entities.PeriodStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeriodStructureRepository
        extends JpaRepository<PeriodStructure, Long> {

    Optional<PeriodStructure> findByScopeId(Long scopeId);
}
