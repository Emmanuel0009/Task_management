package com.fullStack.project.todolist.repository.Interfaces;

import com.fullStack.project.todolist.models.Entity.UrgencyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUrgencyLevelRepository extends JpaRepository<UrgencyLevel, Long> {}
