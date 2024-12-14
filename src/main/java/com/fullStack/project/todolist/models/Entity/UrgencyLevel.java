package com.fullStack.project.todolist.models.Entity;

import com.fullStack.project.todolist.utils.Enum.UrgencyEnum;
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
@Table(name = "urgency")
public class UrgencyLevel {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Enumerated(EnumType.STRING)
   @Column(name = "level", nullable = false)
   private UrgencyEnum urgencyLevel;

   @ManyToOne
   @JoinColumn(name = "task_id", nullable = false)
   private Task task;

   @OneToMany(mappedBy = "urgencyLevel", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<CustomDate> modifiedDates;

   @OneToOne(mappedBy = "comment", cascade = CascadeType.ALL)
   private CustomDate creationDate;

}
