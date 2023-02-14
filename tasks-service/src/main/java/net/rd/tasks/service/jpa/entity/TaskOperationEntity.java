package net.rd.tasks.service.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "task_operation")
public class TaskOperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long duration;
    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "task_definition_id", nullable = false, foreignKey = @ForeignKey(name="task_operation_task_definition_fk"))
    private TaskDefinitionEntity taskDefinition;

    public TaskOperationEntity() {
        // Do-nothing constructor
    }

    public TaskOperationEntity(Long duration) {
        this.duration = duration;
    }

    public TaskOperationEntity(Long duration, LocalDateTime startTime) {
        this.duration = duration;
        this.startTime = startTime;
    }

    public TaskOperationEntity(Long id, Long duration, LocalDateTime startTime, TaskDefinitionEntity taskDefinition) {
        this.id = id;
        this.duration = duration;
        this.startTime = startTime;
        this.taskDefinition = taskDefinition;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public TaskDefinitionEntity getTaskDefinitionEntity() {
        return taskDefinition;
    }

    public void setTaskDefinitionEntity(TaskDefinitionEntity taskDefinition) {
        this.taskDefinition = taskDefinition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskOperationEntity that = (TaskOperationEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(duration, that.duration) && Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, duration, startTime);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TaskOperationEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("duration=" + duration)
                .add("startTime=" + startTime)
                .add("taskDefinition=" + taskDefinition)
                .toString();
    }
}
