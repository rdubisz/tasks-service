package net.rd.tasks.service.model;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * Query result contains query parameters and the result value
 */
public class TaskOperationQueryResultModel {

    private Long taskDefinitionId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Double resultValue;

    public TaskOperationQueryResultModel() {
        // Do-nothing constructor
    }

    public TaskOperationQueryResultModel(Long taskDefinitionId, LocalDateTime startTime, LocalDateTime endTime, Double resultValue) {
        this.taskDefinitionId = taskDefinitionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.resultValue = resultValue;
    }

    /**
     * Copy query values from the query param object
     */
    public TaskOperationQueryResultModel(TaskOperationQueryParamModel param) {
        this.setStartTime(param.getStartTime());
        this.setEndTime(param.getEndTime());
        this.setTaskDefinitionId(param.getTaskDefinitionId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TaskOperationQueryResultModel.class.getSimpleName() + "[", "]")
                .add("taskDefinitionId=" + taskDefinitionId)
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .add("resultValue=" + resultValue)
                .toString();
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

    public Double getResultValue() {
        return resultValue;
    }

    public void setResultValue(Double resultValue) {
        this.resultValue = resultValue;
    }
}
