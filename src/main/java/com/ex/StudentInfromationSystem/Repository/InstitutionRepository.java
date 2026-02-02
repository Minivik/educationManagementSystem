package com.ex.StudentInfromationSystem.Repository;

import com.ex.StudentInfromationSystem.Entities.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution,Long> {
}
