package com.fullStack.project.todolist.repository.Interfaces;

import com.fullStack.project.todolist.models.Entity.ExampleFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExampleFileRepository extends JpaRepository<ExampleFile, Long> {}
