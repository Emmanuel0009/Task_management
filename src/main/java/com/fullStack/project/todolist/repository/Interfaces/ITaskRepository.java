package com.fullStack.project.todolist.repository.Interfaces;

import com.fullStack.project.todolist.models.Entity.Task;
import com.fullStack.project.todolist.utils.Enum.StatusEnum;
import com.fullStack.project.todolist.utils.Enum.UrgencyEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM task t JOIN t.urgencyLevels u WHERE u.urgencyLevel = :urgencyLevel")
    List<Task> findByUrgencyLevel(@Param("urgencyLevel") UrgencyEnum urgencyLevel);

    @Query("SELECT t FROM task t JOIN t.statusList s WHERE s.status = :status")
    List<Task> findByStatus(@Param("status") StatusEnum status);

    @Query("SELECT t FROM task t JOIN t.estimations e WHERE e.estimationTime = :estimationTime")
    List<Task> findByEstimation(@Param("estimationTime") Integer estimationTime);

    @Query("SELECT t FROM task t WHERE t.creationDate = :date")
    List<Task> findByCreationDate(@Param("date") Timestamp date);

    @Query("SELECT t FROM task t WHERE t.creationDate >= :date")
    List<Task> findByCreationDateAfter(@Param("date") Timestamp date);

    @Query("SELECT t FROM task t WHERE t.creationDate <= :date")
    List<Task> findByCreationDateBefore(@Param("date") Timestamp date);

    @Query("SELECT t FROM task t JOIN t.startDates s WHERE s.date = :date")
    List<Task> findByStartDate(@Param("date") Timestamp date);

    @Query("SELECT t FROM task t JOIN t.startDates s WHERE s.date >= :date")
    List<Task> findByStartDateAfter(@Param("date") Timestamp date);

    @Query("SELECT t FROM task t JOIN t.startDates s WHERE s.date <= :date")
    List<Task> findByStartDateBefore(@Param("date") Timestamp date);

    @Query("SELECT t FROM task t JOIN t.endDates d WHERE d.date  = :date")
    List<Task> findByEndDate(@Param("date") Timestamp date);

    @Query("SELECT t FROM task t JOIN t.endDates d WHERE d.date >= :date")
    List<Task> findByEndDateAfter(@Param("date") Timestamp date);

    @Query("SELECT t FROM task t JOIN t.endDates d WHERE d.date <= :date")
    List<Task> findByEndDateBefore(@Param("date") Timestamp date);

    @Query("SELECT t FROM task t WHERE t.objective = :objective")
    Optional<Task> findByObjective(@Param("objective") String objective);
}
