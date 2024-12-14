package com.fullStack.project.todolist.repository;

import com.fullStack.project.todolist.models.Entity.Task;
import com.fullStack.project.todolist.repository.Interfaces.ITaskRepository;
import com.fullStack.project.todolist.utils.Enum.StatusEnum;
import com.fullStack.project.todolist.utils.Enum.UrgencyEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private ITaskRepository taskRepository;

    private Task task1;
    private Task task2;
    private Task task3;
    private Task task4;
    private Task task5;

    @BeforeEach
    void setUp () {

        Task task1 = new Task();
        task1.setId(1L);
        task1.setObjective("Complete project report");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setObjective("Prepare client presentation");

        Task task3 = new Task();
        task3.setId(3L);
        task3.setObjective("Fix bug in payment system");

        Task task4 = new Task();
        task4.setId(4L);
        task4.setObjective("Review code for new feature");

        Task task5 = new Task();
        task5.setId(5L);
        task5.setObjective("Conduct user testing");
    }

    @Test
    void shouldReturnAllTasks() {
        //Act
        List<Task> returnedTaskList = taskRepository.findAll();
        //Assert
        assertThat(returnedTaskList).hasSize(5);
        assertThat(returnedTaskList).containsExactly(task1, task2, task3, task4, task5);
    }

    @Test
    void shouldReturnTaskWithId1() {
        //Act
        Optional<Task> returnedTask = taskRepository.findById(1L);
        //Assert
        assertThat(returnedTask).isNotEmpty();
        assertThat(returnedTask.get().getId()).isEqualTo(task1.getId());
        assertThat(returnedTask.get().getObjective()).isEqualTo(task1.getObjective());
        assertThat(returnedTask.get().getComments()).hasSize(task1.getComments().size()).containsAll(task1.getComments());
        assertThat(returnedTask.get().getDescriptions()).hasSize(task1.getDescriptions().size()).containsAll(task1.getDescriptions());
        assertThat(returnedTask.get().getEstimations()).hasSize(task1.getEstimations().size()).containsAll(task1.getEstimations());
        assertThat(returnedTask.get().getExamples()).hasSize(task1.getExamples().size()).containsAll(task1.getExamples());
        assertThat(returnedTask.get().getStatusList()).hasSize(task1.getStatusList().size()).containsAll(task1.getStatusList());
        assertThat(returnedTask.get().getUrgencyLevels()).hasSize(task1.getUrgencyLevels().size()).containsAll(task1.getUrgencyLevels());
        assertThat(returnedTask.get().getClosingDates()).hasSize(task1.getClosingDates().size()).containsAll(task1.getClosingDates());
        assertThat(returnedTask.get().getEndDates()).hasSize(task1.getEndDates().size()).containsAll(task1.getEndDates());
        assertThat(returnedTask.get().getReopeningDates()).hasSize(task1.getReopeningDates().size()).containsAll(task1.getReopeningDates());
    }

    @Test
    void shouldReturnTaskWithObjective() {
        //Act
        Optional<Task> returnedTask = taskRepository.findByObjective("Complete project report");
        //Assert
        assertThat(returnedTask).isNotEmpty();
        assertThat(returnedTask.get().getId()).isEqualTo(task1.getId());
        assertThat(returnedTask.get().getObjective()).isEqualTo(task1.getObjective());
        assertThat(returnedTask.get().getComments()).hasSize(task1.getComments().size()).containsAll(task1.getComments());
        assertThat(returnedTask.get().getDescriptions()).hasSize(task1.getDescriptions().size()).containsAll(task1.getDescriptions());
        assertThat(returnedTask.get().getEstimations()).hasSize(task1.getEstimations().size()).containsAll(task1.getEstimations());
        assertThat(returnedTask.get().getExamples()).hasSize(task1.getExamples().size()).containsAll(task1.getExamples());
        assertThat(returnedTask.get().getStatusList()).hasSize(task1.getStatusList().size()).containsAll(task1.getStatusList());
        assertThat(returnedTask.get().getUrgencyLevels()).hasSize(task1.getUrgencyLevels().size()).containsAll(task1.getUrgencyLevels());
        assertThat(returnedTask.get().getClosingDates()).hasSize(task1.getClosingDates().size()).containsAll(task1.getClosingDates());
        assertThat(returnedTask.get().getEndDates()).hasSize(task1.getEndDates().size()).containsAll(task1.getEndDates());
        assertThat(returnedTask.get().getReopeningDates()).hasSize(task1.getReopeningDates().size()).containsAll(task1.getReopeningDates());
    }

    @Test
    void shouldReturnTaskWithUrgencyLevel() {
        //Act
        List<Task> returnedTask = taskRepository.findByUrgencyLevel(UrgencyEnum.VERY_LOW);
        //Assert
        assertThat(returnedTask).isNotEmpty().hasSize(2);

        assertThat(returnedTask.get(0).getId()).isEqualTo(task1.getId());
        assertThat(returnedTask.get(0).getObjective()).isEqualTo(task1.getObjective());
        assertThat(returnedTask.get(0).getComments()).hasSize(task1.getComments().size()).containsAll(task1.getComments());
        assertThat(returnedTask.get(0).getDescriptions()).hasSize(task1.getDescriptions().size()).containsAll(task1.getDescriptions());
        assertThat(returnedTask.get(0).getEstimations()).hasSize(task1.getEstimations().size()).containsAll(task1.getEstimations());
        assertThat(returnedTask.get(0).getExamples()).hasSize(task1.getExamples().size()).containsAll(task1.getExamples());
        assertThat(returnedTask.get(0).getStatusList()).hasSize(task1.getStatusList().size()).containsAll(task1.getStatusList());
        assertThat(returnedTask.get(0).getUrgencyLevels()).hasSize(task1.getUrgencyLevels().size()).containsAll(task1.getUrgencyLevels());
        assertThat(returnedTask.get(0).getClosingDates()).hasSize(task1.getClosingDates().size()).containsAll(task1.getClosingDates());
        assertThat(returnedTask.get(0).getEndDates()).hasSize(task1.getEndDates().size()).containsAll(task1.getEndDates());
        assertThat(returnedTask.get(0).getReopeningDates()).hasSize(task1.getReopeningDates().size()).containsAll(task1.getReopeningDates());

        assertThat(returnedTask.get(1).getId()).isEqualTo(task2.getId());
        assertThat(returnedTask.get(1).getObjective()).isEqualTo(task2.getObjective());
        assertThat(returnedTask.get(1).getComments()).hasSize(task2.getComments().size()).containsAll(task2.getComments());
        assertThat(returnedTask.get(1).getDescriptions()).hasSize(task2.getDescriptions().size()).containsAll(task2.getDescriptions());
        assertThat(returnedTask.get(1).getEstimations()).hasSize(task2.getEstimations().size()).containsAll(task2.getEstimations());
        assertThat(returnedTask.get(1).getExamples()).hasSize(task2.getExamples().size()).containsAll(task2.getExamples());
        assertThat(returnedTask.get(1).getStatusList()).hasSize(task2.getStatusList().size()).containsAll(task2.getStatusList());
        assertThat(returnedTask.get(1).getUrgencyLevels()).hasSize(task2.getUrgencyLevels().size()).containsAll(task2.getUrgencyLevels());
        assertThat(returnedTask.get(1).getClosingDates()).hasSize(task1.getClosingDates().size()).containsAll(task2.getClosingDates());
        assertThat(returnedTask.get(1).getEndDates()).hasSize(task2.getEndDates().size()).containsAll(task2.getEndDates());
        assertThat(returnedTask.get(1).getReopeningDates()).hasSize(task2.getReopeningDates().size()).containsAll(task2.getReopeningDates());
    }

    @Test
    void shouldReturnTaskWithStatus() {
        //Act
        List<Task> returnedTask = taskRepository.findByStatus(StatusEnum.TODO);
        //Assert
        assertThat(returnedTask).isNotEmpty().hasSize(2);

        assertThat(returnedTask.get(0).getId()).isEqualTo(task1.getId());
        assertThat(returnedTask.get(0).getObjective()).isEqualTo(task1.getObjective());
        assertThat(returnedTask.get(0).getComments()).hasSize(task1.getComments().size()).containsAll(task1.getComments());
        assertThat(returnedTask.get(0).getDescriptions()).hasSize(task1.getDescriptions().size()).containsAll(task1.getDescriptions());
        assertThat(returnedTask.get(0).getEstimations()).hasSize(task1.getEstimations().size()).containsAll(task1.getEstimations());
        assertThat(returnedTask.get(0).getExamples()).hasSize(task1.getExamples().size()).containsAll(task1.getExamples());
        assertThat(returnedTask.get(0).getStatusList()).hasSize(task1.getStatusList().size()).containsAll(task1.getStatusList());
        assertThat(returnedTask.get(0).getUrgencyLevels()).hasSize(task1.getUrgencyLevels().size()).containsAll(task1.getUrgencyLevels());
        assertThat(returnedTask.get(0).getClosingDates()).hasSize(task1.getClosingDates().size()).containsAll(task1.getClosingDates());
        assertThat(returnedTask.get(0).getEndDates()).hasSize(task1.getEndDates().size()).containsAll(task1.getEndDates());
        assertThat(returnedTask.get(0).getReopeningDates()).hasSize(task1.getReopeningDates().size()).containsAll(task1.getReopeningDates());

        assertThat(returnedTask.get(1).getId()).isEqualTo(task2.getId());
        assertThat(returnedTask.get(1).getObjective()).isEqualTo(task2.getObjective());
        assertThat(returnedTask.get(1).getComments()).hasSize(task2.getComments().size()).containsAll(task2.getComments());
        assertThat(returnedTask.get(1).getDescriptions()).hasSize(task2.getDescriptions().size()).containsAll(task2.getDescriptions());
        assertThat(returnedTask.get(1).getEstimations()).hasSize(task2.getEstimations().size()).containsAll(task2.getEstimations());
        assertThat(returnedTask.get(1).getExamples()).hasSize(task2.getExamples().size()).containsAll(task2.getExamples());
        assertThat(returnedTask.get(1).getStatusList()).hasSize(task2.getStatusList().size()).containsAll(task2.getStatusList());
        assertThat(returnedTask.get(1).getUrgencyLevels()).hasSize(task2.getUrgencyLevels().size()).containsAll(task2.getUrgencyLevels());
        assertThat(returnedTask.get(1).getClosingDates()).hasSize(task1.getClosingDates().size()).containsAll(task2.getClosingDates());
        assertThat(returnedTask.get(1).getEndDates()).hasSize(task2.getEndDates().size()).containsAll(task2.getEndDates());
        assertThat(returnedTask.get(1).getReopeningDates()).hasSize(task2.getReopeningDates().size()).containsAll(task2.getReopeningDates());
    }

    @Test
    void shouldReturnTaskWithEstimation() {
        //Act
        List<Task> returnedTask = taskRepository.findByEstimation(3600);
        //Assert
        assertThat(returnedTask).isNotEmpty().hasSize(2);

        assertThat(returnedTask.get(0).getId()).isEqualTo(task1.getId());
        assertThat(returnedTask.get(0).getObjective()).isEqualTo(task1.getObjective());
        assertThat(returnedTask.get(0).getComments()).hasSize(task1.getComments().size()).containsAll(task1.getComments());
        assertThat(returnedTask.get(0).getDescriptions()).hasSize(task1.getDescriptions().size()).containsAll(task1.getDescriptions());
        assertThat(returnedTask.get(0).getEstimations()).hasSize(task1.getEstimations().size()).containsAll(task1.getEstimations());
        assertThat(returnedTask.get(0).getExamples()).hasSize(task1.getExamples().size()).containsAll(task1.getExamples());
        assertThat(returnedTask.get(0).getStatusList()).hasSize(task1.getStatusList().size()).containsAll(task1.getStatusList());
        assertThat(returnedTask.get(0).getUrgencyLevels()).hasSize(task1.getUrgencyLevels().size()).containsAll(task1.getUrgencyLevels());
        assertThat(returnedTask.get(0).getClosingDates()).hasSize(task1.getClosingDates().size()).containsAll(task1.getClosingDates());
        assertThat(returnedTask.get(0).getEndDates()).hasSize(task1.getEndDates().size()).containsAll(task1.getEndDates());
        assertThat(returnedTask.get(0).getReopeningDates()).hasSize(task1.getReopeningDates().size()).containsAll(task1.getReopeningDates());

        assertThat(returnedTask.get(1).getId()).isEqualTo(task2.getId());
        assertThat(returnedTask.get(1).getObjective()).isEqualTo(task2.getObjective());
        assertThat(returnedTask.get(1).getComments()).hasSize(task2.getComments().size()).containsAll(task2.getComments());
        assertThat(returnedTask.get(1).getDescriptions()).hasSize(task2.getDescriptions().size()).containsAll(task2.getDescriptions());
        assertThat(returnedTask.get(1).getEstimations()).hasSize(task2.getEstimations().size()).containsAll(task2.getEstimations());
        assertThat(returnedTask.get(1).getExamples()).hasSize(task2.getExamples().size()).containsAll(task2.getExamples());
        assertThat(returnedTask.get(1).getStatusList()).hasSize(task2.getStatusList().size()).containsAll(task2.getStatusList());
        assertThat(returnedTask.get(1).getUrgencyLevels()).hasSize(task2.getUrgencyLevels().size()).containsAll(task2.getUrgencyLevels());
        assertThat(returnedTask.get(1).getClosingDates()).hasSize(task1.getClosingDates().size()).containsAll(task2.getClosingDates());
        assertThat(returnedTask.get(1).getEndDates()).hasSize(task2.getEndDates().size()).containsAll(task2.getEndDates());
        assertThat(returnedTask.get(1).getReopeningDates()).hasSize(task2.getReopeningDates().size()).containsAll(task2.getReopeningDates());
    }

    @Test
    void shouldReturnTaskWithCreationDate() {
        //Act
        List<Task> returnedTask = taskRepository.findByCreationDate(Timestamp.valueOf(LocalDateTime.of(2024, 12, 1, 8, 0, 0, 0)));

        //Assert
        assertThat(returnedTask).isNotEmpty().hasSize(1);

        assertThat(returnedTask.get(0).getId()).isEqualTo(task1.getId());
        assertThat(returnedTask.get(0).getObjective()).isEqualTo(task1.getObjective());
        assertThat(returnedTask.get(0).getComments()).hasSize(task1.getComments().size()).containsAll(task1.getComments());
        assertThat(returnedTask.get(0).getDescriptions()).hasSize(task1.getDescriptions().size()).containsAll(task1.getDescriptions());
        assertThat(returnedTask.get(0).getEstimations()).hasSize(task1.getEstimations().size()).containsAll(task1.getEstimations());
        assertThat(returnedTask.get(0).getExamples()).hasSize(task1.getExamples().size()).containsAll(task1.getExamples());
        assertThat(returnedTask.get(0).getStatusList()).hasSize(task1.getStatusList().size()).containsAll(task1.getStatusList());
        assertThat(returnedTask.get(0).getUrgencyLevels()).hasSize(task1.getUrgencyLevels().size()).containsAll(task1.getUrgencyLevels());
        assertThat(returnedTask.get(0).getClosingDates()).hasSize(task1.getClosingDates().size()).containsAll(task1.getClosingDates());
        assertThat(returnedTask.get(0).getEndDates()).hasSize(task1.getEndDates().size()).containsAll(task1.getEndDates());
        assertThat(returnedTask.get(0).getReopeningDates()).hasSize(task1.getReopeningDates().size()).containsAll(task1.getReopeningDates());
    }

    @Test
    void shouldReturnTaskWithCreationDateBeforeBefore() {
        //Act
        List<Task> returnedTask = taskRepository.findByCreationDate(Timestamp.valueOf(LocalDateTime.of(2024, 12, 1, 8, 36, 0, 0)));

        //Assert
        assertThat(returnedTask).isNotEmpty().hasSize(3);

        assertThat(returnedTask.get(0).getId()).isEqualTo(task1.getId());
        assertThat(returnedTask.get(0).getObjective()).isEqualTo(task1.getObjective());
        assertThat(returnedTask.get(0).getComments()).hasSize(task1.getComments().size()).containsAll(task1.getComments());
        assertThat(returnedTask.get(0).getDescriptions()).hasSize(task1.getDescriptions().size()).containsAll(task1.getDescriptions());
        assertThat(returnedTask.get(0).getEstimations()).hasSize(task1.getEstimations().size()).containsAll(task1.getEstimations());
        assertThat(returnedTask.get(0).getExamples()).hasSize(task1.getExamples().size()).containsAll(task1.getExamples());
        assertThat(returnedTask.get(0).getStatusList()).hasSize(task1.getStatusList().size()).containsAll(task1.getStatusList());
        assertThat(returnedTask.get(0).getUrgencyLevels()).hasSize(task1.getUrgencyLevels().size()).containsAll(task1.getUrgencyLevels());
        assertThat(returnedTask.get(0).getClosingDates()).hasSize(task1.getClosingDates().size()).containsAll(task1.getClosingDates());
        assertThat(returnedTask.get(0).getEndDates()).hasSize(task1.getEndDates().size()).containsAll(task1.getEndDates());
        assertThat(returnedTask.get(0).getReopeningDates()).hasSize(task1.getReopeningDates().size()).containsAll(task1.getReopeningDates());

        assertThat(returnedTask.get(1).getId()).isEqualTo(task3.getId());
        assertThat(returnedTask.get(1).getObjective()).isEqualTo(task3.getObjective());
        assertThat(returnedTask.get(1).getComments()).hasSize(task3.getComments().size()).containsAll(task3.getComments());
        assertThat(returnedTask.get(1).getDescriptions()).hasSize(task3.getDescriptions().size()).containsAll(task3.getDescriptions());
        assertThat(returnedTask.get(1).getEstimations()).hasSize(task3.getEstimations().size()).containsAll(task3.getEstimations());
        assertThat(returnedTask.get(1).getExamples()).hasSize(task3.getExamples().size()).containsAll(task3.getExamples());
        assertThat(returnedTask.get(1).getStatusList()).hasSize(task3.getStatusList().size()).containsAll(task3.getStatusList());
        assertThat(returnedTask.get(1).getUrgencyLevels()).hasSize(task3.getUrgencyLevels().size()).containsAll(task3.getUrgencyLevels());
        assertThat(returnedTask.get(1).getClosingDates()).hasSize(task3.getClosingDates().size()).containsAll(task3.getClosingDates());
        assertThat(returnedTask.get(1).getEndDates()).hasSize(task3.getEndDates().size()).containsAll(task3.getEndDates());
        assertThat(returnedTask.get(1).getReopeningDates()).hasSize(task3.getReopeningDates().size()).containsAll(task3.getReopeningDates());

        assertThat(returnedTask.get(2).getId()).isEqualTo(task5.getId());
        assertThat(returnedTask.get(2).getObjective()).isEqualTo(task5.getObjective());
        assertThat(returnedTask.get(2).getComments()).hasSize(task5.getComments().size()).containsAll(task5.getComments());
        assertThat(returnedTask.get(2).getDescriptions()).hasSize(task5.getDescriptions().size()).containsAll(task5.getDescriptions());
        assertThat(returnedTask.get(2).getEstimations()).hasSize(task5.getEstimations().size()).containsAll(task5.getEstimations());
        assertThat(returnedTask.get(2).getExamples()).hasSize(task5.getExamples().size()).containsAll(task5.getExamples());
        assertThat(returnedTask.get(2).getStatusList()).hasSize(task5.getStatusList().size()).containsAll(task5.getStatusList());
        assertThat(returnedTask.get(2).getUrgencyLevels()).hasSize(task5.getUrgencyLevels().size()).containsAll(task5.getUrgencyLevels());
        assertThat(returnedTask.get(2).getClosingDates()).hasSize(task5.getClosingDates().size()).containsAll(task5.getClosingDates());
        assertThat(returnedTask.get(2).getEndDates()).hasSize(task5.getEndDates().size()).containsAll(task5.getEndDates());
        assertThat(returnedTask.get(2).getReopeningDates()).hasSize(task5.getReopeningDates().size()).containsAll(task5.getReopeningDates());
    }

    @Test
    void shouldReturnTaskWithCreationDateAfter() {
        //Act
        List<Task> returnedTask = taskRepository.findByCreationDateAfter(Timestamp.valueOf(LocalDateTime.of(2024, 12, 2, 8, 0, 0, 0)));
        //Assert
        assertThat(returnedTask).isNotEmpty().hasSize(2);

        assertThat(returnedTask.get(0).getId()).isEqualTo(task2.getId());
        assertThat(returnedTask.get(0).getObjective()).isEqualTo(task2.getObjective());
        assertThat(returnedTask.get(0).getComments()).hasSize(task2.getComments().size()).containsAll(task2.getComments());
        assertThat(returnedTask.get(0).getDescriptions()).hasSize(task2.getDescriptions().size()).containsAll(task2.getDescriptions());
        assertThat(returnedTask.get(0).getEstimations()).hasSize(task2.getEstimations().size()).containsAll(task2.getEstimations());
        assertThat(returnedTask.get(0).getExamples()).hasSize(task2.getExamples().size()).containsAll(task2.getExamples());
        assertThat(returnedTask.get(0).getStatusList()).hasSize(task2.getStatusList().size()).containsAll(task2.getStatusList());
        assertThat(returnedTask.get(0).getUrgencyLevels()).hasSize(task2.getUrgencyLevels().size()).containsAll(task2.getUrgencyLevels());
        assertThat(returnedTask.get(0).getClosingDates()).hasSize(task2.getClosingDates().size()).containsAll(task2.getClosingDates());
        assertThat(returnedTask.get(0).getEndDates()).hasSize(task2.getEndDates().size()).containsAll(task2.getEndDates());
        assertThat(returnedTask.get(0).getReopeningDates()).hasSize(task2.getReopeningDates().size()).containsAll(task2.getReopeningDates());

        assertThat(returnedTask.get(1).getId()).isEqualTo(task4.getId());
        assertThat(returnedTask.get(1).getObjective()).isEqualTo(task4.getObjective());
        assertThat(returnedTask.get(1).getComments()).hasSize(task4.getComments().size()).containsAll(task4.getComments());
        assertThat(returnedTask.get(1).getDescriptions()).hasSize(task4.getDescriptions().size()).containsAll(task4.getDescriptions());
        assertThat(returnedTask.get(1).getEstimations()).hasSize(task4.getEstimations().size()).containsAll(task4.getEstimations());
        assertThat(returnedTask.get(1).getExamples()).hasSize(task4.getExamples().size()).containsAll(task4.getExamples());
        assertThat(returnedTask.get(1).getStatusList()).hasSize(task4.getStatusList().size()).containsAll(task4.getStatusList());
        assertThat(returnedTask.get(1).getUrgencyLevels()).hasSize(task4.getUrgencyLevels().size()).containsAll(task4.getUrgencyLevels());
        assertThat(returnedTask.get(1).getClosingDates()).hasSize(task4.getClosingDates().size()).containsAll(task4.getClosingDates());
        assertThat(returnedTask.get(1).getEndDates()).hasSize(task4.getEndDates().size()).containsAll(task4.getEndDates());
        assertThat(returnedTask.get(1).getReopeningDates()).hasSize(task4.getReopeningDates().size()).containsAll(task4.getReopeningDates());
    }

    @Test
    void shouldReturnTaskWithStartDate() {
        //Act
        List<Task> returnedTask = taskRepository.findByStartDate(Timestamp.valueOf(LocalDateTime.of(2024, 12, 2, 12, 0, 0, 0)));
        //Assert
        assertThat(returnedTask).isNotEmpty().hasSize(1);

        assertThat(returnedTask.get(0).getId()).isEqualTo(task1.getId());
        assertThat(returnedTask.get(0).getObjective()).isEqualTo(task1.getObjective());
        assertThat(returnedTask.get(0).getComments()).hasSize(task1.getComments().size()).containsAll(task1.getComments());
        assertThat(returnedTask.get(0).getDescriptions()).hasSize(task1.getDescriptions().size()).containsAll(task1.getDescriptions());
        assertThat(returnedTask.get(0).getEstimations()).hasSize(task1.getEstimations().size()).containsAll(task1.getEstimations());
        assertThat(returnedTask.get(0).getExamples()).hasSize(task1.getExamples().size()).containsAll(task1.getExamples());
        assertThat(returnedTask.get(0).getStatusList()).hasSize(task1.getStatusList().size()).containsAll(task1.getStatusList());
        assertThat(returnedTask.get(0).getUrgencyLevels()).hasSize(task1.getUrgencyLevels().size()).containsAll(task1.getUrgencyLevels());
        assertThat(returnedTask.get(0).getClosingDates()).hasSize(task1.getClosingDates().size()).containsAll(task1.getClosingDates());
        assertThat(returnedTask.get(0).getEndDates()).hasSize(task1.getEndDates().size()).containsAll(task1.getEndDates());
        assertThat(returnedTask.get(0).getReopeningDates()).hasSize(task1.getReopeningDates().size()).containsAll(task1.getReopeningDates());
    }

    @Test
    void shouldReturnTaskWithEndDate() {
        //Act
        List<Task> returnedTask = taskRepository.findByEndDate(Timestamp.valueOf(LocalDateTime.of(2024, 12, 5, 8, 0, 0, 0)));
        //Assert
        assertThat(returnedTask).isNotEmpty().hasSize(1);

        assertThat(returnedTask.get(0).getId()).isEqualTo(task1.getId());
        assertThat(returnedTask.get(0).getObjective()).isEqualTo(task1.getObjective());
        assertThat(returnedTask.get(0).getComments()).hasSize(task1.getComments().size()).containsAll(task1.getComments());
        assertThat(returnedTask.get(0).getDescriptions()).hasSize(task1.getDescriptions().size()).containsAll(task1.getDescriptions());
        assertThat(returnedTask.get(0).getEstimations()).hasSize(task1.getEstimations().size()).containsAll(task1.getEstimations());
        assertThat(returnedTask.get(0).getExamples()).hasSize(task1.getExamples().size()).containsAll(task1.getExamples());
        assertThat(returnedTask.get(0).getStatusList()).hasSize(task1.getStatusList().size()).containsAll(task1.getStatusList());
        assertThat(returnedTask.get(0).getUrgencyLevels()).hasSize(task1.getUrgencyLevels().size()).containsAll(task1.getUrgencyLevels());
        assertThat(returnedTask.get(0).getClosingDates()).hasSize(task1.getClosingDates().size()).containsAll(task1.getClosingDates());
        assertThat(returnedTask.get(0).getEndDates()).hasSize(task1.getEndDates().size()).containsAll(task1.getEndDates());
        assertThat(returnedTask.get(0).getReopeningDates()).hasSize(task1.getReopeningDates().size()).containsAll(task1.getReopeningDates());
    }
}
