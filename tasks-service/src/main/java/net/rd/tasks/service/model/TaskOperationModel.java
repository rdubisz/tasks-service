package net.rd.tasks.service.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class TaskOperationModel {

    private Long id;
    private Long taskDefinitionId;
    private Long duration;
    private LocalDateTime startTime;

    public TaskOperationModel() {
        // Do-nothing constructor
    }

    public TaskOperationModel(Long taskDefinitionId, Long duration) {
        this.taskDefinitionId = taskDefinitionId;
        this.duration = duration;
    }

    public TaskOperationModel(Long taskDefinitionId, Long duration, LocalDateTime startTime) {
        this.taskDefinitionId = taskDefinitionId;
        this.duration = duration;
        this.startTime = startTime;
    }

    public TaskOperationModel(Long id, Long taskDefinitionId, Long duration, LocalDateTime startTime) {
        this.id = id;
        this.taskDefinitionId = taskDefinitionId;
        this.duration = duration;
        this.startTime = startTime;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskDefinitionId() {
        return taskDefinitionId;
    }

    public void setTaskDefinitionId(Long taskDefinitionId) {
        this.taskDefinitionId = taskDefinitionId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskOperationModel that = (TaskOperationModel) o;
        return Objects.equals(id, that.id) && Objects.equals(taskDefinitionId, that.taskDefinitionId) && Objects.equals(duration, that.duration) && Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskDefinitionId, duration, startTime);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TaskOperationModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("taskDefinitionId=" + taskDefinitionId)
                .add("duration=" + duration)
                .add("startTime=" + startTime)
                .toString();
    }
}
