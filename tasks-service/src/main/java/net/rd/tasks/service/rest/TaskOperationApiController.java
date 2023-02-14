package net.rd.tasks.service.rest;

import net.rd.tasks.service.model.TaskOperationModel;
import net.rd.tasks.service.model.TaskOperationQueryParamModel;
import net.rd.tasks.service.model.TaskOperationQueryResultModel;
import net.rd.tasks.service.service.TaskOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Task operations REST controller.
 */
@RestController
public class TaskOperationApiController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private TaskOperationService taskOperationService;

    public TaskOperationApiController(TaskOperationService taskOperationService) {
        this.taskOperationService = taskOperationService;
    }

    /**
     * Not very useful without paging implemented
     */
    @GetMapping("/api/v1/task/operation")
    public Iterable<TaskOperationModel> getAllTaskOperations() {
        return taskOperationService.getAllTaskOperations();
    }

    @GetMapping("/api/v1/task/{id}/operation")
    public Iterable<TaskOperationModel> getTaskOperations(@PathVariable Long id) {
        return taskOperationService.getTaskOperations(id);
    }

    @PostMapping("/api/v1/task/operation")
    public TaskOperationModel createTaskOperation(@RequestBody TaskOperationModel taskOperationModel) {
        return taskOperationService.createTaskOperation(taskOperationModel);
    }

    /**
     * Only value can be updated
     */
    @PutMapping("/api/v1/taskReading/{id}")
    public TaskOperationModel updateOrCreateTaskOperation(@RequestBody TaskOperationModel taskOperationModel, @PathVariable Long id) {
        return taskOperationService.updateOrCreateTaskOperation(taskOperationModel, id);
    }

    @GetMapping("/api/v1/task/operation/{id}")
    public TaskOperationModel getOneTaskDefinition(@PathVariable Long id) {
        return taskOperationService.getOneTaskOperation(id);
    }

    @DeleteMapping("/api/v1/task/operation/{id}")
    public void deleteTaskOperation(@PathVariable Long id) {
        taskOperationService.deleteTaskOperation(id);
    }

    @GetMapping("/api/v1/task/operation/query")
    public TaskOperationQueryResultModel query(
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime,
            @RequestParam(required = false) Long taskDefinitionId) {

        TaskOperationQueryParamModel param = new TaskOperationQueryParamModel(startTime, endTime, taskDefinitionId);
        return taskOperationService.taskOperationsQuery(param);
    }
}
