package com.fullStack.project.todolist.repository.Interfaces;

import com.fullStack.project.todolist.models.Entity.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDescriptionRepository extends JpaRepository<Description, Long> {}
