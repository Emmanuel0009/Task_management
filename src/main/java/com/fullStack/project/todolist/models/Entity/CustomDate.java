package com.fullStack.project.todolist.models.Entity;

import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Data
@Table(name = "custom_date")
public class CustomDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", columnDefinition = "TIMESTAMP")
    private Timestamp date;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_date", nullable = false)
    private TypeDateEnum typeDate;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "description_id")
    private Description description;

    @ManyToOne
    @JoinColumn(name = "estimation_id")
    private Estimation estimation;

    @ManyToOne
    @JoinColumn(name = "example_id")
    private Example example;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private ExampleFile exampleFile;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "urgency_id")
    private UrgencyLevel urgencyLevel;

}
