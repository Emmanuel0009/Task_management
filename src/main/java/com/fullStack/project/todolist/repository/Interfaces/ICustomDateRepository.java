package com.fullStack.project.todolist.repository.Interfaces;

import com.fullStack.project.todolist.models.Entity.*;
import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomDateRepository extends JpaRepository<CustomDate, Long> {

    @Query("SELECT c FROM CustomDate c WHERE c.taskId = :taskId")
    List<CustomDate> findByTaskId(@Param("taskId") Long taskId);

    @Query("SELECT c FROM CustomDate c WHERE c.taskId = :taskId AND c.TypeDateEnum = :typeDate")
    List<CustomDate> findByTaskIdAndTypeDate(@Param("taskId") Long taskId, @Param("typeDate")TypeDateEnum typeDate);

    @Query("SELECT c FROM Comment c WHERE c.commentId = :commentId")
    List<CustomDate> findByCommentId(@Param("commentId") Long commentId);

    @Query("SELECT c FROM CustomDate c WHERE c.commentId = :commentId AND c.TypeDateEnum = :typeDate")
    List<CustomDate> findByCommentIdAndTypeDate(@Param("commentId") Long taskId, @Param("typeDate")TypeDateEnum typeDate);

    @Query("SELECT c FROM CustomDate c WHERE c.descriptionId = :descriptionId")
    List<CustomDate> findByDescriptionId(@Param("descriptionId") Long descriptionId);

    @Query("SELECT c FROM CustomDate c WHERE c.descriptionId = :descriptionId AND c.TypeDateEnum = :typeDate")
    List<CustomDate> findByDescriptionIdAndTypeDate(@Param("descriptionId") Long taskId, @Param("typeDate")TypeDateEnum typeDate);

    @Query("SELECT c FROM CustomDate c WHERE c.estimationId = :estimationId")
    List<CustomDate> findByEstimationId(@Param("estimationId") Long estimationId);

    @Query("SELECT c FROM CustomDate c WHERE c.estimationId = :estimationId AND c.TypeDateEnum = :typeDate")
    List<CustomDate> findByEstimationIdAndTypeDate(@Param("estimationId") Long taskId, @Param("typeDate")TypeDateEnum typeDate);

    @Query("SELECT c FROM CustomDate c WHERE c.exampleId = :exampleId")
    List<CustomDate> findByExampleId(@Param("exampleId") Long exampleId);

    @Query("SELECT c FROM CustomDate c WHERE c.exampleId = :exampleId AND c.TypeDateEnum = :typeDate")
    List<CustomDate> findByExampleIdAndTypeDate(@Param("exampleId") Long taskId, @Param("typeDate")TypeDateEnum typeDate);

    @Query("SELECT c FROM CustomDate c WHERE c.exampleFileId = :exampleFileId")
    List<CustomDate> findByExampleFileId(@Param("exampleFileId") Long exampleFileId);

    @Query("SELECT c FROM CustomDate c WHERE c.exampleFileId = :exampleFileId AND c.TypeDateEnum = :typeDate")
    List<CustomDate> findByExampleFileIdAndTypeDate(@Param("exampleFileId") Long taskId, @Param("typeDate")TypeDateEnum typeDate);

    @Query("SELECT c FROM CustomDate c WHERE c.statusId = :statusId")
    List<CustomDate> findByStatusId(@Param("statusId") Long statusId);

    @Query("SELECT c FROM CustomDate c WHERE c.statusId = :statusId AND c.TypeDateEnum = :typeDate")
    List<CustomDate> findByStatusIdAndTypeDate(@Param("statusId") Long taskId, @Param("typeDate")TypeDateEnum typeDate);

    @Query("SELECT c FROM UrgencyLevel c WHERE c.urgencyLevelId = :urgencyLevelId")
    List<CustomDate> findByUrgencyLevelId(@Param("urgencyLevelId") Long urgencyLevelId);

    @Query("SELECT c FROM CustomDate c WHERE c.urgencyLevelId = :urgencyLevelId AND c.TypeDateEnum = :typeDate")
    List<CustomDate> findByUrgencyLevelIdAndTypeDate(@Param("urgencyLevelId") Long urgencyLevelId, @Param("typeDate")TypeDateEnum typeDate);
}
