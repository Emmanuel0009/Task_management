package com.fullStack.project.todolist.models.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Entity
@Data
@Table(name = "description")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(name = "description text", nullable = false)
    private String descriptionText;

    @OneToMany(mappedBy = "description", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomDate> modifiedDates;

    @OneToOne(mappedBy = "comment", cascade = CascadeType.ALL)
    private CustomDate creationDate;

}
