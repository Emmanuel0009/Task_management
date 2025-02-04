package com.fullStack.project.todolist.repository.Interfaces;

import com.fullStack.project.todolist.models.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepository extends JpaRepository<Status, Long> {}
