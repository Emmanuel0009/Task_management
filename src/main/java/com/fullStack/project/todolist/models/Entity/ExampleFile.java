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
@Table(name = "file")
public class ExampleFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "example_id", nullable = false)
    private Example example;

    @Lob
    @Column(name = "file")
    private Byte[] file;

    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomDate> modifiedDates;

    @OneToOne(mappedBy = "comment", cascade = CascadeType.ALL)
    private CustomDate creationDate;

}
