package net.rd.tasks.service.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class TaskOperationQueryParamModel {

    private Long taskDefinitionId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TaskOperationQueryParamModel() {
        // Do-nothing constructor
    }

    public TaskOperationQueryParamModel(LocalDateTime startTime, LocalDateTime endTime, Long taskDefinitionId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskDefinitionId = taskDefinitionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskOperationQueryParamModel that = (TaskOperationQueryParamModel) o;
        return Objects.equals(taskDefinitionId, that.taskDefinitionId)
                && Objects.equals(startTime, that.startTime)
                && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskDefinitionId, startTime, endTime);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TaskOperationQueryParamModel.class.getSimpleName() + "[", "]")
                .add("taskDefinitionId=" + taskDefinitionId)
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .toString();
    }

    public Map<String, ?> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        if(taskDefinitionId != null)
            map.put("taskDefinitionId", taskDefinitionId);

        return map;
    }

    public Long getTaskDefinitionId() {
        return taskDefinitionId;
    }

    public void setTaskDefinitionId(Long taskDefinitionId) {
        this.taskDefinitionId = taskDefinitionId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
