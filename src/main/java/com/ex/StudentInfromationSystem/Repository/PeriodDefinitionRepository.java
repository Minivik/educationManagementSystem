package com.ex.StudentInfromationSystem.Repository;

import com.ex.StudentInfromationSystem.Entities.PeriodDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodDefinitionRepository
        extends JpaRepository<PeriodDefinition, Long> {

    List<PeriodDefinition>
    findByPeriodStructureIdOrderByPeriodNumber(Long structureId);
}
