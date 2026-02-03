package com.talenttrek.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talenttrek.education.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
